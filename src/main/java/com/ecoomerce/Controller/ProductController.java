package com.ecoomerce.Controller;

import com.ecoomerce.DTO.ProductRequestDTO;
import com.ecoomerce.DTO.ProductResponseDTO;
import com.ecoomerce.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productRequestDTO){
        if (productRequestDTO != null){
            return ResponseEntity.ok(this.productService.create(productRequestDTO));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> all(){
        return ResponseEntity.ok(this.productService.getAll());
    }
}
