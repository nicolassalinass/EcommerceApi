package com.ecoomerce.Services;

import com.ecoomerce.DTO.ProductRequestDTO;
import com.ecoomerce.DTO.ProductResponseDTO;
import com.ecoomerce.DTO.UploadedImage;
import com.ecoomerce.Entity.ProductEntity;
import com.ecoomerce.Mappers.ProductMapper;
import com.ecoomerce.Repository.ProductRespository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRespository productRespository;
    private final ProductMapper productMapper;
    private final ImageService imageService;

    public ProductService(ProductRespository productRespository, ProductMapper productMapper, ImageService imageService) {
        this.productRespository = productRespository;
        this.productMapper = productMapper;
        this.imageService = imageService;
    }

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO requestDTO){
        if (requestDTO == null) return null;
        ProductEntity entity = this.productMapper.toEntity(requestDTO);
        entity.setCreated_at(LocalDateTime.now());
        ProductEntity savedEntity = this.productRespository.save(entity);
        return this.productMapper.toDTO(savedEntity);
    }

    public List<ProductResponseDTO> getAll(){
        List<ProductEntity> entities = this.productRespository.findAll();
        return this.productMapper.toDTO(entities);
    }

    public void delete(Long id){
        ProductEntity entity = this.productRespository.findById(id).orElse(null);
        if (entity != null){
            entity.setIs_active(false);
            this.productRespository.save(entity);
        }
    }

    public UploadedImage uploadImage(MultipartFile file) {
        return this.imageService.upload(file);
    }

    public ProductResponseDTO getById(Long id) {
        ProductEntity product = this.productRespository.findById(id).orElse(null);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return this.productMapper.toDTO(product);
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO){
        ProductEntity product = this.productRespository.findById(id).orElse(null);

        if (product == null) return null;

        ProductEntity update = this.productMapper.updateEntity(product, productRequestDTO);



        return this.productMapper.toDTO(this.productRespository.save(update));
    }


    public boolean exist(Long id){
        return this.productRespository.existsById(id);
    }

    @Transactional
    public ProductResponseDTO updateImageProduct(Long id, MultipartFile file) {
        ProductEntity product = this.productRespository.findById(id).orElse(null);

        UploadedImage uploadedImage = uploadImage(file);

        ProductEntity save = this.productMapper.updateImageProduct(product, uploadedImage);
        return this.productMapper.toDTO(save);
    }
}
