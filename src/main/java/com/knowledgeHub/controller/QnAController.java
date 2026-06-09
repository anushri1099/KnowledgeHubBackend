package com.knowledgeHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowledgeHub.DTO.AnswerDTO;
import com.knowledgeHub.DTO.QuestionDTO;
import com.knowledgeHub.services.QnAService;

@RestController
@RequestMapping("/api/qna")
public class QnAController {

	@Autowired
	private QnAService service;

	//Create question
	@PostMapping("/question")
	public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO dto) {
		return ResponseEntity.ok(service.saveQuestion(dto));
	}

	//Add answer to question
	@PostMapping("/{id}/answer")
	public ResponseEntity<?> addAnswer(@PathVariable Long id, @RequestBody AnswerDTO dto) {

		return ResponseEntity.ok(service.addAnswer(id, dto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getQuestionWithAnswers(@PathVariable Long id) {
	    return ResponseEntity.ok(service.getQuestionWithAnswers(id));
	}
	@GetMapping("/questions")
	public ResponseEntity<?> getAllQuestions() {
	    return ResponseEntity.ok(service.getAllQuestions());
	}

}