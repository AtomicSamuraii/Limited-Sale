package com.AtomicSamurai.LimitedSale.Controller;


import com.AtomicSamurai.LimitedSale.Service.OrderService;
import com.AtomicSamurai.LimitedSale.dto.PurchaseRequest;
import com.AtomicSamurai.LimitedSale.dto.PurchaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flash-sales")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{flashSaleId}/purchase")
    public ResponseEntity<PurchaseResponse> createOrder(@PathVariable Long flashSaleId, @RequestBody @Valid PurchaseRequest request){
        PurchaseResponse response = orderService.purchaseProduct(request,flashSaleId);
        return ResponseEntity.ok(response);
    }
}
