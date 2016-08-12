package ch.fdehedin.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ch.fdehedin.domain.Person;

public class PersonDaoTest {

	private static final Logger LOGGER = Logger.getLogger(PersonDaoTest.class.getName());

	public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBoilerPlate/api";

	/* GET */
	@SuppressWarnings("unchecked")
	@Test
	public void getAll() {
		try {

			final RestTemplate restTemplate = new RestTemplate();
			ParameterizedTypeReference<List<Person>> responseType = new ParameterizedTypeReference<List<Person>>() {
			};

			final String url = REST_SERVICE_URI + "/person/all";
			LOGGER.log(Level.INFO, "getAll, url: " + url);
			//final ResponseEntity<? extends ArrayList<Person>> responseEntity = restTemplate.getForEntity(url,
			//		(Class<? extends ArrayList<Person>>) ArrayList.class);

			final ResponseEntity<List<Person>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					responseType);

			final HttpStatus statusCode = responseEntity.getStatusCode();
			Assert.assertEquals(HttpStatus.OK.toString(), statusCode.toString());

			assertThat(responseEntity.hasBody(), is(true));

			final List<Person> lst = responseEntity.getBody();
			assertThat(lst.isEmpty(), is(false));

			LOGGER.log(Level.INFO, "getAll, count: " + lst.size());

		} catch (final Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			Assert.fail(e.getMessage());

		}
	}

}
