package com.restapi.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restapi.assignment.model.Person;


/** Person Repository interface
 * @author amit chauhan
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
