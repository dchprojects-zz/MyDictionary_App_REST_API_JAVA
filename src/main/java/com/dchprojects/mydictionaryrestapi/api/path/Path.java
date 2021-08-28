package com.dchprojects.mydictionaryrestapi.api.path;

import com.dchprojects.mydictionaryrestapi.api.version.Version;

public class Path {

    private static final String FORWARD_SLASH = "/";
    private static final String API = "api";
    private static final String ACCOUNT_PATH_NAME = "account";
    private static final String ADMIN_PATH_NAME = "admin";
    private static final String AUTH_PATH_NAME = "auth";
    private static final String COURSES_PATH_NAME = "courses";
    private static final String JWT_PATH_NAME = "jwt";
    private static final String LANGUAGES_PATH_NAME = "languages";
    private static final String USERS_PATH_NAME = "users";
    private static final String WORDS_PATH_NAME = "words";

    private static final String CONFIGURED_API_PATH_WITH_DEFAULT_VERSION = FORWARD_SLASH + API + FORWARD_SLASH + Version.DEFAULT_VERSION + FORWARD_SLASH;

    public static final String REQUEST_PATH_API_ACCOUNT = CONFIGURED_API_PATH_WITH_DEFAULT_VERSION + ACCOUNT_PATH_NAME;
    public static final String REQUEST_PATH_API_ADMIN = CONFIGURED_API_PATH_WITH_DEFAULT_VERSION + ADMIN_PATH_NAME;
    public static final String REQUEST_PATH_API_AUTH = CONFIGURED_API_PATH_WITH_DEFAULT_VERSION + AUTH_PATH_NAME;
    public static final String REQUEST_PATH_API_COURSES = CONFIGURED_API_PATH_WITH_DEFAULT_VERSION + COURSES_PATH_NAME;
    public static final String REQUEST_PATH_API_JWT = CONFIGURED_API_PATH_WITH_DEFAULT_VERSION + JWT_PATH_NAME;
    public static final String REQUEST_PATH_API_LANGUAGES = CONFIGURED_API_PATH_WITH_DEFAULT_VERSION + LANGUAGES_PATH_NAME;
    public static final String REQUEST_PATH_API_USERS = CONFIGURED_API_PATH_WITH_DEFAULT_VERSION + USERS_PATH_NAME;
    public static final String REQUEST_PATH_API_WORDS = CONFIGURED_API_PATH_WITH_DEFAULT_VERSION + WORDS_PATH_NAME;

}
