package com.dmitrromashov.service;

import com.dmitrromashov.dao.PersonDAO;
import com.dmitrromashov.model.Person;
import com.dmitrromashov.model.TableResponseBody;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {
    private PersonDAO personDAO;

    public PersonService(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    public int addPerson(Person person) {

        try {
            return personDAO.addPerson(person);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
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

    public TableResponseBody handlePersons(List<Integer> ids) {
        TableResponseBody responseBody = new TableResponseBody();
        String[] statuses = new String[ids.size()];

        for (int i = 0; i < ids.size(); i++) {
            Integer id = ids.get(i);
            if (personDAO.handlePerson(id)) {
                statuses[i] = "good";
            } else {
                statuses[i] = "bad";
            }
        }

        responseBody.setIds(ids);
        responseBody.setStatuses(statuses);
        return responseBody;
    }
}
