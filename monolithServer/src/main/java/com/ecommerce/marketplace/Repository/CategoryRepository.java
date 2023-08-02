package com.ecommerce.marketplace.Repository;

import com.ecommerce.marketplace.Entity.Category;
import com.ecommerce.marketplace.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<User> findByCreatedBy(Long userId);

    Optional<Category> findByName(String name);

    Optional<Category> findByDescription(String description);

}
