package com.dmitrromashov.dao;

import com.dmitrromashov.model.Person;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class PersonDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public int addPerson(Person person){
        if(person.getPersonId() == 0){
            String hql = "FROM Person as psn ORDER BY psn.id DESC";
            Person lastPerson = (Person) entityManager.createQuery(hql).getResultList().get(0);
            person.setPersonId(lastPerson.getPersonId() + 1);
        }
        entityManager.persist(person);
        return person.getPersonId();
    }

    public Person getPerson(Integer id) {
        return entityManager.find(Person.class, id);
    }

    public void updatePerson(Person person) {
        Person updatedPerson = getPerson(person.getPersonId());
        updatedPerson.setFirstName(person.getFirstName());
        updatedPerson.setLastName(person.getLastName());
        updatedPerson.setBirthDate(person.getBirthDate());
        updatedPerson.setMiddleName(person.getMiddleName());
        entityManager.flush();
    }

    public void deletePerson(Integer id) {
        entityManager.remove(getPerson(id));
    }

    public List<Person> getAllPersons() {
        String hql = "FROM Person as psn ORDER BY psn.id";
        return (List<Person>) entityManager.createQuery(hql).getResultList();
    }
}
