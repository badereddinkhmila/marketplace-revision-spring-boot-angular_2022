package com.ecommerce.marketplace.Service;

import com.ecommerce.marketplace.Entity.ProductImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductImageService {

    ProductImageFile store(MultipartFile file, Long productId) throws IOException;

    ProductImageFile getProductFileById(Long fileId);

    void delete(Long id);

    List<ProductImageFile> getProductFiles(Long productId);
}
