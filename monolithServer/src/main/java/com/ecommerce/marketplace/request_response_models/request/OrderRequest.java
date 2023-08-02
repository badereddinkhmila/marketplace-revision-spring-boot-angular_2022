package com.ecommerce.marketplace.request_response_models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class OrderRequest {
    @NotEmpty
    private List<String> products;

}
