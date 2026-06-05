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
@Table(name ="tech_stacks")
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

	@OneToMany(mappedBy = "techStack",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
 private List<CourseItem> courses;
    
	public String getTechstackName() {
		return techstackName;
	}

	public void setTechstackName(String techstackName) {
		this.techstackName = techstackName;
	}



}
