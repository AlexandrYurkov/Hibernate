package ru.otus.YurkovAleksandr.DAO.implement;

import ru.otus.YurkovAleksandr.DAO.intreface.ProductDao;
import ru.otus.YurkovAleksandr.dataSource.DataSource;
import ru.otus.YurkovAleksandr.entity.Product;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private DataSource dataSource = new DataSource();
    @Override
    public void save(Product product) {
        dataSource.getSession().beginTransaction();
        dataSource.getSession().save(product);
        dataSource.getSession().getTransaction().commit();
    }

    @Override
    public void update(Product product) {
        dataSource.getSession().beginTransaction();
        Product result = dataSource.getSession().get(Product.class, product.getId());
        result.setTitle(product.getTitle());
        result.setPrice(product.getPrice());
        dataSource.getSession().update(result);
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
    }

    @Override
    public void delete(Long id) {
        dataSource.getSession().beginTransaction();
        Product product = dataSource.getSession().get(Product.class, id);
        dataSource.getSession().delete(product);
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
    }

    @Override
    public List<Product> listAll() {
        dataSource.getSession().beginTransaction();
        List products = dataSource.getSession().createQuery("from Product").getResultList();
        dataSource.getSession().getTransaction().commit();
        dataSource.sessionClose();
        return products;
    }

    @Override
    public Product findById(Long id) {
        dataSource.getSession().beginTransaction();
        Product product = dataSource.getSession().get(Product.class, id);
        System.out.println("product : " + product);
        dataSource.getSession().getTransaction().commit();
        return product;
    }
}
