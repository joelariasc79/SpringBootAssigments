package com.booking.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class) // Enable JPA auditing
public class BookingRules {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String RuleName;
    private String ruleDescription;
    
	public BookingRules(int id, String ruleName, String ruleDescription) {
		super();
		this.id = id;
		RuleName = ruleName;
		this.ruleDescription = ruleDescription;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRuleName() {
		return RuleName;
	}
	public void setRuleName(String ruleName) {
		RuleName = ruleName;
	}
	public String getRuleDescription() {
		return ruleDescription;
	}
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
    
}