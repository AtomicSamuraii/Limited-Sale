package com.AtomicSamurai.LimitedSale.Repository;

import com.AtomicSamurai.LimitedSale.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
