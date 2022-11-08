package com.restapi.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.assignment.model.Person;
import com.restapi.assignment.repository.PersonRepository;

/**
 * person service class
 * 
 * @author amit chauhan
 */
@Service
public class PersonServiceImpl implements PersonService {

	PersonRepository personRepository;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	public List<Person> getAllPerson() {

		return personRepository.findAll();
	}

	public Optional<Person> getPersonDetailsById(long id) {

		return personRepository.findById(id);
	}

	public List<Person> savePersonDetails(List<Person> person) {

		return personRepository.saveAll(person);
	}

	public Optional<Person> updatePersonDetails(long id, Person person) {

		Optional<Person> perObj = getPersonDetailsById(id);
		if (perObj.isPresent()) {
			person.setId(id);
			return Optional.ofNullable(personRepository.save(person));
		}
		return Optional.empty();

	}

	public boolean deletePersonDetails(long id) {
		Optional<Person> perObj = getPersonDetailsById(id);
		if (perObj.isPresent()) {
			personRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
