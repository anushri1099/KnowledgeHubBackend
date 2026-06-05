package com.knowledgeHub.DTO;


import lombok.*;


@Getter
@Setter
public class TechStackDTO {
   
	private String techstackName;
	private Long techStackId;

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