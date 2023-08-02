package com.ecommerce.marketplace.Controller;

import com.ecommerce.marketplace.Entity.Rule;
import com.ecommerce.marketplace.Service.Implementation.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/v1/rules")
@Slf4j
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/")
    public ResponseEntity<?> createRule(@Valid @RequestBody Rule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping("/")
    public ResponseEntity<?> getRules() {
        return ResponseEntity.ok(ruleService.loadRules());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRule(@PathVariable Long id) {
        ruleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
