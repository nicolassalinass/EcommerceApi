package com.ecoomerce.Mappers;

import com.ecoomerce.DTO.ProductRequestDTO;
import com.ecoomerce.DTO.ProductResponseDTO;
import com.ecoomerce.Entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductMapper {
    public ProductResponseDTO toDTO(ProductEntity productEntity){
        return new ProductResponseDTO(
                productEntity.getId(),
                productEntity.getNombre(),
                productEntity.getDescripcion(),
                productEntity.getPrice(),
                productEntity.getStok(),
                productEntity.isIs_active(),
                productEntity.getImage(),
                productEntity.getCategory(),
                productEntity.getCreated_at().toString()
        );
    }

    public List<ProductResponseDTO> toDTO(List<ProductEntity> productEntities){
        return productEntities.stream().map(this::toDTO).toList();
    }

    public ProductEntity toEntity(ProductRequestDTO requestDTO){
        ProductEntity entity = new ProductEntity();
        entity.setNombre(requestDTO.nombre());
        entity.setDescripcion(requestDTO.descripcion());
        entity.setPrice(requestDTO.price());
        entity.setStok(requestDTO.stok());
        entity.setIs_active(requestDTO.is_active());
        entity.setImage(requestDTO.image());
        entity.setCategory(requestDTO.category());
        return entity;
    }

}
