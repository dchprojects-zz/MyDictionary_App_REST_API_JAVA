package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.dto.CourseResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateCourseRequest;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
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

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<CourseResponse>> listByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(courseService.listByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody @Valid CreateCourseRequest createCourseRequest) {
        try {
            return new ResponseEntity<>(courseService.create(createCourseRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/userId/{userId}/courseId/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        try {
            courseService.deleteByUserIdAndCourseId(userId, courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
