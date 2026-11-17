package com.AtomicSamurai.LimitedSale.Repository;

import com.AtomicSamurai.LimitedSale.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
