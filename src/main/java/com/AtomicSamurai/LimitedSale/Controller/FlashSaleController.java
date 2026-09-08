package com.AtomicSamurai.LimitedSale.Controller;

import com.AtomicSamurai.LimitedSale.Service.FlashSaleService;
import com.AtomicSamurai.LimitedSale.dto.CreateFlashSaleRequest;
import com.AtomicSamurai.LimitedSale.dto.FlashSaleResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flash-sales")
public class FlashSaleController {
    private final FlashSaleService flashSaleService;

    public FlashSaleController(FlashSaleService flashSaleService) {
        this.flashSaleService = flashSaleService;
    }

    @PostMapping
    public ResponseEntity<FlashSaleResponse> createFlashSale(@RequestBody @Valid CreateFlashSaleRequest request){
        FlashSaleResponse response = flashSaleService.createFlashSale(request);
        return ResponseEntity.ok(response);
    }
}
