package vebProektEshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private String image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    private Integer likes;


    public Product() {
    }

    public Product(String name, Double price, Integer quantity,String image, Category category, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image=image;
        this.category = category;
        this.manufacturer = manufacturer;
    }
}
