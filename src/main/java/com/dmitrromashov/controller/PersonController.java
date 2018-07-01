package com.dmitrromashov.controller;

import com.dmitrromashov.model.Person;
import com.dmitrromashov.model.PersonResponseBody;
import com.dmitrromashov.service.PersonService;
import com.dmitrromashov.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonValidator validator;

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponseBody> addPerson(@RequestBody Person person) {
        System.out.println("Person from client: " + person.toJSON());
        PersonResponseBody responseBody = new PersonResponseBody();
        int result = personService.addPerson(person);
        if (result != -1) {
            responseBody.setMessage("ok");
            responseBody.setPersonId(result);
            return ResponseEntity.ok(responseBody);
        } else {
            responseBody.setMessage("Пользователь с таким ID уже существует");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @RequestMapping("/persons/{id}")
    public ResponseEntity<PersonResponseBody> getPerson(@PathVariable Integer id) {
        PersonResponseBody responseBody = new PersonResponseBody();

        Person requestedPerson = personService.getPerson(id);
        if (requestedPerson != null){
            responseBody.setPerson(requestedPerson);
            responseBody.setMessage("ok");
            return ResponseEntity.ok(responseBody);
        } else {
            responseBody.setMessage("Не найден пользователь с ID = " + id);
            return ResponseEntity.badRequest().body(responseBody);
        }


    }

    @RequestMapping(method = RequestMethod.PUT, value = "/persons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponseBody> updatePerson(@RequestBody Person person, @PathVariable int id) {
        PersonResponseBody responseBody = new PersonResponseBody();
        int result = personService.updatePerson(person);
        if (result != -1) {
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/persons/{id}")
    public ResponseEntity<PersonResponseBody> deletePerson(@PathVariable Integer id) {
        int result = personService.deletePerson(id);
        PersonResponseBody responseBody = new PersonResponseBody();

        if (result != -1) {
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @RequestMapping("/persons/fill")
    public List<Person> fillTable() {
        List<Person> personList = personService.getAllPersons();
        return personList;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/persons/handle")
    public String handleTable(@RequestParam(value = "ids[]") List<Integer> ids) {
        String result = personService.handlePersons(ids);
        System.out.println("Result = " + result);
        return result;
    }

}
