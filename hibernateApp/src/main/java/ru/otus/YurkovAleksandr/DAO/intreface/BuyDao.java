package ru.otus.YurkovAleksandr.DAO.intreface;

import ru.otus.YurkovAleksandr.entity.Buyer;
import ru.otus.YurkovAleksandr.entity.Product;

public interface BuyDao {
    public void save(Buyer buyer, Product product);
    public void delete(Buyer buyer, Product product);
    public void listAll();
}
