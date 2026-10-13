package com.ecoomerce.Controller;

import com.ecoomerce.DTO.ProductRequestDTO;
import com.ecoomerce.DTO.ProductResponseDTO;
import com.ecoomerce.DTO.UploadedImage;
import com.ecoomerce.Services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Productos", description = "Api de gestion de productos")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @Operation(
            summary = "Crea nuevo Producto",
            description = "Permite la creacion de un nuevo producto"
    )
    public ResponseEntity<ProductResponseDTO> create(
            @RequestBody @Valid ProductRequestDTO productRequestDTO){
        if (productRequestDTO != null){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.productService.create(productRequestDTO));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/uploadimage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Carga de imagenes a cloudinary"
    )
    public ResponseEntity<UploadedImage> uploadImage(MultipartFile file){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.uploadImage(file));
    }

    @GetMapping("/")
    @Operation(
            summary = "Devuelve todos los productos",
            description = "Permite devolver todos los productos de la base de datos"
    )
    public ResponseEntity<List<ProductResponseDTO>> all(){
        return ResponseEntity.ok(this.productService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por id")
    public ResponseEntity<ProductResponseDTO> getById(@Valid @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.productService.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto por id")
    public ResponseEntity<Void> delete(@RequestParam Long id){
        this.productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto por id")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @Valid @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO productRequestDTO){
        if (!this.productService.exist(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.productService.updateProduct(id, productRequestDTO));
    }
}
