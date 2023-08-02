package com.ecommerce.marketplace.Repository;

import com.ecommerce.marketplace.Entity.Rule;
import com.ecommerce.marketplace.Entity.RuleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
    Collection<Rule> findByType(RuleType type);
}
