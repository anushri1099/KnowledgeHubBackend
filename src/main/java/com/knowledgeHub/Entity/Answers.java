package com.knowledgeHub.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "answers",schema="knowledge_hub")
public class Answers {

    public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public QnAItems getQuestion() {
		return question;
	}

	public void setQuestion(QnAItems question) {
		this.question = question;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String answerText;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private QnAItems question;
}