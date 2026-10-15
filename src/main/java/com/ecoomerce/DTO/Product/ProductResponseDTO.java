package com.ecoomerce.DTO.Product;

import java.math.BigDecimal;

public record ProductResponseDTO(
    Long id,
    String nombre,
    String descripcion,
    BigDecimal price,
    Integer stok,
    boolean is_active,
    String image,
    String category,
    String created_at
) {
}
