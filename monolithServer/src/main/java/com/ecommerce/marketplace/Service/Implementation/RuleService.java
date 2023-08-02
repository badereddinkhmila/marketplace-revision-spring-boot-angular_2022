package com.ecommerce.marketplace.Service.Implementation;

import com.ecommerce.marketplace.Entity.Rule;
import com.ecommerce.marketplace.Entity.RuleType;
import com.ecommerce.marketplace.Exception.ResourceNotFoundException;
import com.ecommerce.marketplace.Repository.RuleRepository;
import com.ecommerce.marketplace.Service.IRuleService;
import com.github.javaparser.utils.Log;
import lombok.extern.slf4j.Slf4j;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RuleService implements IRuleService {
    private static final String RULES_DRL_PATH = "src/main/resources/drools/";
    private final KieServices kieServices = KieServices.Factory.get();
    private final RuleRepository ruleRepository;

    @Autowired
    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    /**
     *
     */
    @Override
    public void reload() {

    }

    /**
     * @param id
     * @return a Rule by its id
     * @throws com.ecommerce.marketplace.Exception.ResourceNotFoundException
     */
    @Override
    public Rule getById(Long id) throws ResourceNotFoundException {
        Optional<Rule> rule = ruleRepository.findById(id);
        if (!rule.isPresent()) {
            throw new ResourceNotFoundException("Rule", "Id", id);
        }
        return rule.get();
    }

    /**
     * @return
     */
    @Override
    public Collection<Rule> loadRules() {
        Collection<Rule> rules = ruleRepository.findAll();
        return rules;
    }

    /**
     * @param type
     * @return
     */
    @Override
    public Collection<Rule> getByType(RuleType type) {
        Collection<Rule> rules = ruleRepository.findByType(type);
        return rules;
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        try {
            ruleRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting rule with id: " + id + ", with error: " + e.getMessage());
        }
    }

    /**
     * @param rule
     * @return rule
     */
    @Override
    public Rule createRule(Rule rule) {
        try {
            ruleRepository.save(rule);
            return rule;
        } catch (Exception e) {
            log.error("Error creating a new rule with error: " + e.getMessage());
        }
        return rule;
    }

    public KieSession createKieSessionFromDRL(String drl) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                Log.error("Error: " + message.getText());
            }
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        return kieHelper.build().newKieSession();
    }

    /**
     * @return
     */
    @Override
    public KieContainer loadContainerFromString(String drl) {
        KieFileSystem kieFileSystem = this.kieServices.newKieFileSystem();
        kieFileSystem.write(RULES_DRL_PATH + "sample_rule.drt", drl);
        log.info("In load container");
        KieBuilder kieBuilder = this.kieServices.newKieBuilder(kieFileSystem).buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        return this.kieServices.newKieContainer(kieModule.getReleaseId());
    }

    /**
     * @return drl file as a string
     */
    @Override
    public String createDroolFile(Collection<Rule> rules, String template_name) throws FileNotFoundException {
        ObjectDataCompiler droolsCompiler = new ObjectDataCompiler();
        try {
            FileInputStream template = new FileInputStream(RULES_DRL_PATH + template_name);
            String drl = droolsCompiler.compile(rules, template);
            log.info("The rule states that: " + rules.stream().findFirst().get().getRuleWhen()
                    + " then execute this: " + rules.stream().findFirst().get().getRuleThen());
            return drl;
        } catch (FileNotFoundException notFoundException) {
            log.info("the template file is not found");
        } catch (Exception e) {
            log.error("error in the stream input");
        }
        return "";
    }

    /**
     *
     */
    @Override
    public void clearRules() {

    }
}
