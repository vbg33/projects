package ru.vbg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class ATM extends Menu {


    public static void main(String [] args) throws IOException {
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("applicationContext");

        Menu menu = context.getBean("menu",Menu.class);
        menu.getLogin();
    }
}
