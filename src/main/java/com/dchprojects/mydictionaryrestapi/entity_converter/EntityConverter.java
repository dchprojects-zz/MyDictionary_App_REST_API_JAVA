package com.dchprojects.mydictionaryrestapi.entity_converter;

import com.dchprojects.mydictionaryrestapi.domain.dto.CourseResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.LanguageResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.UserResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.WordResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.WordEntity;

public class EntityConverter {

    public static UserResponse userEntityToUserResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();

        userResponse.setUserId(userEntity.getUserId());
        userResponse.setNickname(userEntity.getNickname());
        userResponse.setCreatedAt(userEntity.getCreatedAt());

        return userResponse;
    }

    public static LanguageResponse languageEntityToLanguageResponse(LanguageEntity languageEntity) {
        LanguageResponse languageResponse = new LanguageResponse();

        languageResponse.setLanguageId(languageEntity.getLanguageId());
        languageResponse.setLanguageName(languageEntity.getLanguageName());

        return languageResponse;
    }

    public static CourseResponse courseEntityToCourseResponse(CourseEntity courseEntity) {
        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setUserId(courseEntity.getUserId());
        courseResponse.setCourseId(courseEntity.getCourseId());
        courseResponse.setLanguageId(courseEntity.getLanguageId());
        courseResponse.setLanguageName(courseEntity.getLanguageName());
        courseResponse.setCreatedAt(courseEntity.getCreatedAt());

        return courseResponse;
    }

    public static WordResponse wordEntityToWordResponse(WordEntity wordEntity) {
        WordResponse wordResponse = new WordResponse();

        wordResponse.setUserId(wordEntity.getUserId());
        wordResponse.setWordId(wordEntity.getWordId());
        wordResponse.setCourseId(wordEntity.getCourseId());
        wordResponse.setLanguageId(wordEntity.getLanguageId());
        wordResponse.setWordText(wordEntity.getWordText());
        wordResponse.setWordDescription(wordEntity.getWordDescription());
        wordResponse.setLanguageName(wordEntity.getLanguageName());
        wordResponse.setCreatedAt(wordEntity.getCreatedAt());

        return wordResponse;
    }

}
