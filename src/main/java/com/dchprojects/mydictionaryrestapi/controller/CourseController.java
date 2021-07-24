package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/userId/{id}")
    public List<CourseEntity> listByUserId(@PathVariable Integer id) { return courseService.listByUserId(id); }

    @PostMapping
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
        Boolean isExist = courseService.isExist(course.getLanguage_name(), course.getUser_id());
        if (isExist) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            courseService.save(course);
            Optional<CourseEntity> savedCourse = courseService.findByLanguageNameAndUserId(course.getLanguage_name(), course.getUser_id());
            if (savedCourse.isPresent()) {
                return new ResponseEntity<>(savedCourse.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @DeleteMapping("/userId/{userId}/courseId/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer userId, @PathVariable Integer courseId) {
        Boolean isExist = courseService.isExist(userId, courseId);
        if (isExist) {
            courseService.deleteByUserIdAndCourseId(userId, courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
