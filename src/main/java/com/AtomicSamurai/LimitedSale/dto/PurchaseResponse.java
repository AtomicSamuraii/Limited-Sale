package com.AtomicSamurai.LimitedSale.dto;

import com.AtomicSamurai.LimitedSale.Entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PurchaseResponse {
    private Long orderId;
    private Long flashSaleId;
    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public PurchaseResponse() {
    }

    public PurchaseResponse(Long orderId, Long flashSaleId, Long quantity, BigDecimal unitPrice, BigDecimal totalPrice, OrderStatus status, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.flashSaleId = flashSaleId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFlashSaleId() {
        return flashSaleId;
    }

    public void setFlashSaleId(Long flashSaleId) {
        this.flashSaleId = flashSaleId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
