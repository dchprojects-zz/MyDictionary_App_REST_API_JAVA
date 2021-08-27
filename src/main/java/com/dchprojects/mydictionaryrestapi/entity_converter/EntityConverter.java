package com.dchprojects.mydictionaryrestapi.entity_converter;

import com.dchprojects.mydictionaryrestapi.domain.dto.CourseResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.LanguageResponse;
import com.dchprojects.mydictionaryrestapi.domain.dto.UserResponse;
import com.dchprojects.mydictionaryrestapi.domain.entity.CourseEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.LanguageEntity;
import com.dchprojects.mydictionaryrestapi.domain.entity.UserEntity;

public class EntityConverter {

    public static UserResponse userEntityToUserResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();

        userResponse.setUserId(userEntity.getUserId());
        userResponse.setNickname(userEntity.getNickname());

        return userResponse;
    }

    public static LanguageResponse languageEntityToLanguageResponse(LanguageEntity languageEntity) {
        LanguageResponse languageResponse = new LanguageResponse();

        languageResponse.setLanguageId(languageEntity.getLanguageId());
        languageResponse.setLanguageName(languageEntity.getLanguageName());

        return languageResponse;
    }

    public static CourseResponse courseEntityToLanguageResponse(CourseEntity courseEntity) {
        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setUserId(courseEntity.getUserId());
        courseResponse.setCourseId(courseEntity.getCourseId());
        courseResponse.setLanguageId(courseEntity.getLanguageId());
        courseResponse.setLanguageName(courseEntity.getLanguageName());

        return courseResponse;
    }

}
