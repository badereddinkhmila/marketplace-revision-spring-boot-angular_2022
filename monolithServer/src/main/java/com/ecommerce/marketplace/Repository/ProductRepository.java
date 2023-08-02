package com.ecommerce.marketplace.Repository;

import com.ecommerce.marketplace.Entity.Category;
import com.ecommerce.marketplace.Entity.Product;
import com.ecommerce.marketplace.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategory(Category category, Pageable pageReq);

    Page<Product> findByCreatedBy(User provider, Pageable pageReq);
}
