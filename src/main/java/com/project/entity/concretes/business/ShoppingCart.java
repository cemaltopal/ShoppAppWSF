package com.project.entity.concretes.business;

import com.project.entity.concretes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User customer;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.REMOVE)
    private List<Product> productList;

    private boolean isPaid;
}
