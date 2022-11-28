package ru.emailapp;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(SpringConfig.class);

        EmailAppMenu emailAppMenu1 = context.getBean("emailAppMenu",EmailAppMenu.class);
        emailAppMenu1.start();
    }
}
