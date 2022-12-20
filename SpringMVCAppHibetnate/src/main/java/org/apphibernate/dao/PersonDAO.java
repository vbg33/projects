package org.apphibernate.dao;


import org.apphibernate.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public List getAllPeople(){
        Session session = sessionFactory.getCurrentSession();
        List personList = session.createQuery("select i from Person i", Person.class)
                .getResultList();
        return personList;
    }

    @Transactional
    public Person showById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    @Transactional
    public void savePerson(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void updatePerson(int id, Person updatePerson){
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class,id);

        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    @Transactional
    public void deletePerson(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));
    }
}
