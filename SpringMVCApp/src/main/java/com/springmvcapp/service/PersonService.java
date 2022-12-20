package com.springmvcapp.service;

import com.springmvcapp.dao.PersonDAO;
import com.springmvcapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonDAO personDAO;

    @Autowired
    PersonService(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    public List<Person> getAllPeople(){
        return personDAO.getAllPeople();
    }

    public Person showById(int id){
        return personDAO.showById(id);
    }

    public void savePerson(Person person){
        personDAO.savePerson(person);
    }

    public void updatePerson(int id,Person person){
        personDAO.updatePerson(id,person);
    }

    public void deletePerson(int id){
        personDAO.deletePerson(id);
    }
}
