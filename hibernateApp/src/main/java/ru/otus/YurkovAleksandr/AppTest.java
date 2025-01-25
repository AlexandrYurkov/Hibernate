package ru.otus.YurkovAleksandr;

import org.hibernate.Session;
import ru.otus.YurkovAleksandr.dataSource.DataSource;
import ru.otus.YurkovAleksandr.entity.Buyer;
import ru.otus.YurkovAleksandr.service.Service;


public class AppTest {
    public static void main(String[] args) {

        Service service = new Service();
        try {
            service.run();
            boolean result = true;
            while (result) {
                result = service.consoleMenu();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Service stopped");
    }
}
