package com.knowledgeHub.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowledgeHub.Entity.CourseItem;

public interface CourseRepository extends JpaRepository<CourseItem, Long> {
	
	List<CourseItem> findByTechStack_TechstackNameIgnoreCase(String techStackName);
	//void deleteByTechstackName(String name);
    void deleteById(Long id);
}

