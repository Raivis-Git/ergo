package com.task.ergo.controller;

import com.task.ergo.controller.dto.PersonDTO;
import com.task.ergo.exception.PersonException;
import com.task.ergo.model.Gender;
import com.task.ergo.model.Person;
import com.task.ergo.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/person")
public class PersonController {
	
	private final PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping
	public ResponseEntity<?> savePerson(@RequestBody PersonDTO person) {

		if (!StringUtils.hasLength(person.getFirstName()))
			throw new PersonException("First name not provided");

		if (!StringUtils.hasLength(person.getLastName()))
			throw new PersonException("Last name not provided");

		if (!Gender.genderExists(person.getGender()))
			throw new PersonException("Gender not provided");

		if (!Person.validPersonalNumber(person.getPersonalNumber()))
			throw new PersonException("Personal number not valid");

		personService.createPerson(person);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	@RequestMapping("/{personalNumber}")
	public ResponseEntity<?> deletePerson(@PathVariable String personalNumber) {
		if (personService.deletePerson(personalNumber))
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.internalServerError().build();
	}

	@PutMapping
	public ResponseEntity<?> updatePerson(@RequestBody PersonDTO person) {

		return ResponseEntity.ok(personService.updatePerson(person));
	}


	@GetMapping
	@RequestMapping("/findByBirthDate/{dateOfBirth}")
	public ResponseEntity<?> findByBirthDate(@PathVariable String dateOfBirth) {
		if (Person.parseDateOfBirth(dateOfBirth) == null)
			throw new PersonException("Date of birth is invalid. Format: yyyy-MM-dd");

		return ResponseEntity.ok(personService.getPersonByDateOfBirth(dateOfBirth));
	}

	@GetMapping
	@RequestMapping("/findAll")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(personService.getAllPersonList());
	}

	@GetMapping
	@RequestMapping("/findByName/{firstName}")
	public ResponseEntity<?> findByName(@PathVariable String firstName) {
		if (!StringUtils.hasLength(firstName))
			throw new PersonException("First name not provided");

		return ResponseEntity.ok(personService.getPersonByFirstName(firstName));
	}
	
}
