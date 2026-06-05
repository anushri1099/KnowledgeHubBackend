package com.knowledgeHub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knowledgeHub.DTO.TechStackDTO;
import com.knowledgeHub.Entity.TechStack;
import com.knowledgeHub.Repository.CourseRepository;
import com.knowledgeHub.Repository.TechStackRepository;

import java.util.List;

@Service
public class TechStackService {

	@Autowired
	private TechStackRepository repository;
	private CourseRepository courseRepository;


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

	    if (!repository.existsByTechstackName(name)) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Tech stack not found");
	    }

	    // ✅ Delete child records FIRST
	    //courseRepository.deleteByTechstackName(name);

	    // ✅ Then delete parent
	    repository.deleteByTechstackName(name);

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
}