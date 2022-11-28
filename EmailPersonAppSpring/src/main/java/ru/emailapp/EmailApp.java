package ru.emailapp;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class EmailApp {

    static List<EmailPerson> emailList = new ArrayList();
    static Iterator<EmailPerson> iter = emailList.listIterator();

    //добавление пользователя
    public void addUser(){
        EmailPerson newEmail = new EmailPerson();
        boolean found = false;
        for(EmailPerson email : emailList){
            if (newEmail.equals(email)){
                found = true;
                System.out.println("Такой пользователь уже есть\n");
                //уменьшение порядкового номера
                newEmail.minusSerialNumber();
            }
        }
        if(!found){
            emailList.add(newEmail);
            System.out.println("Пользователь добавлен\n");
        }
    }

    //вывод всех сотрудников
    public void showAllUsers(){
        if(iter.hasNext()){

            System.out.print("-----------");
            for (EmailPerson email : emailList)
                System.out.println(email.showInfo());
            System.out.println("-----------");
        }else {
            System.out.println("\nСписок пуст\n");
        }
    }

    //очистка списка
    public void clearList(){
        if(iter.hasNext()){
            for (int i = 0; i < emailList.size();i++) {
                emailList.remove(i);
            }
        }else {
            System.out.println("\nСписок пуст\n");
        }
    }

        //удаление пользователя
    public void deleteUser(){
        if(iter.hasNext()){
                boolean quitDeleteUser = false;

                Scanner sc = new Scanner(System.in);
                System.out.println("Введите порядковый номер для удаления:");

                while (!quitDeleteUser){
                    int p = sc.nextInt();

                    try{
                        emailList.remove(p-1);

                        for (EmailPerson email : emailList){

                            if (email.getNumberSerial()>p-1){
                                email.setNumberSerial(email.getNumberSerial()-1);
                            }
                        }
                        System.out.println("Пользователь с порядковым номером " + p + " удален");
                        quitDeleteUser = true;

                    }catch (IndexOutOfBoundsException a){
                        System.out.println("Введите корректный порядковый номер");
                    }
                }
        } else {
                System.out.println("\nСписок пуст\n");
            }
    }

        //изменение объема почтового ящика
    public void changeCapacity(){
        if(!emailList.isEmpty()){
            Scanner scChange = new Scanner(System.in);

            System.out.println("Введите порядковый номер пользователя");
            int i = scChange.nextInt();
                if(i <= emailList.size()){
                    System.out.println("Введите желаемый размер почтового ящика");
                    int j = scChange.nextInt();
                    emailList.get(i-1).setMailboxCapacity(j);
                    System.out.println("Размер почтового ящика успешно изменен!\n");
                }else {
                    System.out.println("Введите правильный порядковый номер");
                }

        } else {
                System.out.println("\nСписок пуст\n");
        }
    }
}

