package com.ecoomerce.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "El campo nombre es requerido")
        String nombre,
        @NotBlank(message = "El campo descripcion es requerido")
        String descripcion,
        @Min(value = 0, message = "El campo precio es requerido")
        BigDecimal price,
        @Min(value = 0, message = "El campo stock debe ser mayor o igual a 0")
        Integer stok,
        @NotNull(message = "El campo activo es requerido")
        boolean is_active,
        @NotBlank(message = "El campo imagen es requerido")
        String image,
        @NotBlank(message = "El campo categoria es requerido")
        String category
) {
}
