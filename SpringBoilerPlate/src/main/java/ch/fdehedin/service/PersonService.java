package ch.fdehedin.service;

import java.util.List;

import ch.fdehedin.domain.Person;

public interface PersonService {
	public List<Person> getAll();

	public Person getById(Long id);

	public void save(Person person);
}
