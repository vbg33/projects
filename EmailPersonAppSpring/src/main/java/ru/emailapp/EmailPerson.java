package ru.emailapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmailPerson implements Info {
    static int number = 0;
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;
    private int numberSerial;
    private int mailboxCapacity = 500;
    private int defaultPasswordLength = 8;
    private String alternateEmail;
    private String companySuffix = "PROJECTcompany.com";

    //констурктор
    public EmailPerson(){
        //порядковый номер
        number++;
        this.numberSerial = number;

        //ввод имени
        this.firstName = inputFirstName();;

        //ввод фамилии
        this.lastName = inputLastName();

        //выбор отдела
        this.department = setDepartment();
        System.out.println("\nОтдел: "+ this.department);

        System.out.println("Имя: " + this.firstName);
        System.out.println("Фамилия: " + this.lastName);

        //создание e-mail
        email = createEmail(firstName,lastName,setDepartmentEMAIL(department),companySuffix);
        System.out.println("Ваш E-Mail: "+ email);

        //генерация пароля
        this.password = randomPasword(defaultPasswordLength);
        System.out.println("Ваш пароль: "+ this.password);

    }
    //порядковый номер
    public int getNumberSerial() {
        return numberSerial;
    }

    public void setNumberSerial(int numberSerial){
        this.numberSerial = numberSerial;
    }

    public void minusSerialNumber(){
        number = number - 1;
    }

    //ввод имени
    public String inputFirstName(){
        System.out.println("Введите имя работника:");
        Scanner sc = new Scanner(System.in);
        String str_firstName =sc.nextLine();

        return str_firstName;
    }

    //ввод фамилии
    public String inputLastName(){
        System.out.println("Введите фамилию работника:");
        Scanner sc = new Scanner(System.in);
        String str_lastName =sc.nextLine();

        return str_lastName;
    }

    //создание e-mail
    public String createEmail(String firstName,String lastName,
                              String department,String company){
        String emailCreated = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@"+
                department+"."+company;
        return emailCreated;
    }


    //выбор отдела
    private String setDepartment(){
        String choice = null;

        System.out.println("Выберете отдел:\n1 Продажи\n2 Разработка"
                +"\n3 Авторизация\n0 Назад");
        System.out.print("Номер отдела: ");

        while (true){
            try {
                Scanner sc = new Scanner(System.in);
                int depChoice = sc.nextInt();

                if(depChoice>=0 && depChoice<=3){
                    choice = choiceDepartment(depChoice);
                    break;
                }else {
                    System.out.println("Введите правильное значение");
                }
            } catch (InputMismatchException e){
                System.out.println("Введено не число, введите число: ");
            }
        }
        return choice;
    }

    private String choiceDepartment(int inCoice){
        String dep = null;
        boolean quit = false;

        while(!quit){

            try {

                int choice = inCoice;

                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        dep = "Продажи";
                        quit = true;
                        break;

                    case 2:
                        dep = "Разработка";
                        quit = true;
                        break;

                    case 3:
                        dep = "Автоматизация";
                        quit = true;
                        break;

                    default:
                        System.out.println("Введено неверное значение");
                }
                break;

            } catch (InputMismatchException ex){
                System.out.println("Введите корректное значение");
            }
        }
        return dep;
    }


    //добавление к email'у суффикса отдела
    private String setDepartmentEMAIL(String department){
        String depEmail = null;

        if (department.equals("Продажи"))
            depEmail = "sales";

        else if(department.equals("Разработка"))
            depEmail = "dev";

        else if (department.equals("Автоматизация"))
            depEmail = "automation";

        return depEmail;
    }


    //создание случайного пароля
    private String randomPasword(int length){

        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] password = new char[length];

        for(int i = 0; i<length; i++){
           int rand =  (int) (Math.random() * passwordSet.length());
           password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    //установка размера почты
    public void setMailboxCapacity(int capacity){
        this.mailboxCapacity = capacity;
    }


    //информация о пользователе
    @Override
    public String showInfo(){

        return  "\nИнформация о пользователе:\n" +
                "Порядковый номер: " + numberSerial+ "\n" +
                "Имя: " + firstName + " " + lastName+"\n" +
                "Отдел: " + department + "\n" +
                "E-Mail: " + email + "\n" +
                "Размер почтового ящика "+ mailboxCapacity + " mb";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null && getClass()!=obj.getClass() ) return false;
        EmailPerson emailPerson = (EmailPerson) obj;
        return firstName.equals(emailPerson.firstName) && lastName.equals(emailPerson.lastName)
                && department.equals(emailPerson.department);
    }

    @Override
    public int hashCode(){
        int result1 = this.firstName.hashCode();
        int result2 = this.lastName.hashCode();
        int result3 = this.department.hashCode();
        return 31 * (result1 + result2 + result3);
    }
}


