package com.ecommerce.marketplace.Repository;

import com.ecommerce.marketplace.Entity.Product;
import com.ecommerce.marketplace.Entity.ProductImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageFile, Long> {

    List<ProductImageFile> findByProduct(Product product);
}
