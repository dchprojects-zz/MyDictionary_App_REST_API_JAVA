CREATE DATABASE my_dictionary_database;

USE my_dictionary_database;

UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `language`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `word`;

CREATE TABLE `user` (
                        user_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                        nickname TEXT NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (user_id)
);

CREATE TABLE `language` (
                            language_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                            language_name TEXT NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (language_id)
);

CREATE TABLE `course` (
                          user_id BIGINT UNSIGNED NOT NULL,
                          course_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          language_id BIGINT UNSIGNED NOT NULL,
                          language_name TEXT NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (course_id)
);

CREATE TABLE `word` (
                        user_id BIGINT UNSIGNED NOT NULL,
                        word_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                        course_id BIGINT UNSIGNED NOT NULL,
                        word_text TEXT NOT NULL,
                        word_description TEXT NOT NULL,
                        language_name TEXT NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (word_id)
);