package com.dchprojects.mydictionaryrestapi.api;

import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.role.RoleNameString;
import com.dchprojects.mydictionaryrestapi.service.CourseService;
import com.dchprojects.mydictionaryrestapi.service.LanguageService;
import com.dchprojects.mydictionaryrestapi.service.UserService;
import com.dchprojects.mydictionaryrestapi.service.WordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Tag(name = "Word")
@RestController
@RequestMapping("/api/v1/words")
@RolesAllowed({RoleNameString.ROLE_USER, RoleNameString.ROLE_ADMIN})
@RequiredArgsConstructor
public class WordApi {

    private final WordService wordService;
    private final UserService userService;
    private final CourseService courseService;
    private final LanguageService languageService;

    @GetMapping
    public List<WordEntity> list() {
        return wordService.listAll();
    }

    @PostMapping
    public ResponseEntity<WordEntity> createWord(@RequestBody WordEntity word) {
        Boolean existsByUserId = userService.existsByUserId(word.getUserId());
        Boolean courseIsExist = courseService.isExist(word.getUserId(), word.getCourseId());
        Boolean languageIsExist = languageService.isExist(word.getLanguageId(), word.getLanguageName());
        Boolean wordIsExist = wordService.existsByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(
                word.getUserId(),
                word.getCourseId(),
                word.getLanguageId(),
                word.getWordText(),
                word.getWordDescription(),
                word.getLanguageName()
        );
        if (existsByUserId && courseIsExist && languageIsExist) {
            if (wordIsExist) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                wordService.save(word);
                Optional<WordEntity> savedWord = wordService.findByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(
                        word.getUserId(),
                        word.getCourseId(),
                        word.getLanguageId(),
                        word.getWordText(),
                        word.getWordDescription(),
                        word.getLanguageName());
                if (savedWord.isPresent()) {
                    return new ResponseEntity<>(savedWord.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<WordEntity> udpateWord(@RequestBody WordEntity word) {
        Boolean existsByUserId = userService.existsByUserId(word.getUserId());
        Boolean courseIsExist = courseService.isExist(word.getUserId(), word.getCourseId());
        Boolean languageIsExist = languageService.isExist(word.getLanguageId(), word.getLanguageName());
        Boolean wordIsExist = wordService.existsByUserIdAndCourseIdAndLanguageIdAndWordTextAndWordDescriptionAndLanguageName(
                word.getUserId(),
                word.getCourseId(),
                word.getLanguageId(),
                word.getWordText(),
                word.getWordDescription(),
                word.getLanguageName()
        );
        if (existsByUserId && courseIsExist && languageIsExist) {
            if (wordIsExist) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                wordService.update(word);
                Optional<WordEntity> updatedWord = wordService.findByUserIdAndCourseIdAndWordId(word.getUserId(), word.getCourseId(), word.getWordId());
                if (updatedWord.isPresent()) {
                    return new ResponseEntity<>(updatedWord.get(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userId/{userId}/courseId/{courseId}/wordId/{wordId}")
    public ResponseEntity<?> deleteWord(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long wordId) {
        Boolean existsByUserId = userService.existsByUserId(userId);
        Boolean courseIsExist = courseService.isExist(userId, courseId);
        Boolean wordIsExist = wordService.isExist(wordId);
        if (existsByUserId && courseIsExist) {
            if (wordIsExist) {
                wordService.deleteByUserIdAndCourseIdAndWordId(userId, courseId, wordId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/userId/{userId}")
    public ResponseEntity<?> deleteAllWordsByUserId(@PathVariable Long userId) {
        Boolean existsByUserId = userService.existsByUserId(userId);
        if (existsByUserId) {
            wordService.deleteAllByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
