package com.task.ergo.service;

import com.task.ergo.controller.dto.PersonDTO;
import com.task.ergo.exception.PersonException;
import com.task.ergo.model.Gender;
import com.task.ergo.model.Person;
import com.task.ergo.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public void createPerson(PersonDTO personDTO) {
		personRepository.save(new Person(
				personDTO.getPersonalNumber(), personDTO.getFirstName(), personDTO.getLastName(),
				personDTO.getDateOfBirth(), personDTO.getEmail(), personDTO.getPhoneNumber(),
				Gender.getGender(personDTO.getGender())));
	}

	public boolean deletePerson(String personalNumber) {
		Person personToDelete = personRepository.findByPersonalNumber(personalNumber)
				.orElseThrow(() -> new PersonException("No person found"));

		personRepository.deleteById(personToDelete.getId());
		return personRepository.findById(personToDelete.getId()).isEmpty();
	}

	public boolean updatePerson(PersonDTO person) throws PersonException {
		Person personToUpdate = personRepository.findByPersonalNumber(person.getPersonalNumber())
				.orElseThrow(() -> new PersonException("No person found"));

		if (person.getGender() != null)
			personToUpdate.setGender(Gender.getGender(person.getGender()));

		if (StringUtils.hasLength(person.getDateOfBirth()))
			personToUpdate.setDateOfBirth(Person.parseDateOfBirth(person.getDateOfBirth()));

		if (StringUtils.hasLength(person.getFirstName()))
			personToUpdate.setFirstName(person.getFirstName());

		if (StringUtils.hasLength(person.getLastName()))
			personToUpdate.setLastName(person.getLastName());

		personToUpdate.setEmail(person.getEmail());
		personToUpdate.setPhoneNumber(person.getPhoneNumber());

		personRepository.save(personToUpdate);

		return true;
	}

	public List<PersonDTO> getPersonByFirstName(String firstName) {
		return personRepository.findByFirstName(firstName)
				.stream()
				.map(Person::toPersonDTO)
				.collect(Collectors.toList());
	}

	public List<PersonDTO> getPersonByDateOfBirth(String dateOfBirth) {
		return personRepository.findByDateOfBirth(LocalDate.parse(dateOfBirth))
				.stream()
				.map(Person::toPersonDTO)
				.collect(Collectors.toList());
	}

	public List<PersonDTO> getAllPersonList() {
		return personRepository.findAll()
				.stream()
				.map(Person::toPersonDTO)
				.collect(Collectors.toList());
	}

	public Optional<Person> getPersonByPersonalNumber(String personalNumber) {
		return personRepository.findByPersonalNumber(personalNumber);
	}

}
