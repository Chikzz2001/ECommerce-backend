package com.ecommerce.ecom.dto;

import lombok.Data;

/*
    Separation of Concerns: DTOs separate the internal representation (entity) from the external representation (API response). This helps ensure that database structure and API structure remain decoupled.
 */
@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
