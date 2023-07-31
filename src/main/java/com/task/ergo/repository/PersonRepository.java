package com.task.ergo.repository;

import com.task.ergo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findByFirstName(String firstName);
	List<Person> findByDateOfBirth(LocalDate dateOfBirth);
	Optional<Person> findByPersonalNumber(String personalNumber);
	
}
