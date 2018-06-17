package com.dmitrromashov.validator;

import com.dmitrromashov.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        String personName = person.getName();
        String personSurname = person.getSurname();
        String personPatronymic = person.getPatronymic();
        String[] fields = new String[]{personName, personSurname, personPatronymic};
        Pattern fioPattern = Pattern.compile("^[а-яА-Я]+$");
        Matcher nameMatcher = fioPattern.matcher(personName);
        Matcher surnameMatcher = fioPattern.matcher(personSurname);
        Matcher patronymicMatcher = fioPattern.matcher(personPatronymic);
        Matcher[] matchers = new Matcher[]{nameMatcher, surnameMatcher, patronymicMatcher};
        for (int i = 0; i < matchers.length; i++) {
            errors.rejectValue(fields[i], "valid.fio");
        }
    }
}
