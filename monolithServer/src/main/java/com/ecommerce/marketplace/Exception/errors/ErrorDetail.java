package com.ecommerce.marketplace.Exception.errors;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ErrorDetail {
    private final Map<String, List<ValidationError>> errors = new HashMap<String,
            List<ValidationError>>();
    private String title;
    private int status;
    private String detail;
    private Long timestamp;
    private String path;
    private String developerMessage;
}
