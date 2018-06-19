package com.dmitrromashov.controller;

import com.dmitrromashov.model.Person;
import com.dmitrromashov.service.PersonService;
import com.dmitrromashov.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    protected void initBinder(final WebDataBinder binder)
    {
        binder.addValidators(validator);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/persons",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addPerson(@RequestBody Person person){
        System.out.println("Person from client: " + person.toJSON());
        int result = personService.addPerson(person);
        if (result == -1){
            return "{\"id_error\": \"yes\"}";
        } else {
            return  "{\"person_id\": " + result + "}";
        }
    }

    @RequestMapping("/persons/{id}")
    public String getPerson(@PathVariable Integer id){
        try {
            return personService.getPerson(id).toJSON();
        } catch (Exception e){
            return "{\"id_error\": \"yes\"}";
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/persons/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePerson(@RequestBody Person person, @PathVariable int id){
        int result = personService.updatePerson(person);
        if (result == -1){
            return "{\"id_error\": \"yes\"}";
        } else {
            return person.toJSON();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/persons/{id}")
    public String deletePerson(@PathVariable Integer id){
        int result = personService.deletePerson(id);
        if (result == -1){
            return "{\"id_error\": \"yes\"}";
        } else {
            return "{\"ok\": \"yes\"}";
        }
    }

    @RequestMapping("/persons/fill")
    public List<Person> fillTable(){
        List<Person> personList = personService.getAllPersons();
        return personList;
    }


}
