package ch.fdehedin.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.fdehedin.service.PersonService;
import ch.fdehedin.service.PersonServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public PersonService getPersonService() {
		return new PersonServiceImpl();
	}

}
