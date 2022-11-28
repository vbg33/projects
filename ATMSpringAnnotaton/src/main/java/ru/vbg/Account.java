package ru.vbg;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;

@Component
public class Account {
    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    private int customerNumber;
    private int pinNumber;
    private double commonBalance;
    private double savingBalance;

    List<Double> commonDoubList = new ArrayList<>();
    List<Double> savingDoubList = new ArrayList<>();

    List<String> commonStrList = new LinkedList<>();
    List<String> savingStrList = new LinkedList<>();

    //задание логина
    public void setCustomerNumber(int customerNumber){
        this.customerNumber=customerNumber;
    }

    //задание пароля
    public void setPinNumber(int pinNumber){
        this.pinNumber = pinNumber;
    }

    //получение логина
    public int getCustomerNumber(){
        return customerNumber;
    }

    //получение пароля
    public int getPinNumber(){
        return pinNumber;
    }

    //баланс текущего счета
    public double getCommonBalance(){
        return commonBalance;
    }

    //баланс сберегательного счет
    public double getSavingBalance(){
        return savingBalance;
    }

    //расчет снятия с  екущего счета
    public double calcCommonWithdrawal(double amount){

        commonDoubList.add(amount);
        commonStrList.add("снято");

        commonBalance = commonBalance - amount;
        return commonBalance;
    }

    //расчет снятия со сберегательного счета
    public double calcSavingWithdrawal(double amount){

        savingDoubList.add(amount);
        savingStrList.add("снято");

        savingBalance = (savingBalance - amount);
        return savingBalance;
    }

    //расчет внесения на текущий счет
    public double calcCommonDeposit(double amount){

        commonDoubList.add(amount);
        commonStrList.add("внесено");

        commonBalance = (commonBalance+amount);
        return commonBalance;
    }

    //расчет внесения на сберегательный
    public double calcSavingDeposit(double amount){

        savingDoubList.add(amount);
        savingStrList.add("внесено");

        savingBalance = (savingBalance + amount);
        return savingBalance;
    }

    //снятие с текущего счета
    public void getCommonWithdrawalInpit(){

        System.out.println("Текущий счет: "+ moneyFormat.format(commonBalance));

        if (commonBalance > 0) {
            while (true) {
                try {
                    System.out.println("Введите сумму для снятия с текущего счета:");
                    input = new Scanner(System.in);
                    double amount = input.nextDouble();

                    if ((commonBalance - amount) >= 0) {
                        calcCommonWithdrawal(amount);
                        System.out.println("Текущий счет: " + moneyFormat.format(commonBalance));
                    } else {
                        System.out.println("Недостаточно средств на счете\n");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка!Введите число:");
                }
            }
        }else {
            System.out.println("Недостаточно средств на счете\n");
        }
    }

    //снятие со сберегательного счета
    public void getSavingWithdrawInput(){

        System.out.println("Сберегательный счет: " + moneyFormat.format(savingBalance));

        if(savingBalance > 0){
            while (true){

                try{
                    System.out.println("Введите сумму для снятия со сберегательного счета:");
                    input = new Scanner(System.in);
                    double amount = input.nextDouble();

                    if(savingBalance - amount >= 0){
                        calcSavingWithdrawal(amount);
                        System.out.println("Баланс сберегательного счета " + savingBalance+ "\n");
                        break;
                    }
                    else {
                        System.out.println("Недостаточно средств на сберегательном счете\n");
                        break;
                    }
                }catch (IllegalArgumentException e){
                    System.out.println("Ошибка!Введите число:");
                }
            }
        }else {
            System.out.println("Недостаточно средств на счете\n");
        }
    }


    //внесение на текущий счет
    public void getCommonDepositInput(){

        System.out.println("Баланс текущего счета: "+ moneyFormat.format(commonBalance));
        System.out.println("Введите сумму для внесения: ");

        while (true){
            try {
                double amount = input.nextDouble();
                calcCommonDeposit(amount);
                System.out.println("Баланс текущего счета: "+ moneyFormat.format(commonBalance) +"\n");
                break;

            }catch (InputMismatchException e){
                System.out.println("Ошибка!Введите число:");
            }
        }
    }

    //внесение на сберегательный счет
    public void getSavingDepositInput(){
        System.out.println("Баланс сберегательного счета: "+ moneyFormat.format(savingBalance));
        System.out.println("Введите сумму для внесения: ");

        while (true){
            try {
                double amount = input.nextDouble();
                calcSavingDeposit(amount);
                System.out.println("Сберегательный счет: " + moneyFormat.format(savingBalance) + "\n");
                break;

            }catch (InputMismatchException e){
                System.out.println("Ошибка!Введите число:");
            }
        }
    }

    //получение списка операций текущего счета
    public void getCommonList(){

        if(!commonDoubList.isEmpty()){
            System.out.println("-----------");
            for (int i = 0;i < commonDoubList.size();i++){
                System.out.println(commonStrList.get(i) + " " + commonDoubList.get(i));
            }
            System.out.println("-----------");
        }else {
            System.out.println("\nОперации с текущим счетом не производилсь\n");
        }
    }

    //получение списка операций сберегательного счета
    public void getSavingList(){

        if(!savingDoubList.isEmpty()){
            System.out.println("-----------");
            for (int i = 0;i < savingDoubList.size();i++){
                System.out.println(savingStrList.get(i) + " " + savingDoubList.get(i));
            }
            System.out.println("-----------");
        }else {
            System.out.println("\nОперации со сберегательным счетом не производились\n");
        }
    }

}
