package ch.fdehedin.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.fdehedin.model.Person;
import ch.fdehedin.model.Status;

@Service
public class PersonServiceImpl implements PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	private final HashMap<Integer, Person> persons = new HashMap<Integer, Person>();
	private int nextId = 0;

	public PersonServiceImpl() {
		ensureTestData();
	}

	public List<Person> read() {
		return this.search(null);
	}

	public List<Person> search(String query) {
		ArrayList<Person> arrayList = new ArrayList<>();
		for (Person person : persons.values()) {
			boolean passesFilter = (query == null || query.isEmpty())
					|| person.toString().toLowerCase().contains(query.toLowerCase());
			if (passesFilter) {
				arrayList.add(person);
			}
		}
		Collections.sort(arrayList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}

	/**
	 * Finds all Customer's that match given filter and limits the resultset.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @param start
	 *            the index of first result
	 * @param maxresults
	 *            maximum result count
	 * @return list a Customer objects
	 */
	public synchronized List<Person> search(String stringFilter, int start, int maxresults) {
		ArrayList<Person> arrayList = new ArrayList<>();
		for (Person contact : persons.values()) {
			boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
					|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
			if (passesFilter) {
				arrayList.add(contact);
			}
		}
		Collections.sort(arrayList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	/**
	 * @return the amount of all customers in the system
	 */
	public synchronized long count() {
		return persons.size();
	}

	/**
	 * Deletes a customer from a system
	 *
	 * @param value
	 *            the Customer to be deleted
	 */
	public synchronized void delete(Person value) {
		persons.remove(value.getId());
	}

	/**
	 * Sample data generation
	 */
	public void ensureTestData() {
		if (read().isEmpty()) {
			final String[] names = new String[] { "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
					"Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
					"Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
					"Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
					"Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
					"Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
					"Jaydan Jackson", "Bernard Nilsen" };
			Random r = new Random(0);
			for (String name : names) {
				String[] split = name.split(" ");
				Person c = new Person();
				c.setFirstName(split[0]);
				c.setLastName(split[1]);
				c.setEmail(split[0].toLowerCase() + "@" + split[1].toLowerCase() + ".com");
				c.setStatus(Status.values()[r.nextInt(Status.values().length)]);
				Calendar cal = Calendar.getInstance();
				int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);
				cal.add(Calendar.DAY_OF_MONTH, daysOld);
				c.setBirthDate(cal.getTime());
				create(c);
			}
		}
	}

	/**
	 * Persists or updates customer in the system. Also assigns an identifier
	 * for new Customer instances.
	 *
	 * @param entry
	 */
	@Override
	public void create(Person person) {
		if (person.getId() == 0) {
			person.setId(nextId++);
			LOGGER.debug("setting id to: " + person.getId());
		}

		persons.put(person.getId(), person);
	}

	@Override
	public Person read(int id) throws NoSuchElementException {
		if (!this.persons.containsKey(id)) {
			throw new NoSuchElementException("could not find entry with id " + id);
		}
		return persons.get(id);
	}

	@Override
	public void update(Person person) {
		persons.put(person.getId(), person);
	}

	@Override
	public void delete(int id) {
		persons.remove(id);
	}
}
