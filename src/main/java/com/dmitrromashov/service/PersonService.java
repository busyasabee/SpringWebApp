package com.dmitrromashov.service;

import com.dmitrromashov.dao.PersonDAO;
import com.dmitrromashov.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    public int addPerson(Person person) {
        try {
            personDAO.addPerson(person);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public Person getPerson(Integer id){
        return personDAO.getPerson(id);
    }

    public int updatePerson(Person person) {
        try {
            personDAO.updatePerson(person);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int deletePerson(Integer id) {
        try {
            personDAO.deletePerson(id);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }
}
