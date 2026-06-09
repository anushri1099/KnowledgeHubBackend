package com.knowledgeHub.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowledgeHub.DTO.DashboardStatsDTO;
import com.knowledgeHub.DTO.TechStackDTO;
import com.knowledgeHub.Entity.TechStack;
import com.knowledgeHub.services.TechStackService;

@RestController
@RequestMapping("/api/techstack")
public class TechStackController {

	@Autowired
	private TechStackService service;

	// Tech stack Apis

	@PostMapping("/bulk")
	public ResponseEntity<?> saveAll(@RequestBody List<TechStackDTO> dtoList) {
		return service.saveAll(dtoList);
	}


	
	@PostMapping("/saveStack")
	public ResponseEntity<?> create(@RequestBody TechStackDTO dto) {

	    ResponseEntity<?> saved = service.save(dto);

	    if (saved.getStatusCode().is2xxSuccessful()) {
	        return ResponseEntity.ok(
	                Map.of(
	                        "status", "success",
	                        "message", "Saved"
	                )
	        );
	    } else {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(
	                Map.of(
	                        "status", "duplicate",
	                        "message", "Already exists"
	                )
	        );
	    }
	}


	@GetMapping("/getAll")
	public List<TechStack> getAll() {
		return service.getAll();
	}

	@DeleteMapping("/name/{name}")
	public ResponseEntity<?> deleteByName(@PathVariable String name) {
		return service.deleteByName(name);
	}
	
	@GetMapping("/stats")
	public ResponseEntity<DashboardStatsDTO> getStats() {
	    return ResponseEntity.ok(service.getStats());
	}
}

