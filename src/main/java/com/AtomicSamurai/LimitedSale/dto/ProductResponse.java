package com.AtomicSamurai.LimitedSale.dto;




import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;
    private LocalDateTime createdAt;

    public ProductResponse(){}

    public ProductResponse(Long id, String name, String description, BigDecimal price, Long stock, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
