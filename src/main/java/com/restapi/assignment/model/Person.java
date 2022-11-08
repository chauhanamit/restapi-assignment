package com.restapi.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class of Person
 * 
 * @author amit chauhan
 */

@ApiModel(description = "Person details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", nullable = false)
	@JsonProperty("first_name")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@JsonProperty("last_name")
	private String lastName;

	@Column(name = "age", nullable = false)
	private String age;

	@Column(name = "favourite_colour", nullable = false)
	@JsonProperty("favourite_colour")
	private String favouriteColour;

	@Column(name = "hobby", nullable = false)
	private String[] hobby;
}
