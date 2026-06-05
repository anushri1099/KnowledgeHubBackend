package com.knowledgeHub.controller;

import com.knowledgeHub.Entity.CourseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.knowledgeHub.DTO.CourseDTO;
import com.knowledgeHub.services.CourseService;

import java.util.List;


@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService service;

    //Create
    @PostMapping("/createCourse")
    public ResponseEntity<?> create(@RequestBody CourseDTO dto) {
        return service.saveCourse(dto);
    }

    //Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.deleteCourse(id);
    }

    //Get by TechStack
    @GetMapping("/tech/{name}")
    public ResponseEntity<?> getByTech(@PathVariable String name) {
        return service.getCoursesByTechStack(name);
    }

    //Get All Courses
    @GetMapping("/getAllCourses")
    public List<CourseItem> getAllCourses(){
        return service.getAllCourses();
    }
}