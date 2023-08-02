package com.ecommerce.marketplace.request_response_models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotNull
    @Size(min = 3, max = 50)
    private String nameProduct;

    @NotNull
    @Size(min = 20, message = "Enter a good description with a minimum of 20 characters")
    private String description;

    private boolean isAvailable;

    @NotNull
    @Min(value = 0, message = "Price must be set to a value above 0.00")
    private Float price;

    @Min(value = 0, message = "Minimum quantity is 0")
    private int quantity;

    @NotNull
    private Long categoryId;
}
