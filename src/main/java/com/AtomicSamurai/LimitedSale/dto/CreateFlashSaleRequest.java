package com.AtomicSamurai.LimitedSale.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateFlashSaleRequest {
    @NotNull
    private Long productId;

    @NotNull
    @Positive
    private BigDecimal salePrice;

    @NotNull
    @PositiveOrZero
    private Long saleStock;

    @NotNull
    private LocalDateTime saleStartTime;

    @NotNull
    private LocalDateTime saleEndTime;

    public CreateFlashSaleRequest() {
    }

    public CreateFlashSaleRequest(Long productId, BigDecimal salePrice, Long saleStock, LocalDateTime saleStartTime, LocalDateTime saleEndTime) {
        this.productId = productId;
        this.salePrice = salePrice;
        this.saleStock = saleStock;
        this.saleStartTime = saleStartTime;
        this.saleEndTime = saleEndTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
