package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.api.path.Path;
import com.dchprojects.mydictionaryrestapi.domain.dto.CreateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.UpdateWordRequest;
import com.dchprojects.mydictionaryrestapi.domain.dto.WordResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.annotation.security.RolesAllowed;
import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "Word")
@RestController
@RequestMapping(Path.REQUEST_PATH_API_WORDS)
@RolesAllowed({RoleNameString.ROLE_USER, RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class WordApi {

    private final WordService wordService;

    private static final String REQUEST_PATH_API_DELETE_WORD_INDIVIDUAL_USER_INDIVIDUAL_COURSE_INDIVIDUAL_WORD = "/userId/{userId}/courseId/{courseId}/wordId/{wordId}";

    @GetMapping
    public List<WordResponse> list() {
        return wordService.listAll();
    }

    @PostMapping
    public ResponseEntity<WordResponse> createWord(@RequestBody @Valid CreateWordRequest createWordRequest) {
        try {
            return new ResponseEntity<>(wordService.create(createWordRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<WordResponse> udpateWord(@RequestBody @Valid UpdateWordRequest updateWordRequest) {
        try {
            return new ResponseEntity<>(wordService.update(updateWordRequest), HttpStatus.OK);
        } catch (ValidationException validationException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(REQUEST_PATH_API_DELETE_WORD_INDIVIDUAL_USER_INDIVIDUAL_COURSE_INDIVIDUAL_WORD)
    public ResponseEntity<?> deleteWord(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long wordId) {
        try {
            wordService.deleteByUserIdAndCourseIdAndWordId(userId, courseId, wordId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
