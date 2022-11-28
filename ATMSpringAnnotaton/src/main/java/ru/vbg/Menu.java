package ru.vbg;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

@Component
public class Menu extends Account {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    //hashmap для хранения логин пароля
    HashMap<Integer,Integer> data = new HashMap<>();

    //вход
    public void getLogin() throws IOException {
        int x= 1;
        boolean a = false;

        //задание логин и пароль в hashmap
        data.put(123,123);

        //инициализация
        do{
            while (!a){
                try {
                    System.out.println("Введите логин:");
                    menuInput = new Scanner(System.in);

                    int num = menuInput.nextInt();
                    setCustomerNumber(num);
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Ошибка!Логин является числом!\n");

                }
            }

            while (!a){
                try {
                    System.out.println("Введите пароль:");
                    menuInput = new Scanner(System.in);

                    setPinNumber(menuInput.nextInt());
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Ошибка!Пароль является числом!\n");
                }
            }

            //проверка логина пароля
            for(Map.Entry<Integer,Integer> entry : data.entrySet()){
                if (entry.getKey()==getCustomerNumber() && entry.getValue()==
                        getPinNumber()){
                    System.out.println("Инициализация пройдена!");
                    getAccountType();
                }else {
                    System.out.println("Неправильный логин или пароль");
                }
            }

        }while (x==1);
    }

    int selection;

    //выбор типа аккаунта
    public void getAccountType(){
        System.out.println("1.Текущий счет \n" +
                "2.Сберегательный счет \n" +
                "3.Выход ");
        System.out.println("Выберете действие из представленных: ");


        while (true){
            try {
                menuInput = new Scanner(System.in);
                selection = menuInput.nextInt();
                break;
            }catch (InputMismatchException e ){
                System.out.println("Ошибка!Введите число соответствующее нужному действию:");
            }
        }

        switch (selection){
            case 1:
                getCommon();
                break;

            case 2:
                getSaving();
                break;
            case 3:
                System.out.println("Спасибо за визит!\nДо свидания");
                System.exit(0);
                break;
            default:
                System.out.println("Введите необходимое число");
                getAccountType();
        }
    }

    //Действия текущего счета
    public void getCommon(){
        System.out.println("Текущий счет\n"+
                "1. Показать доступный баланс\n" +
                "2. Снять сумму \n" +
                "3. Внести сумму \n" +
                "4. Показать операции\n" +
                "5. Выход ");
        System.out.println("Выберете действие из представленных: ");

        while (true){
            try {
                menuInput = new Scanner(System.in);
                selection = menuInput.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Ошибка!Введите число соответствующее нужному действию:");
            }
        }

        switch (selection){
            case 1:
                System.out.println("Баланс текущего счета: " + moneyFormat.format(getCommonBalance()) +" \n");
                getAccountType();
                break;

            case 2:
                getCommonWithdrawalInpit();
                getAccountType();
                break;
            case 3:
                getCommonDepositInput();
                getAccountType();
            case 4:
                getCommonList();
                getAccountType();
                break;
            case 5:
                System.out.println("Спасибо за визит!\nДо свидания");
                System.exit(0);
                break;
            default:
                System.out.println("Введите необходимое число");
                getCommon();
        }
    }

    //Действия сберегательного счета
    public void getSaving(){
        System.out.println("Сберегательный счет\n"+
                "1. Показать доступный баланс\n" +
                "2. Снять сумму \n" +
                "3. Внести сумму \n" +
                "4. Показать операции\n" +
                "5. Выход ");
        System.out.println("Выберете действие из представленных: ");

        while (true){
            try {
                menuInput = new Scanner(System.in);
                selection = menuInput.nextInt();
                break;
            }catch (Exception e ){
                System.out.println("Ошибка!Введите число соответствующее нужному действию:");
            }
        }

        switch (selection){
            case 1:
                System.out.println("Баланс сберегательного счета: " + moneyFormat.format(getSavingBalance()) + "\n");
                getAccountType();
                break;
            case 2:
                getSavingWithdrawInput();
                getAccountType();
                break;
            case 3:
                getSavingDepositInput();
                getAccountType();
            case 4:
                getSavingList();
                getAccountType();
                break;
            case 5:
                System.out.println("Спасибо за визит!\nДо свидания");
                System.exit(0);
                break;
            default:
                System.out.println("Введите необходимое число");
                getCommon();
        }

    }
}
