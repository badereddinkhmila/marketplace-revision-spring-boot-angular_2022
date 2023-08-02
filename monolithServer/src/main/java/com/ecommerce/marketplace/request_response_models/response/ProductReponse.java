package com.ecommerce.marketplace.request_response_models.response;

import com.ecommerce.marketplace.DTO.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReponse {

    private Long id;

    private String nameProduct;

    private String description;

    private boolean isAvailable;

    private Float price;

    private int quantity;

    private CategoryDTO category;

    private List<String> productImages;
}
