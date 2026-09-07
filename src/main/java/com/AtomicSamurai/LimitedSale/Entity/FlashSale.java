package com.AtomicSamurai.LimitedSale.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class FlashSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    @Positive
    private BigDecimal salePrice;

    @Column(nullable = false)
    @PositiveOrZero
    private Long saleStock;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime saleStartTime;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime saleEndTime;

    public FlashSale(){}

    public FlashSale(Long id, Product product, BigDecimal salePrice, Long saleStock, LocalDateTime saleStartTime, LocalDateTime saleEndTime) {
        this.id = id;
        this.product = product;
        this.salePrice = salePrice;
        this.saleStock = saleStock;
        this.saleStartTime = saleStartTime;
        this.saleEndTime = saleEndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Long getSaleStock() {
        return saleStock;
    }

    public void setSaleStock(Long saleStock) {
        this.saleStock = saleStock;
    }

    public LocalDateTime getSaleStartTime() {
        return saleStartTime;
    }

    public void setSaleStartTime(LocalDateTime saleStartTime) {
        this.saleStartTime = saleStartTime;
    }

    public LocalDateTime getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(LocalDateTime saleEndTime) {
        this.saleEndTime = saleEndTime;
    }
}
