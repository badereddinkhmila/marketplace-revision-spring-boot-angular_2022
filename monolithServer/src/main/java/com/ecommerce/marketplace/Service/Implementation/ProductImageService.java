package com.ecommerce.marketplace.Service.Implementation;

import com.ecommerce.marketplace.Entity.Product;
import com.ecommerce.marketplace.Entity.ProductImageFile;
import com.ecommerce.marketplace.Exception.ResourceNotFoundException;
import com.ecommerce.marketplace.Repository.ProductImageRepository;
import com.ecommerce.marketplace.Service.IProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductImageService implements IProductImageService {
    private final ProductImageRepository productImageRepository;
    private final CrudProductService crudProductService;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository,
                               CrudProductService crudProductService) {
        this.productImageRepository = productImageRepository;
        this.crudProductService = crudProductService;
    }

    public ProductImageFile store(MultipartFile file, Long productId) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss.SS");
        String fileName = "ProductImage_" + simpleDateFormat.format(new Date()) + "_" + file.getOriginalFilename();
        ProductImageFile productImageFile = new ProductImageFile(fileName, file.getBytes(), crudProductService.getProductById(productId));
        return productImageRepository.save(productImageFile);
    }

    public List<ProductImageFile> getProductFiles(Long productId) {
        Product product = crudProductService.getProductById(productId);
        return productImageRepository.findByProduct(product);
    }

    public ProductImageFile getProductFileById(Long fileId) {
        Optional<ProductImageFile> file = productImageRepository.findById(fileId);
        if (!file.isPresent()) {
            throw new ResourceNotFoundException("Product Image", "ID", fileId);
        }
        return file.get();
    }

    public void delete(Long productIamgeId) {
        productImageRepository.deleteById(productIamgeId);
    }
}
