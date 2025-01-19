package ru.otus.YurkovAleksandr.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "buy")
public class Buy {
    @EmbeddedId
    private BuyKey key;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "buyer_id", insertable = false, updatable = false)
    private Buyer buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "price", nullable = false)
    private int price;

    public Buy(BuyKey key, Buyer buyer, Product product) {
        this.key = key;
        this.buyer = buyer;
        this.product = product;
        this.price = product.getPrice();
    }
    public Buy() {}

    @Override
    public String toString() {
        return "BuyDao{" +
                "key=" + key +
                ", buyer=" + buyer.getName() +
                ", product=" + product.getTitle() +
                ", price=" + price +
                '}';
    }
}
