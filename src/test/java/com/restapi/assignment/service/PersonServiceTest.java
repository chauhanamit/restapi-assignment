package com.restapi.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import com.restapi.assignment.model.Person;
import com.restapi.assignment.repository.PersonRepository;

@RunWith(JUnit4.class)
public class PersonServiceTest {

	PersonService personService;
	PersonRepository personRepository;

	@BeforeEach
	void setUp() {
		personRepository = Mockito.mock(PersonRepository.class);
		personService = new PersonServiceImpl(personRepository);
	}

	@Test
	public void getAllPersonTest() {

		when(personRepository.findAll()).thenReturn(Stream
				.of(new Person(1L, "firstname1", "lastname1", "31", "blue", new String[] { "cricket", "football" }),
						new Person(1L, "firstname2", "lastname2", "33", "red", new String[] { "carrom", "chess" }))
				.collect(Collectors.toList()));

		assertEquals(2, personService.getAllPerson().size());

	}

	@Test
	public void getPersonDetailsByIdTest() {

		when(personRepository.findById(any())).thenReturn(Optional.of(getPersonObject()));
		Optional<Person> person = personService.getPersonDetailsById(1);
		assertEquals(1, person.get().getId());
	}

	@Test
	public void savePersonDetailsTest() {

		when(personRepository.saveAll(anyList())).thenReturn(Stream.of(getPersonObject()).collect(Collectors.toList()));
		List<Person> perObj = personService
				.savePersonDetails(Stream.of(getPersonObject()).collect(Collectors.toList()));
		assertEquals(1, perObj.size());
	}

	@Test
	public void updatePersonDetailsTest() {

		Person person = getPersonObject();
		person.setFavouriteColour("red");
		when(personRepository.findById(any())).thenReturn(Optional.of(getPersonObject()));
		when(personRepository.save(any())).thenReturn(person);
		Optional<Person> perObj = personService.updatePersonDetails(1, person);
		assertEquals("red", perObj.get().getFavouriteColour());
	}

	@Test
	public void deletePersonDetailsTest() {

		when(personRepository.findById(any())).thenReturn(Optional.of(getPersonObject()));
		boolean flag = personService.deletePersonDetails(1);
		assertEquals(true, flag);
	}

	public Person getPersonObject() {

		return new Person(1L, "firstname1", "lastname1", "31", "blue", new String[] { "cricket", "football" });
	}
}
