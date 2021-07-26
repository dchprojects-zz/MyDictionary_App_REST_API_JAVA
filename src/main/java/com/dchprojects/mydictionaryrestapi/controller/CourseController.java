package com.dchprojects.mydictionaryrestapi.controller;

import com.dchprojects.mydictionaryrestapi.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private LanguageService languageService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<CourseEntity>> listByUserId(@PathVariable Long userId) {
        Boolean userIsExist = userService.isExist(userId);
        if (userIsExist) {
            return new ResponseEntity<>(courseService.listByUserId(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
        Boolean userIsExist = userService.isExist(course.getUserId());
        Boolean courseIsExist = courseService.isExist(course.getLanguageName(), course.getUserId());
        Boolean languageIsExist = languageService.isExist(course.getLanguageId(), course.getLanguageName());
        if (userIsExist && languageIsExist) {
            if (courseIsExist) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                courseService.save(course);
                Optional<CourseEntity> savedCourse = courseService.findByLanguageNameAndUserId(course.getLanguageName(), course.getUserId());
                if (savedCourse.isPresent()) {
                    return new ResponseEntity<>(savedCourse.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userId/{userId}/courseId/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        Boolean userIsExist = userService.isExist(userId);
        Boolean courseIsExist = courseService.isExist(userId, courseId);
        if (userIsExist && courseIsExist) {
            courseService.deleteByUserIdAndCourseId(userId, courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userId/{userId}")
    public ResponseEntity<?> deleteAllCoursesByUserId(@PathVariable Long userId) {
        Boolean userIsExist = userService.isExist(userId);
        if (userIsExist) {
            courseService.deleteAllByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
