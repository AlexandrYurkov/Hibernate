package ru.otus.YurkovAleksandr.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Embeddable
public class BuyKey implements Serializable {
    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "product_id")
    private Long  productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuyKey)) return false;
        BuyKey buyKey = (BuyKey) o;
        return Objects.equals(buyerId, buyKey.buyerId) && Objects.equals(productId, buyKey.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerId, productId);
    }
}
