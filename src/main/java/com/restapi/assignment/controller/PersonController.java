package com.restapi.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.assignment.exception.PersonNotFoundException;
import com.restapi.assignment.model.Person;
import com.restapi.assignment.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * PersonController class for person endpoints
 * 
 * @author amit chauhan
 */

@RestController
@RequestMapping("/person")
@Api(tags = "Person details Api's")
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	/**
	 * Rest Api to retrieve all person details
	 * 
	 * @return
	 */
	@ApiOperation(value = "retrieve all person's details")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@GetMapping
	public List<Person> retrieveAllPersonDetails() {
		return personService.getAllPerson();
	}

	/**
	 * Rest Api to retrieve person details
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "retrieve person details")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@GetMapping("/{id}")
	public Person retrievePersonDetailsById(
			@ApiParam(value = "Person Id", defaultValue = "1", required = true) @PathVariable long id) {

		Optional<Person> person = personService.getPersonDetailsById(id);
		if (person.isPresent()) {
			return person.get();
		} else {
			throw new PersonNotFoundException("id-" + id);
		}

	}

	/**
	 * Rest Api to store person details
	 * 
	 * @param person
	 * @return
	 */
	@ApiOperation(value = "store person Details")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@PostMapping
	public ResponseEntity<List<Person>> storePersonDetails(@RequestBody List<Person> person) {

		List<Person> perObj = personService.savePersonDetails(person);
		return ResponseEntity.ok().body(perObj);
	}

	/**
	 * Rest Api to update person details using Id
	 * 
	 * @param person
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "update person details")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePersonDetails(@RequestBody Person person,
			@ApiParam(value = "Person Id", defaultValue = "1", required = true) @PathVariable long id) {

		Optional<Person> perObj = personService.updatePersonDetails(id, person);

		if (perObj.isPresent()) {
			return ResponseEntity.ok().body(person);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Rest Api to delete a person by Id
	 * 
	 * @param id
	 */
	@ApiOperation(value = "delete person details")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@DeleteMapping("/{id}")
	public void deletePersonDetails(
			@ApiParam(value = "Person Id", defaultValue = "1", required = true) @PathVariable long id) {

		boolean output = personService.deletePersonDetails(id);
		if (!output) {
			throw new PersonNotFoundException("id-" + id);
		}
	}
}
