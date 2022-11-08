package com.restapi.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.restapi.assignment.exception.PersonNotFoundException;
import com.restapi.assignment.model.Person;
import com.restapi.assignment.service.PersonService;

@RunWith(JUnit4.class)
public class PersonControllerTest {

	PersonService personService;
	PersonController personController;

	@BeforeEach
	void setUp() {
		personService = Mockito.mock(PersonService.class);
		personController = new PersonController(personService);
	}

	@Test
	public void retrieveAllPersonDetailsTest() {
		when(personService.getAllPerson()).thenReturn(Stream
				.of(new Person(1L, "firstname1", "lastname1", "31", "blue", new String[] { "cricket", "football" }),
						new Person(1L, "firstname2", "lastname2", "33", "red", new String[] { "carrom", "chess" }))
				.collect(Collectors.toList()));
		assertEquals(2, personController.retrieveAllPersonDetails().size());
	}

	@Test
	public void retrievePersonDetailsByIdTest() {

		when(personService.getPersonDetailsById(anyLong())).thenReturn(Optional.of(getPersonObject()));
		Person person = personController.retrievePersonDetailsById(1L);
		assertEquals(1, person.getId());
	}

	@Test
	public void storePersonDetailsTest() {
		when(personService.savePersonDetails(anyList()))
				.thenReturn(Stream.of(getPersonObject()).collect(Collectors.toList()));
		ResponseEntity<List<Person>> resObj = personController
				.storePersonDetails(Stream.of(getPersonObject()).collect(Collectors.toList()));
		assertEquals(1, resObj.getBody().get(0).getId());
	}

	@Test
	public void updatePersonDetailsTest() {

		Person person = getPersonObject();
		person.setFavouriteColour("red");

		when(personService.updatePersonDetails(anyLong(), any())).thenReturn(Optional.of(person));
		ResponseEntity<Person> resObj = personController.updatePersonDetails(getPersonObject(), 1);
		assertEquals(1, resObj.getBody().getId());
	}

	@Test
	public void deletePersonDetailsTest() {

		when(personService.deletePersonDetails(anyLong())).thenReturn(true);
		personController.deletePersonDetails(1);
		assertEquals(1, getPersonObject().getId());
	}

	@Test
	public void deletePersonExceptionTest() {

		Assertions.assertThrows(PersonNotFoundException.class, () -> {
			personController.deletePersonDetails(1);
		});
	}

	public Person getPersonObject() {

		return new Person(1L, "firstname1", "lastname1", "31", "blue", new String[] { "cricket", "football" });
	}
}
