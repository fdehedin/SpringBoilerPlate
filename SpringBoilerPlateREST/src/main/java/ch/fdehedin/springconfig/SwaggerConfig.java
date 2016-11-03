package ch.fdehedin.springconfig;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("person").apiInfo(apiInfo()).select().paths(paths())
				.build();
	}

	// Here is an example where we select any api that matches one of these
	// paths
	private Predicate<String> paths() {
		return or(regex("/api/person.*"), regex("/api/*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SpringMVC BoilerPlate API")
				.description("This is the API reference to my Spring BoilerPlate Project")
				.termsOfServiceUrl("http://www.fdehedin.ch")
				.contact(new Contact("Frédéric Dehédin", "http://www.fdehedin.ch", "info@fdehedin.ch"))
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.version("2.0").build();
	}

}
