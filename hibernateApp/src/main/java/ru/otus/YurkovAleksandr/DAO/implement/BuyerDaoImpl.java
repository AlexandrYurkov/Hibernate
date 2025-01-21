package ru.otus.YurkovAleksandr.DAO.implement;

import ru.otus.YurkovAleksandr.DAO.intreface.BuyerDao;
import ru.otus.YurkovAleksandr.dataSource.DataSource;
import ru.otus.YurkovAleksandr.entity.Buyer;

import java.util.List;

public class BuyerDaoImpl implements BuyerDao {
    private DataSource dataSource = new DataSource();
    @Override
    public void save(Buyer buyer) {
        dataSource.getSession().beginTransaction();
        dataSource.getSession().save(buyer);
        dataSource.getSession().getTransaction().commit();
    }

    @Override
    public void update(Buyer buyer) {
        dataSource.getSession().beginTransaction();
        Buyer result = dataSource.getSession().get(Buyer.class, buyer.getId());
        System.out.println("result : " + result);
        result.setName(buyer.getName());
        dataSource.getSession().update(result);
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
    }

    @Override
    public void delete(Long id) {
        dataSource.getSession().beginTransaction();
        Buyer buyer = dataSource.getSession().get(Buyer.class, id);
        dataSource.getSession().delete(buyer);
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
    }

    @Override
    public List<Buyer> listAll() {
        dataSource.getSession().beginTransaction();
        List<Buyer> buyers = dataSource.getSession().createQuery("from Buyer").getResultList();
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
        return buyers;
    }

    @Override
    public Buyer findById(Long id) {
        dataSource.getSession().beginTransaction();
        Buyer buyer = dataSource.getSession().get(Buyer.class, id);
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
        return buyer;
    }
}
