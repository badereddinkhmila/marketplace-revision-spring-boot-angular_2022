package com.ecommerce.marketplace.Utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


public class PagingCommons {
    public static PageRequest getPagingRequest(int page, int size, String sortDir, String sort) {
        if (size > AppConstants.MAX_PAGE_SIZE) size = AppConstants.MAX_PAGE_SIZE;
        PageRequest pageRequest = (sortDir == null && sort == null) ? PageRequest.of(page, size) : PageRequest.of(page, size, Sort.Direction.fromString(sortDir.toUpperCase()), sort);
        return pageRequest;
    }
}
