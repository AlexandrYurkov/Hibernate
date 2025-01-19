package ru.otus.YurkovAleksandr.DAO.intreface;

import ru.otus.YurkovAleksandr.entity.Buyer;

import java.util.List;

public interface BuyerDao {
    public void save(Buyer buyer);
    public void update(Buyer buyer);
    public void delete(Long id);
    public List<Buyer> listAll();
    public Buyer findById(Long id);

}
