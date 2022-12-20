package com.springmvcapp.models;

import javax.validation.constraints.*;

public class Person {

    private int id;

    @NotEmpty(message = "name incorrect")
    @Size(min = 2,max = 30, message = "Name should have 2 up 30 symbols")
    private String name;

    @Min(value = 0,message = "age incorrect")
    @Max(value = 130,message = "age incorrect")
    private int age;

    @NotEmpty(message = "Email will not be empty")
    @Email(message = "Email incorrect")
    private String email;

    public Person(int id,String name,int age,String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
