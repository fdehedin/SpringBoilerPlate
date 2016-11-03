package ch.fdehedin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.fdehedin.model.Person;
import ch.fdehedin.service.PersonService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("api")
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	PersonService personService;

	@Autowired
	public PersonController(final PersonService personService) {
		this.personService = personService;
		logger.debug("creating PersonController");
	}

	// @ApiOperation(value = "Create a new person", response = Person.class,
	// consumes = "application/json")

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Person.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@ApiOperation(httpMethod = "POST", value = "create a new person", response = Person.class, nickname = "createPerson", consumes = "application/json")
	@RequestMapping(method = RequestMethod.POST, path = "person/create", produces = "application/json")
	public ResponseEntity<Person> create(@ApiParam("person") @RequestBody Person person) {
		this.personService.create(person);
		return new ResponseEntity<Person>(person, HttpStatus.CREATED);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Updated", response = Person.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@ApiOperation(httpMethod = "PUT", value = "update a person", response = Person.class, nickname = "updatePerson", consumes = "application/json")
	@RequestMapping(method = RequestMethod.PUT, path = "person/update", produces = "application/json")
	public ResponseEntity<Person> update(@ApiParam("person") @RequestBody Person person) {
		this.personService.update(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@ApiOperation(httpMethod = "DELETE", value = "deletes a person", nickname = "deletePerson")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Person's ID", required = false, dataType = "Long", paramType = "path", defaultValue = "1") })

	@RequestMapping(method = RequestMethod.DELETE, path = "person/delete/{id}")
	public ResponseEntity<Person> delete(@PathVariable final int id) {
		this.personService.delete(id);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "read all persons", nickname = "read")
	@RequestMapping(method = RequestMethod.GET, path = "/person/read", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = Person.class, responseContainer = "List"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<List<Person>> read() {
		final List<Person> lst = this.personService.read();
		if (lst.isEmpty()) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Person>>(lst, HttpStatus.OK);
	}

	@ApiOperation(value = "read 1 person by id", nickname = "read by id")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Person's ID", required = false, dataType = "Long", paramType = "path", defaultValue = "1") })
	@RequestMapping(method = RequestMethod.GET, path = "person/read/{id}", produces = "application/json")
	public ResponseEntity<Person> read(@PathVariable final int id) {
		final Person person = this.personService.read(id);
		if (person == null) {
			return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

}
