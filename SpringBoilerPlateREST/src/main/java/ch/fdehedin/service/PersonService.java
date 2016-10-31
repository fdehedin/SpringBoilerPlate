package ch.fdehedin.service;

import java.util.List;
import java.util.NoSuchElementException;

import ch.fdehedin.model.Person;

public interface PersonService {
	public List<Person> read();

	public List<Person> search(String query);

	public void create(Person person);

	public Person read(int id) throws NoSuchElementException;

	public void update(Person person);

	public void delete(Person person);

	public void delete(int id);

}
