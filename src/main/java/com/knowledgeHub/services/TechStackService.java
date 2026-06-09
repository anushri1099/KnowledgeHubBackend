package com.knowledgeHub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knowledgeHub.DTO.DashboardStatsDTO;
import com.knowledgeHub.DTO.TechStackDTO;
import com.knowledgeHub.Entity.CourseItem;
import com.knowledgeHub.Entity.TechStack;
import com.knowledgeHub.Repository.AnswerRepository;
import com.knowledgeHub.Repository.CourseRepository;
import com.knowledgeHub.Repository.QnARepository;
import com.knowledgeHub.Repository.TechStackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TechStackService {

	@Autowired
	private TechStackRepository repository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private QnARepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;


	public ResponseEntity<?> save(TechStackDTO dto) {

	    try {
	        TechStack tech = new TechStack();
	        tech.setTechstackName(dto.getTechstackName());

	        return ResponseEntity.ok(repository.save(tech));

	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	public List<TechStack> getAll() {
		return repository.findAll();
	}

	@Transactional
	public ResponseEntity<?> deleteByName(String name) {

	    Optional<TechStack> techStackOpt = repository.findByTechstackNameIgnoreCase(name);

	    if (techStackOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Tech stack not found");
	    }

	    TechStack techStack = techStackOpt.get();

	    // ✅ FIXED getter name (IMPORTANT)
	    Long techId = techStack.getTechStackId();

	    // ✅ Step 1: delete child
	    courseRepository.deleteByTechStack_TechStackId(techId);

	    // ✅ Step 2: delete parent
	    repository.deleteById(techId);

	    return ResponseEntity.ok("Deleted successfully");
	}

	public ResponseEntity<?> saveAll(List<TechStackDTO> dtoList) {

	    // Get existing names from DB
	    List<String> existing = repository.findAll()
	            .stream()
	            .map(TechStack::getTechstackName)
	            .toList();

	    // Filter + map DTO → Entity

	    List<TechStack> newList = dtoList.stream()
	            .filter(dto -> dto.getTechstackName() != null && !dto.getTechstackName().isBlank())
	            .filter(dto -> !existing.contains(dto.getTechstackName()))
	            .map(dto -> {
	                TechStack t = new TechStack();
	                t.setTechstackName(dto.getTechstackName());
	                return t;
	            })
	            .toList();

	    if (newList.isEmpty()) {
	        return ResponseEntity
	                .badRequest()
	                .body("All tech stacks already exist");
	    }

	    List<TechStack> saved = repository.saveAll(newList);

	    return ResponseEntity.ok(saved);
	}
	
	public DashboardStatsDTO getStats() {

	    DashboardStatsDTO stats = new DashboardStatsDTO();

	    stats.setCourses(courseRepository.count());
	    stats.setQuestions(questionRepository.count());
	    stats.setAnswers(answerRepository.count());
	    stats.setTechStacks(repository.count());

	    return stats;
	}
}