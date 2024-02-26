package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entity.concretes.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PurchasedProductHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productHistoryId;

    @ManyToOne //Bunu değiştirdim önce manytomany idi.
    @JoinColumn(name = "user_id")
    private User customer;

    @ManyToMany
    @JoinTable(name = "Purchased_Product",
    joinColumns = @JoinColumn(name = "product_history_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> allProduct;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDate transactionDate;
}
