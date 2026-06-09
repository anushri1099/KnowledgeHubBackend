package com.knowledgeHub.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledgeHub.DTO.AnswerDTO;
import com.knowledgeHub.DTO.QuestionDTO;
import com.knowledgeHub.Entity.Answers;
import com.knowledgeHub.Entity.QnAItems;
import com.knowledgeHub.Repository.AnswerRepository;
import com.knowledgeHub.Repository.QnARepository;

@Service
public class QnAService {

    @Autowired
    private QnARepository questionRepo;

    @Autowired
    private AnswerRepository answerRepo;

    // Save question
    public QnAItems saveQuestion(QuestionDTO dto) {
        QnAItems q = new QnAItems();
        q.setQuestion(dto.getQuestion());
        return questionRepo.save(q);
    }

    // Add answer
    public Answers addAnswer(Long questionId, AnswerDTO dto) {

        QnAItems question = questionRepo.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Answers ans = new Answers();
        ans.setAnswerText(dto.getAnswerText());
        ans.setQuestion(question); // FK

        return answerRepo.save(ans);
    }
    
    public QnAItems getQuestionWithAnswers(Long id) {

        return questionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }
    
    public List<QuestionDTO> getAllQuestions() {

    	List<QuestionDTO> listofquestions =  questionRepo.findAll()
                .stream()
                .map(q -> {
                    QuestionDTO dto = new QuestionDTO();

                    dto.setId(q.getId());
                    dto.setQuestion(q.getQuestion());

                    return dto;
                })
                .toList();
        return listofquestions;
    }
}