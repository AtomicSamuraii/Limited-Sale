package com.AtomicSamurai.LimitedSale.Repository;


import com.AtomicSamurai.LimitedSale.Entity.FlashSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlashSaleRepository extends JpaRepository<FlashSale,Long> {

    @Query(""" 
            select count(fs) > 0
            from FlashSale fs
            where fs.product.id = :productId
                and fs.saleEndTime > :newStartTime
                and fs.saleStartTime < :newEndTime
    """)
    boolean existsOverlappingSale(@Param("productId") Long productId, @Param("newStartTime") LocalDateTime newStartTime, @Param("newEndTime") LocalDateTime newEndTime);

    Optional<FlashSale> findById(Long id);



    @Modifying(flushAutomatically = true)
    @Query("""
        UPDATE FlashSale fs
        SET fs.saleStock = fs.saleStock - :quantity
        WHERE fs.id = :flashSaleId
        AND fs.saleStock >= :quantity
        AND fs.saleStartTime <= :now
        AND fs.saleEndTime > :now
    """)
    int deductStockIfAvailable(
            @Param("flashSaleId") Long flashSaleId,
            @Param("quantity") Long quantity,
            @Param("now") LocalDateTime now
    );
}
