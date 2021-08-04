package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.dto.CreateCourseRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "Course")
@RestController
@RequestMapping("/api/v1/courses")
@RolesAllowed({RoleNameString.ROLE_USER, RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class CourseApi {

    private final CourseService courseService;
    private final UserService userService;

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
    public ResponseEntity<CourseEntity> createCourse(@RequestBody @Valid CreateCourseRequest createCourseRequest) {
        try {
            return new ResponseEntity<>(courseService.create(createCourseRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userId/{userId}/courseId/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        Boolean existsByUserId = userService.existsByUserId(userId);
        Boolean existsByUserIdAndCourseId = courseService.existsByUserIdAndCourseId(userId, courseId);
        if (existsByUserId && existsByUserIdAndCourseId) {
            courseService.deleteByUserIdAndCourseId(userId, courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
