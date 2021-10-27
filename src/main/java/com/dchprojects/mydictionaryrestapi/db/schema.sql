DROP DATABASE IF EXISTS `my_dictionary_database`;

CREATE DATABASE my_dictionary_database;

USE my_dictionary_database;

CREATE TABLE `users` (
                        user_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                        nickname VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (user_id)
);

CREATE TABLE `roles` (
                         `id` BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `name` VARCHAR(255) NOT NULL,
                         PRIMARY KEY (`id`)
);

CREATE TABLE `user_role` (
                             `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                             `user_id` BIGINT UNSIGNED NOT NULL,
                             `role_id` BIGINT(19) UNSIGNED NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `fk_security_user_id` (`user_id`),
                             KEY `fk_security_role_id` (`role_id`),
                             CONSTRAINT `fk_security_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
                             CONSTRAINT `fk_security_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

CREATE TABLE `languages` (
                            language_id BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT,
                            language_name VARCHAR(255) NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (language_id)
);

CREATE TABLE `courses` (
                          user_id BIGINT UNSIGNED NOT NULL,
                          course_id BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT,
                          language_id BIGINT(19) UNSIGNED NOT NULL,
                          language_name VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (course_id)
);

CREATE TABLE `words` (
                        user_id BIGINT UNSIGNED NOT NULL,
                        word_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                        course_id BIGINT(19) UNSIGNED NOT NULL,
                        language_id BIGINT(19) UNSIGNED NOT NULL,
                        word_text VARCHAR(500) NOT NULL,
                        word_description VARCHAR(500) NOT NULL,
                        language_name VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (word_id)
);