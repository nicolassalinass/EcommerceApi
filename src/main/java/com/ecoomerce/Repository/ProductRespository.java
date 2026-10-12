package com.ecoomerce.Repository;

import com.ecoomerce.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRespository extends JpaRepository<ProductEntity, Long> {
}
