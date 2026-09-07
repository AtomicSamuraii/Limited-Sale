package com.AtomicSamurai.LimitedSale.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FlashSaleResponse {
    private Long id;
    private Long productId;
    private BigDecimal salePrice;
    private Long saleStock;
    private LocalDateTime saleStartTime;
    private LocalDateTime saleEndTime;

    public FlashSaleResponse(Long id, Long productId, BigDecimal salePrice, Long saleStock, LocalDateTime saleStartTime, LocalDateTime saleEndTime) {
        this.id = id;
        this.productId = productId;
        this.salePrice = salePrice;
        this.saleStock = saleStock;
        this.saleStartTime = saleStartTime;
        this.saleEndTime = saleEndTime;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public Long getSaleStock() {
        return saleStock;
    }

    public LocalDateTime getSaleStartTime() {
        return saleStartTime;
    }

    public LocalDateTime getSaleEndTime() {
        return saleEndTime;
    }
}
