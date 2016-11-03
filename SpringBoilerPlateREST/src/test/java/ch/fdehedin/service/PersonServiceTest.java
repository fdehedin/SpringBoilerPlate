package ch.fdehedin.service;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ch.fdehedin.model.Person;
import ch.fdehedin.springconfig.AppConfig;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(Parameterized.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class PersonServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);

	private int idToTest;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Autowired
	private PersonService personService;

	private TestContextManager testContextManager;

	@Before
	public void setUpContext() throws Exception {
		// this is where the magic happens, we actually do "by hand" what the
		// spring runner would do for us,
		// read the JavaDoc for the class bellow to know exactly what it does,
		// the method names are quite accurate though
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 1 }, { 2 }, });
	}

	public PersonServiceTest(int idToTest) {
		this.idToTest = idToTest;
	}

	@Test
	public void testPersonService() {
		assertThat(personService, Matchers.notNullValue());
	}

	@Test
	public void read() {
		List<Person> persons = personService.read();
		assertThat(persons, Matchers.notNullValue());
		assertThat(persons, Matchers.hasSize(Matchers.greaterThan(0)));
	}

	@Test
	public void readWithId() {

		try {
			// reinit because there maybe removed elements..
			this.personService = new PersonServiceImpl();
		} catch (Exception e) {
			LOGGER.error(null, e);
		}

		Person person = personService.read(idToTest);
		assertThat(person, Matchers.notNullValue());
		assertThat(person.getLastName(), Matchers.not(Matchers.isEmptyOrNullString()));

		// throw error check..
		exception.expect(NoSuchElementException.class);
		person = personService.read(2000000);

	}

	@Test
	public void create() {
		Person.PersonBuilder personBuilder = new Person.PersonBuilder();
		Person person = personBuilder.birthDate(new Date()).email("test@test.ch").firstName("firstName")
				.lastName("lastName").createPerson();
		personService.create(person);

		assertThat(person, Matchers.notNullValue());
		assertThat(person.getLastName(), Matchers.not(Matchers.isEmptyOrNullString()));
		assertThat(person.getId(), Matchers.greaterThan(0));

		// throw error check..
		exception.expect(NullPointerException.class);
		personService.create(null);

	}

	@Test
	public void search() {
		try {
			// reinit because there maybe removed elements..
			this.personService = new PersonServiceImpl();
		} catch (Exception e) {
			LOGGER.error(null, e);
		}

		List<Person> persons = personService.search("Robinson");
		assertThat(persons, Matchers.notNullValue());
		assertThat(persons, Matchers.hasSize(Matchers.greaterThan(0)));
	}

	@Test
	public void delete() {
		personService.delete(idToTest);

		// throw error check..
		exception.expect(NoSuchElementException.class);
		personService.read(idToTest);

	}

	@Test
	public void deleteByPersonObject() {

		try {
			// reinit because there maybe removed elements..
			this.personService = new PersonServiceImpl();
		} catch (Exception e) {
			LOGGER.error(null, e);
		}

		Person person = this.personService.read(idToTest);
		assertThat(person, Matchers.notNullValue());

		personService.delete(person);

		// throw error check..
		exception.expect(NoSuchElementException.class);
		personService.read(idToTest);

	}

}
