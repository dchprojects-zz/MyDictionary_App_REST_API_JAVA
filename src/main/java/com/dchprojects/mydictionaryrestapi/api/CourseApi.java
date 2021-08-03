package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Tag(name = "Course")
@RestController
@RequestMapping("/api/v1/courses")
@RolesAllowed({RoleNameString.ROLE_USER, RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;
    private final UserService userService;
    private final LanguageService languageService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<CourseEntity>> listByUserId(@PathVariable Long userId) {
        Boolean existsByUserId = userService.existsByUserId(userId);
        if (existsByUserId) {
            return new ResponseEntity<>(courseService.listByUserId(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
        Boolean existsByUserId = userService.existsByUserId(course.getUserId());
        Boolean courseIsExist = courseService.isExist(course.getLanguageName(), course.getUserId());
        Boolean languageIsExist = languageService.isExist(course.getLanguageId(), course.getLanguageName());
        if (existsByUserId && languageIsExist) {
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
        Boolean existsByUserId = userService.existsByUserId(userId);
        Boolean courseIsExist = courseService.isExist(userId, courseId);
        if (existsByUserId && courseIsExist) {
            courseService.deleteByUserIdAndCourseId(userId, courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userId/{userId}")
    public ResponseEntity<?> deleteAllCoursesByUserId(@PathVariable Long userId) {
        Boolean userIsExist = userService.existsByUserId(userId);
        if (userIsExist) {
            courseService.deleteAllByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
