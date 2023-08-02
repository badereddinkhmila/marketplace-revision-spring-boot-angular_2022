package com.ecommerce.marketplace.Repository;

import com.ecommerce.marketplace.Entity.Role;
import com.ecommerce.marketplace.Entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}
