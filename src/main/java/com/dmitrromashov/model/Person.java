package com.dmitrromashov.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name="id")
    private int personId;
    @Column(name="last_name")
    private String lastName;
    @Column(name="first_name")
    private String firstName;
    @Column(name="middle_name")
    private String middleName;
    @Column(name="birth_date")
    private Date birthDate;
    @Column(name="comment")
    private String comment;
    @Column(name="update_date")
    private Timestamp updateDate;

    public Person(){

    }

    public Person(int personId, String lastName, String firstName, String middleName, Date birthDate, String comment, Timestamp updateDate) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.comment = comment;
        this.updateDate = updateDate;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", comment='" + comment + '\'' +
                ", updateDate=" + updateDate +
                '}';
    }
}
