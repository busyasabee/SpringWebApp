package com.dmitrromashov.controller;

import com.dmitrromashov.model.Person;
import com.dmitrromashov.service.PersonService;
import com.dmitrromashov.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Date;

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
    public Person addPerson(@RequestBody Person person, Model model){
        System.out.println("Person controller add person");
        System.out.println(person);
        int t = 3;
        personService.addPerson(person);
        model.addAttribute("person", "some data");
        return person;
    }
}
