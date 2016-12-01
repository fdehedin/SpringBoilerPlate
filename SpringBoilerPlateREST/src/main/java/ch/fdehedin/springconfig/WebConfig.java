package ch.fdehedin.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ch.fdehedin")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		// registry.addViewController("/swagger").setViewName("redirect:/swagger-ui.html");
	}

	/**
	 * Since we don't have any controller logic, simpler to just define
	 * controller for page using View Controller. Note: had to extend
	 * WebMvcConfigurerAdapter to get this functionality
	 * 
	 * @param registry
	 */
	/*
	 * @Override public void addViewControllers(final ViewControllerRegistry
	 * registry) { registry.addViewController("/").setViewName("home"); //
	 * registry.addViewController("/").setViewName("swagger-ui.html"); //
	 * registry.addViewController("/").setViewName("swagger-ui"); }
	 */
 
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}
