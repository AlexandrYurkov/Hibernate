package ru.otus.YurkovAleksandr.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column (name = "title")
    private String title;
    @Column(name = "price")
    private int price;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Buy> buys = new ArrayList<>();

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
