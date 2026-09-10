package com.AtomicSamurai.LimitedSale.Service;

import com.AtomicSamurai.LimitedSale.Entity.FlashSale;
import com.AtomicSamurai.LimitedSale.Entity.Product;
import com.AtomicSamurai.LimitedSale.Repository.FlashSaleRepository;
import com.AtomicSamurai.LimitedSale.Repository.ProductRepository;
import com.AtomicSamurai.LimitedSale.dto.CreateFlashSaleRequest;
import com.AtomicSamurai.LimitedSale.dto.FlashSaleResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class FlashSaleService {
    private final FlashSaleRepository flashSaleRepository;
    private final ProductRepository productRepository;

    public FlashSaleService(FlashSaleRepository flashSaleRepository, ProductRepository productRepository) {
        this.flashSaleRepository = flashSaleRepository;
        this.productRepository = productRepository;
    }

    public FlashSaleResponse createFlashSale(CreateFlashSaleRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product Doesnt exist"));

        if(request.getSaleStartTime().isAfter(request.getSaleEndTime()) || request.getSaleStartTime().isEqual(request.getSaleEndTime()) ){
            throw new IllegalArgumentException("Start Time Cannot be Greater or Equal to End Time");
        }

        if(flashSaleRepository.existsOverlappingSale(request.getProductId(), request.getSaleStartTime(), request.getSaleEndTime())){
            throw new IllegalArgumentException("Product already has a FlashSale during this time");
        }

        FlashSale flashSale = new FlashSale();
        flashSale.setProduct(product);
        flashSale.setSalePrice(request.getSalePrice());
        flashSale.setSaleStock(request.getSaleStock());
        flashSale.setSaleStartTime(request.getSaleStartTime());
        flashSale.setSaleEndTime(request.getSaleEndTime());

        FlashSale createdFlashSale = flashSaleRepository.save(flashSale);
        return generateResponse(createdFlashSale);
    }

    private FlashSaleResponse generateResponse(FlashSale flashSale){
        return new FlashSaleResponse(
                flashSale.getId(),
                flashSale.getProduct().getId(),
                flashSale.getSalePrice(),
                flashSale.getSaleStock(),
                flashSale.getSaleStartTime(),
                flashSale.getSaleEndTime()
        );
    }
}
