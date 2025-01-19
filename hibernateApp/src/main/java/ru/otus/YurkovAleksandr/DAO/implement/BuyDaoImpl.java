package ru.otus.YurkovAleksandr.DAO.implement;

import ru.otus.YurkovAleksandr.DAO.intreface.BuyDao;
import ru.otus.YurkovAleksandr.dataSource.DataSource;
import ru.otus.YurkovAleksandr.entity.*;

public class BuyDaoImpl implements BuyDao {
    DataSource dataSource = new DataSource();

    private Buy createBuy(Buyer buyer, Product product){
        BuyKey key = new BuyKey();
        key.setBuyerId(buyer.getId());
        key.setProductId(product.getId());
        return new Buy(key, buyer, product);
    }
    @Override
    public void save(Buyer buyer, Product product) {
        Buy buy = createBuy(buyer, product);
        buyer.getBuys().add(buy);
        product.getBuys().add(buy);
        dataSource.getSession().beginTransaction();
        dataSource.getSession().save(buy);
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
        System.out.println(buy);

    }

    @Override
    public void delete(Buyer buyer, Product product) {
        Buy buy = createBuy(buyer, product);
        buyer.getBuys().remove(buy);
        product.getBuys().remove(buy);
        dataSource.getSession().beginTransaction();
        dataSource.getSession().delete(buy);
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
        System.out.println(buy);
    }

    @Override
    public void listAll() {
        dataSource.getSession().beginTransaction();
        dataSource.getSession().createQuery("from Buy").list().forEach(System.out::println);
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
    }
}
