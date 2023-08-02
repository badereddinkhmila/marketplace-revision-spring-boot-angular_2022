package com.ecommerce.marketplace.Repository;

import com.ecommerce.marketplace.Entity.OrderProduct;
import com.ecommerce.marketplace.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findByOrder(Long order_id);
}
