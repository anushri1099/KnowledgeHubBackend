package com.knowledgeHub.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name ="tech_stacks",schema="knowledge_hub")
@Getter
@Setter
public class TechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long techStackId;

    @Column(nullable = false, unique = true)
    private String techstackName;

	public Long getTechStackId() {
		return techStackId;
	}

	public void setTechStackId(Long techStackId) {
		this.techStackId = techStackId;
	}

	public String getTechstackName() {
		return techstackName;
	}

	public void setTechstackName(String techstackName) {
		this.techstackName = techstackName;
	}
}
