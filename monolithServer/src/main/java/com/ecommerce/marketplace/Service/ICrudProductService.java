package com.ecommerce.marketplace.Service;

import com.ecommerce.marketplace.Entity.Product;
import com.ecommerce.marketplace.Exception.ResourceNotFoundException;
import com.ecommerce.marketplace.request_response_models.request.ProductRequest;
import com.ecommerce.marketplace.request_response_models.response.PagedResponse;
import com.ecommerce.marketplace.request_response_models.response.ProductReponse;

public interface ICrudProductService {
    Product save(ProductRequest product);

    void deleteProduct(Long id);

    Product getProductById(Long id) throws ResourceNotFoundException;

    ProductReponse getProductResponse(Product product);

    PagedResponse<ProductReponse> getAllProducts(int page, int size, String sortDir, String sort);

    PagedResponse<ProductReponse> getProductByProviderId(Long providerId, int page, int size, String sortDir, String sort);

    PagedResponse<ProductReponse> getProductByCategoryId(Long categoryId, int page, int size, String sortDir, String sort);
}
