package ru.otus.YurkovAleksandr.dataSource;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.otus.YurkovAleksandr.entity.Buy;
import ru.otus.YurkovAleksandr.entity.BuyKey;
import ru.otus.YurkovAleksandr.entity.Buyer;
import ru.otus.YurkovAleksandr.entity.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Data
public class DataSource {
    private static SessionFactory sessionFactory;
    private Session session = null;

    public void init() throws IOException {
        getSessionFactory();
        String sql = Files.lines(Paths.get("script.sql")).collect(Collectors.joining(" "));
//        System.out.println(sql);
        Session s = getSession();
        s.beginTransaction();
        s.createNativeQuery(sql).executeUpdate();
        s.getTransaction().commit();
        System.out.println("Done");
    }

    private void getSessionFactory() {
        if(sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Buyer.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(BuyKey.class)
                    .addAnnotatedClass(Buy.class)
                    .buildSessionFactory();
        }
    }

    public Session getSession() {
        if (session == null) {
            session = sessionFactory.getCurrentSession();
        }
        return session;
    }

    public void sessionClose() {
        if (session != null) {
            session.close();
        }
    }

    public void close() {
        session.close();
        sessionFactory.close();
    }

}
