package com.ecommerce.marketplace.Entity;

import com.ecommerce.marketplace.Entity.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Rule extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ruleWhen;
    private String ruleThen;

    @Enumerated(EnumType.STRING)
    private RuleType type;
    
    private int ruleOrder;
}
