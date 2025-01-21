package ru.otus.YurkovAleksandr.service;

import lombok.Data;

import ru.otus.YurkovAleksandr.DAO.implement.*;
import ru.otus.YurkovAleksandr.DAO.intreface.*;
import ru.otus.YurkovAleksandr.dataSource.DataSource;
import ru.otus.YurkovAleksandr.entity.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Data
public class Service {
    private DataSource dataSource;

    public void run() throws IOException {
        dataSource = new DataSource();
        dataSource.init();
        System.out.println("Server running...");
    }

    private void buyerMenu(){
        System.out.println(
                "1) Создать покупателя \n" +
                        "2) Обновить данные пользователя \n" +
                        "3) Получить список всех пользователей \n" +
                        "4) Удалить пользователя \n" +
                        "** введите BACK для возврата в предыдущее меню **");
        int in = new Scanner(System.in).nextInt();
        switch (in){
            case 1:{
                System.out.println("Введите имя нового покупателя:");
                String name = new Scanner(System.in).nextLine();
                if(!name.isEmpty()) {
                    Buyer newUser = new Buyer();
                    newUser.setName(name);
                    BuyerDao connect = new BuyerDaoImpl();
                    connect.save(newUser);
                }
                break;
            } case 2:{
                System.out.println("Введите id покупателя:");
                Long id = new Scanner(System.in).nextLong();
                System.out.println("Введите новое имя покупателя:");
                String newName = new Scanner(System.in).nextLine();
                BuyerDao connect = new BuyerDaoImpl();
                Buyer b = new Buyer();
                b.setId(id);
                b.setName(newName);
                connect.update(b);
                break;
            } case 3:{
                BuyerDao connect = new BuyerDaoImpl();
                List<Buyer> all = connect.listAll();
                for (Buyer buyer : all) {
                    System.out.println(buyer);
                }
                break;
            } case 4:{
                System.out.println("Введите id покупателя:");
                Long id = new Scanner(System.in).nextLong();
                BuyerDao connect = new BuyerDaoImpl();
                connect.delete(id);
                break;
            }
            default: {
                if(dataSource.getSession() != null){
                    dataSource.sessionClose();
                }
                break;
            }
        }

    }
    private void productMenu(){
        System.out.println(
                "1) Создать продукт \n" +
                        "2) Обновить данные продукта \n" +
                        "3) Получить список всех продуктов \n" +
                        "4) Удалить продукт \n" +
                        "** введите BACK для возврата в предыдущее меню **");
        int in = new Scanner(System.in).nextInt();
        switch (in){
            case 1:{
                System.out.println("Введите название нового продукта:");
                String name = new Scanner(System.in).nextLine();
                System.out.println("Введите цену продукта:");
                int price = new Scanner(System.in).nextInt();
                Product newProduct = new Product();
                newProduct.setTitle(name);
                newProduct.setPrice(price);
                ProductDao connect = new ProductDaoImpl();
                connect.save(newProduct);
                break;
            }
            case 2:{
                System.out.println("Введите id продукта:");
                Long id = new Scanner(System.in).nextLong();
                System.out.println("Введите новое имя покупателя:");
                String newName = new Scanner(System.in).nextLine();
                System.out.println("Введите цену продукта:");
                int price = new Scanner(System.in).nextInt();
                Product newProduct = new Product();
                newProduct.setId(id);
                newProduct.setTitle(newName);
                newProduct.setPrice(price);
                ProductDao connect = new ProductDaoImpl();
                connect.update(newProduct);
                break;
            }
            case 3:{
                ProductDao connect = new ProductDaoImpl();
                List<Product> allProduct = connect.listAll();
                for (Product product : allProduct) {
                    System.out.println(product);
                }
                break;
            }
            case 4:{
                System.out.println("Введите id продукта:");
                Long id = new Scanner(System.in).nextLong();
                ProductDao connect = new ProductDaoImpl();
                connect.delete(id);
                break;
            }
            default:{
                if(dataSource.getSession() != null){
                    dataSource.sessionClose();
                }
                break;
            }
        }

    }
    private void buyMenu(){
        System.out.println(
                "1) Создать покупку \n" +
                        "2) Получить покупки покупателя \n" +
                        "3) Получить купленных продуктов \n" +
                        "4) Получить все покупки \n" +
                        "5) Удалить покупку \n" +
                        "** введите BACK для возврата в предыдущее меню **");
        int in = new Scanner(System.in).nextInt();
        switch (in){
            case 1:{
                System.out.println("Введите id покупателя:");
                Buyer buyer = new BuyerDaoImpl()
                        .findById(new Scanner(System.in).nextLong());
                System.out.println("Введите id продукта:");
                Product product = new ProductDaoImpl()
                        .findById(new Scanner(System.in).nextLong());
                BuyDao connect = new BuyDaoImpl();
                connect.save(buyer, product);
                break;
            }
            case 2:{
                System.out.println("Введите id покупателя:");
                Buyer baeyr = new BuyerDaoImpl()
                        .findById(new Scanner(System.in).nextLong());
                List<Buy> buys = baeyr.getBuys();
                for (Buy buy : buys) {
                    System.out.println(buy);
                }
                break;
            }
            case 3:{
                System.out.println("Введите id продукта:");
                Product product = new ProductDaoImpl()
                        .findById(new Scanner(System.in).nextLong());
                List<Buy> buys = product.getBuys();
                for (Buy buy : buys) {
                    System.out.println(buy);
                }
                break;
            }
            case 4: {
                BuyDao connect = new BuyDaoImpl();
                connect.listAll();
                break;
            }
            case 5: {
                System.out.println("Введите id покупателя:");
                Buyer buyer = new BuyerDaoImpl()
                        .findById(new Scanner(System.in).nextLong());
                System.out.println("Введите id продукта:");
                Product product = new ProductDaoImpl()
                        .findById(new Scanner(System.in).nextLong());
                BuyDao connect = new BuyDaoImpl();
                connect.delete(buyer, product);
                break;
            }
            default:break;
        }
    }

    public boolean consoleMenu(){
        System.out.println(
                "1) Покупатель \n" +
                        "2) Продукт \n" +
                        "3) Покупка \n" +
                        "** Для завершения программы введите 0 **");
        int inputDate = new Scanner(System.in).nextInt();
        switch (inputDate){
            case 1: {
                buyerMenu();
                break;
            }
            case 2: {
                productMenu();
                break;
            }
            case 3: {
                buyMenu();
                break;
            }
            case 0: {
                dataSource.close();
                return false;
            }
            default: {
                break;
            }
        }
        return true;
    }

}