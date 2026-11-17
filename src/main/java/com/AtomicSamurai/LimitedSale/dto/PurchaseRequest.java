package com.AtomicSamurai.LimitedSale.dto;

import jakarta.validation.constraints.*;

public class PurchaseRequest {
    @Positive
    @NotNull
    @Max(value = 5, message = "Quantity cannot exceed 5")
    private Long quantity;

    public PurchaseRequest(){}

    public PurchaseRequest(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
