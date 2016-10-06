package ch.fdehedin.service;

import java.util.List;

import ch.fdehedin.model.Person;

public interface PersonService {
	public List<Person> read();

	public void create(Person person);

	public Person read(int id);

	public void update(Person person);

	public void delete(Person person);
	
	public void delete(int id);
}
