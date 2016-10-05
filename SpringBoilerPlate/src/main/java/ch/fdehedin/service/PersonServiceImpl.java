package ch.fdehedin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.fdehedin.controller.PersonController;
import ch.fdehedin.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	String[] names = { "Nikolaus Otto", "Robert Ford", "Gottlieb Daimler", "Lt. General Masaharu Homma" };

	@Override
	public List<Person> read() {
		List<Person> result = new ArrayList<Person>();

		for (int i = 0; i < names.length; i++) {
			Person p = new Person();
			p.setAge(randomAge());
			p.setName(names[i]);
			result.add(p);
		}

		return result;
	}

	public Person getRandom() {
		Person person = new Person();
		person.setName(randomName());
		person.setAge(randomAge());
		person.setId(randomId());
		return person;
	}

	@Override
	public Person read(final int id) {
		Person person = new Person();
		person.setName(names[id]);
		person.setAge(50);
		return person;
	}

	@Override
	public void create(final Person person) {
		person.setId(randomId());
		logger.debug("save person {} with id {}", person.getName(), person.getId());
	}

	private int randomAge() {
		Random random = new Random();
		return 10 + random.nextInt(100);
	}

	private int randomId() {
		Random random = new Random();
		return 10 + random.nextInt(100);
	}

	private String randomName() {
		Random random = new Random();
		return names[random.nextInt(names.length)];
	}

	@Override
	public void update(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Person person) {
		// TODO Auto-generated method stub

	}

}
