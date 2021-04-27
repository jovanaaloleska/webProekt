package vebProektEshop.model;


import lombok.Data;
import vebProektEshop.model.enumerations.WishListStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private WishListStatus status;

    public WishList() {
    }

    public WishList(User user) {
        this.user = user;
        this.products = new ArrayList<>();
        this.status = WishListStatus.CREATED;
    }
}
