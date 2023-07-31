package com.task.ergo.model;

import com.task.ergo.controller.dto.PersonDTO;
import jakarta.persistence.*;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Entity
public class Person {
	
	@Id
	@Column(name = "person_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "personal_number", nullable = false)
	private String personalNumber;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "gender")
	private Gender gender;
	@Column(name = "date_of_birth", columnDefinition = "DATE")
	private LocalDate dateOfBirth;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "email")
	private String email;

	public Person() {
	}

	public Person(String personalNumber, String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, Gender gender) {
		this.personalNumber = personalNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = parseDateOfBirth(dateOfBirth);
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public static LocalDate parseDateOfBirth(String dateOfBirth) {
		try {
			return LocalDate.parse(dateOfBirth);
		} catch (Exception ignored) {
			return null;
		}
	}

	public static boolean validPersonalNumber(String personalNumber) {
		return personalNumber != null && personalNumber.length() == 9;
	}

	public PersonDTO toPersonDTO() {
		String dateOfBirth;
		if (this.dateOfBirth != null)
			dateOfBirth = String.valueOf(this.dateOfBirth);
		else
			dateOfBirth = null;

		return new PersonDTO(
				this.personalNumber,
				this.firstName,
				this.lastName,
				dateOfBirth,
				this.phoneNumber,
				this.email,
				this.gender.getValue());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
