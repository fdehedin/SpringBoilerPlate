package ch.fdehedin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.fdehedin.domain.Person;
import ch.fdehedin.service.PersonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("api")
public class PersonController {

	PersonService personService;

	@Autowired
	public PersonController(final PersonService personService) {
		this.personService = personService;
	}

	@ApiOperation(value = "getAll", nickname = "getAll")
	@RequestMapping(method = RequestMethod.GET, path = "/person/all", produces = "application/json")
	/*
	 * @ApiImplicitParams({
	 * 
	 * @ApiImplicitParam(name = "name", value = "User's name", required = false,
	 * dataType = "string", paramType = "query", defaultValue = "FDN") })
	 */ 
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Person.class, responseContainer = "List"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<List<Person>> getAll() {
		final List<Person> lst = this.personService.getAll();
		if (lst.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Person>>(lst, HttpStatus.OK);
	}

	@ApiOperation(value = "getById", nickname = "getById")
	
	  @ApiImplicitParams({
	  
	  @ApiImplicitParam(name = "id", value = "Person's ID", required = false,
	  dataType = "Long", paramType = "path", defaultValue = "1") })
	  
	@RequestMapping(method = RequestMethod.GET, path = "person/{id}", produces = "application/json")
	public ResponseEntity<Person> getById(@PathVariable final Long id) {
		final Person person = this.personService.getById(id);
		if (person == null) {
			return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

}
