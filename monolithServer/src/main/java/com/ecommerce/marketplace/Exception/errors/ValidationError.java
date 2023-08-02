package com.ecommerce.marketplace.Exception.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {
    private String code;
    private String message;
}
