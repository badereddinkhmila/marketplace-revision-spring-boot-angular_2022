package com.ecommerce.marketplace.request_response_models.response;

import com.ecommerce.marketplace.DTO.ProviderSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductResponse {
    private Long id;
    private String nameProduct;
    private String descriptionProduct;
    private boolean isAvailable;
    private Float price;
    private int discount;
    private ProviderSummary provider;

}
