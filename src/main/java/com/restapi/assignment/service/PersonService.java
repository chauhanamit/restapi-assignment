package com.restapi.assignment.service;

import java.util.List;
import java.util.Optional;

import com.restapi.assignment.model.Person;

/**
 * @author amit chauhan person service interface
 */
public interface PersonService {

	public List<Person> getAllPerson();

	public Optional<Person> getPersonDetailsById(long id);
	
	public List<Person> savePersonDetails(List<Person> person);
	
	public Optional<Person> updatePersonDetails(long id,Person person);
	
	public boolean deletePersonDetails(long id);
}
