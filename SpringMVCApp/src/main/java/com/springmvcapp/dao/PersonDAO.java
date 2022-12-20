package com.springmvcapp.dao;


import com.springmvcapp.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople(){
        return jdbcTemplate.query("SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class));
    }


    public Person showById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM Person WHERE id=?",
                new BeanPropertyRowMapper<>(Person.class),id);
    }

    public void savePerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(name,age,email) VALUES (?,?,?)",
                person.getName(),
                person.getAge(),
                person.getEmail());
    }

    public void updatePerson(int id,Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET name=?,age=?,email=? WHERE id=?",
              updatePerson.getName(),
              updatePerson.getAge(),
              updatePerson.getEmail(),
              id);
    }
    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }
}
