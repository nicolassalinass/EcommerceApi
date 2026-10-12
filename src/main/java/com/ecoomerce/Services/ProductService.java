package com.ecoomerce.Services;

import com.ecoomerce.DTO.ProductRequestDTO;
import com.ecoomerce.DTO.ProductResponseDTO;
import com.ecoomerce.Entity.ProductEntity;
import com.ecoomerce.Mappers.ProductMapper;
import com.ecoomerce.Repository.ProductRespository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRespository productRespository;
    private final ProductMapper productMapper;

    public ProductService(ProductRespository productRespository, ProductMapper productMapper) {
        this.productRespository = productRespository;
        this.productMapper = productMapper;
    }

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

}
