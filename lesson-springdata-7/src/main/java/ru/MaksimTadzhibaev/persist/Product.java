package ru.MaksimTadzhibaev.persist;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private Long id;

    @NotBlank
    @Column(name = "titleProduct", nullable = false)
    private String title;

    @Column(name = "costProduct", nullable = false)
    private Double cost;

    public Product(String name, Double cost) {
        this.title = name;
        this.cost = cost;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
