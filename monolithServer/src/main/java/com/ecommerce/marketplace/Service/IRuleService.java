package com.ecommerce.marketplace.Service;

import com.ecommerce.marketplace.Entity.Rule;
import com.ecommerce.marketplace.Entity.RuleType;
import org.kie.api.runtime.KieContainer;

import java.io.FileNotFoundException;
import java.util.Collection;

public interface IRuleService {
    KieContainer loadContainerFromString(String drl);

    void reload();

    Rule getById(Long id);

    Collection<Rule> loadRules();

    Collection<Rule> getByType(RuleType type);

    void deleteById(Long id);

    Rule createRule(Rule rule);

    String createDroolFile(Collection<Rule> rules, String template_path) throws FileNotFoundException;

    void clearRules();
}
