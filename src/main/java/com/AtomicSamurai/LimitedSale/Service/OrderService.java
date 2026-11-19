package com.AtomicSamurai.LimitedSale.Service;




import com.AtomicSamurai.LimitedSale.Entity.FlashSale;
import com.AtomicSamurai.LimitedSale.Entity.Order;
import com.AtomicSamurai.LimitedSale.Entity.OrderStatus;
import com.AtomicSamurai.LimitedSale.Entity.User;
import com.AtomicSamurai.LimitedSale.Repository.FlashSaleRepository;
import com.AtomicSamurai.LimitedSale.Repository.OrderRepository;
import com.AtomicSamurai.LimitedSale.Repository.UserRepository;
import com.AtomicSamurai.LimitedSale.dto.PurchaseRequest;
import com.AtomicSamurai.LimitedSale.dto.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FlashSaleRepository flashSaleRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, FlashSaleRepository flashSaleRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.flashSaleRepository = flashSaleRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public PurchaseResponse purchaseProduct(PurchaseRequest request, Long flashSaleId){


        //find the flash sale by id
        FlashSale flashSale = flashSaleRepository.findById(flashSaleId).orElseThrow(() -> new EntityNotFoundException("Sale not Found!"));

        LocalDateTime currentTime = LocalDateTime.now();

        Long quantity = request.getQuantity();

        int rowsUpdated = flashSaleRepository.deductStockIfAvailable(flashSaleId, quantity, currentTime);

        if (rowsUpdated == 0) {
            throw new IllegalArgumentException(
                    "Flash sale is inactive or insufficient stock available."
            );
        }

        BigDecimal unitPrice = flashSale.getSalePrice();
        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));

        //retrieve the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user =  userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not Found!"));

        //creating the order (inserting to db)
        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setFlashSale(flashSale);
        newOrder.setQuantity(quantity);
        newOrder.setUnitPrice(unitPrice);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setStatus(OrderStatus.CONFIRMED);

        orderRepository.save(newOrder);
        return generateResponse(newOrder);

    }

    private PurchaseResponse generateResponse(Order order){
        return new PurchaseResponse(order.getId(), order.getFlashSale().getId(), order.getQuantity(),order.getUnitPrice(),order.getTotalPrice(),order.getStatus(),order.getCreatedAt());
    }


}
