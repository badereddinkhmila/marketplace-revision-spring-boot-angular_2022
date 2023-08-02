package com.ecommerce.marketplace.Repository;

import com.ecommerce.marketplace.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long orderId);

    Optional<Order> findByCreatedBy(Long userId);

    long countByCreatedBy(Long userId);
}
