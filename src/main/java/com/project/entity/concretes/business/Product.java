package com.project.entity.concretes.business;

import com.project.entity.enums.CategoryNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotNull(message = "Product Name must not be empty")
    private String productName;
    @NotNull(message = "Price Name must not be empty")

    private Double price;

    private int stockAmount;

    private boolean discount;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    @NotNull(message = "categoryNames must not be empty")
    private CategoryNames categoryNames;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToMany(mappedBy = "allProduct")
    private List<PurchasedProductHistory> purchasedProductHistories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductInfo> productInfoList;
}
