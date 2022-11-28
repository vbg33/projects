package ru.emailapp;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class EmailAppMenu extends EmailApp {


    public void start(){
        while (true){
            menu();
            System.out.print("Действие: ");
            go();
        }
    }

    public void menu(){
        System.out.println("Выберете действие:\n" +
                "Нажмите 1 - для добавления сотрудника\n" +
                "Нажмите 2 - для показа всех сотрудников\n" +
                "Нажмите 3 - для удаления сотрудника из списка по порядковому номеру\n" +
                "Нажмите 4 - для очистки списка\n" +
                "Нажмите 5 - для изменения объема почты пользователя\n" +
                "Нажмите 0 - для выхода из программы");
    }

    public void go(){
        boolean quit = false;
        //       while (!quit){
        Scanner in = new Scanner(System.in);
        try{
            int str = in.nextInt();
            switch (str){

                //выход из программы
                case 0:
                    System.out.println("Выход из программы");
                    System.exit(0);

                    //добавление нового сотрудника
                case 1:
                    addUser();
                    break;

                //вывод всех сотрудников
                case 2:
                    showAllUsers();
                    break;

                //удаление пользователя по порядковому номеру
                case 3:
                    deleteUser();
                    break;

                //очистка списка
                case 4:
                    clearList();
                    break;

                //изменение размера почты
                case 5:
                    changeCapacity();
                    break;

                default:
                    System.out.print("Введено неверное значение, " +
                            "выберете из предложенных:\n");

            }
        }catch (InputMismatchException ex){
            System.out.print("Введено не число!Введите значение их представленных: \n ");
        }
    }
}

