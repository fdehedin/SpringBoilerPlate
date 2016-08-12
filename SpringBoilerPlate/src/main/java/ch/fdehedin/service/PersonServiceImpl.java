package ch.fdehedin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import ch.fdehedin.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

	String[] names = { "Nikolaus Otto", "Robert Ford", "Gottlieb Daimler", "Lt. General Masaharu Homma" };

	@Override
	public List<Person> getAll() {
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
		return person;
	}

	@Override
	public Person getById(final Long id) {
		Person person = new Person();
		person.setName(names[id.intValue()]);
		person.setAge(50);
		return person;
	}

	@Override
	public void save(final Person person) {
		// Save person to database ...
	}

	private Integer randomAge() {
		Random random = new Random();
		return 10 + random.nextInt(100);
	}

	private String randomName() {
		Random random = new Random();
		return names[random.nextInt(names.length)];
	}

}
