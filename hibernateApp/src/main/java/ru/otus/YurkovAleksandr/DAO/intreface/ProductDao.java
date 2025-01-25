package ru.otus.YurkovAleksandr.DAO.intreface;

import ru.otus.YurkovAleksandr.entity.Buyer;
import ru.otus.YurkovAleksandr.entity.Product;

import java.util.List;

public interface ProductDao {
    public void save(Product product);
    public void update(Product product);
    public void delete(Long id);
    public List<Product> listAll();
    public Product findById(Long id);
}
