package com.knowledgeHub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knowledgeHub.DTO.CourseDTO;
import com.knowledgeHub.Entity.CourseItem;
import com.knowledgeHub.Entity.TechStack;
import com.knowledgeHub.Repository.CourseRepository;
import com.knowledgeHub.Repository.TechStackRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private TechStackRepository techRepo;

    public ResponseEntity<?> saveCourse(CourseDTO dto) {

        Optional<TechStack> optional =
                techRepo.findByTechstackNameIgnoreCase(dto.getTechStack().trim());

        //If not found → return clean message
        if (optional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Tech stack not found");
        }

        TechStack tech = optional.get();

        CourseItem course = new CourseItem();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setLink(dto.getLink());
        course.setTechStack(tech); // FK set

        //Success
        return ResponseEntity.ok(courseRepo.save(course));
    }
    
    @Transactional
    public ResponseEntity<?> deleteCourse(Long id) {

        if (!courseRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }

        courseRepo.deleteById(id);

        return ResponseEntity.ok("Course deleted successfully");
    }

    public List<CourseItem> getAllCourses(){
        return courseRepo.findAll();
    }
    public ResponseEntity<?> getCoursesByTechStack(String name) {

        List<CourseItem> courses =
                courseRepo.findByTechStack_TechstackNameIgnoreCase(name);

        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No courses found for this tech stack");
        }

        return ResponseEntity.ok(courses);
    }
}