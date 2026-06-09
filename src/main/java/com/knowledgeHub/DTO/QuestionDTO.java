package com.knowledgeHub.DTO;


import lombok.*;

@Getter
@Setter
public class QuestionDTO {
    private String question;

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	private Long id ;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}