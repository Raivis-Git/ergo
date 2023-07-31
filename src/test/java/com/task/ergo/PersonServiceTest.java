package com.task.ergo;

import com.task.ergo.controller.dto.PersonDTO;
import com.task.ergo.model.Gender;
import com.task.ergo.model.Person;
import com.task.ergo.service.PersonService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersonServiceTest {

    @Autowired
    private PersonService personService;
    Logger log = Logger.getLogger(PersonServiceTest.class.getName());

    Person testPerson;

    @BeforeEach
    private void fetchTestPerson() {
        log.info("fetch person for test");
        testPerson = personService.getPersonByPersonalNumber("9999999999").orElseThrow();
    }

    public Person createValidPerson() {
        personService.createPerson(new PersonDTO("11111111111", "Testerf", "Testerl", "2000-12-02","21231232", "test@test.com", Gender.MALE.getValue()));
        return personService.getPersonByPersonalNumber("11111111111").orElseThrow();
    }

    @Test
    @Order(1)
    public void addPerson() {
        Person addedPerson = createValidPerson();

        assertEquals("11111111111", addedPerson.getPersonalNumber());
        assertEquals("Testerf", addedPerson.getFirstName());
        assertEquals("Testerl", addedPerson.getLastName());
        assertEquals(LocalDate.of(2000, 12, 2), addedPerson.getDateOfBirth());
        assertEquals("21231232", addedPerson.getPhoneNumber());
        assertEquals("test@test.com", addedPerson.getEmail());
        assertEquals(Gender.MALE, addedPerson.getGender());
        assertThrows(RuntimeException.class, this::createValidPerson);
    }

    @Test
    @Order(2)
    public void updatePerson() {
        personService.updatePerson(new PersonDTO("9999999999", "Fi", "La", "1999-01-10", "21113232", "update@update.com", Gender.FEMALE.getValue()));
        Person updatedPerson = personService.getPersonByPersonalNumber("9999999999").orElseThrow();
        assertEquals("Fi", updatedPerson.getFirstName());
        assertEquals("La", updatedPerson.getLastName());
        assertEquals(LocalDate.of(1999, 1, 10), updatedPerson.getDateOfBirth());
        assertEquals("21113232", updatedPerson.getPhoneNumber());
        assertEquals("update@update.com", updatedPerson.getEmail());
        assertEquals(Gender.FEMALE, updatedPerson.getGender());
    }

    @Test
    @Order(3)
    public void deletePerson() {
        personService.deletePerson(testPerson.getPersonalNumber());
        assertTrue(personService.getPersonByPersonalNumber("9999999999").isEmpty());
    }


}
