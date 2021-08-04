DROP DATABASE IF EXISTS `my_dictionary_database`;

CREATE DATABASE my_dictionary_database;

USE my_dictionary_database;

UNLOCK TABLES;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `languages`;
DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `words`;

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
                             `id` BIGINT(19) UNSIGNED NOT NULL AUTO_INCREMENT,
                             `user_id` BIGINT(19) UNSIGNED NOT NULL,
                             `role_id` BIGINT(19) UNSIGNED NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `fk_security_user_id` (`user_id`),
                             KEY `fk_security_role_id` (`role_id`),
                             CONSTRAINT `fk_security_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
                             CONSTRAINT `fk_security_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

CREATE TABLE `languages` (
                            language_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                            language_name VARCHAR(255) NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (language_id)
);

CREATE TABLE `courses` (
                          user_id BIGINT UNSIGNED NOT NULL,
                          course_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          language_id BIGINT UNSIGNED NOT NULL,
                          language_name VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (course_id)
);

CREATE TABLE `words` (
                        user_id BIGINT UNSIGNED NOT NULL,
                        word_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                        course_id BIGINT UNSIGNED NOT NULL,
                        language_id BIGINT UNSIGNED NOT NULL,
                        word_text TEXT NOT NULL,
                        word_description TEXT NOT NULL,
                        language_name VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (word_id)
);

LOCK TABLES `languages` WRITE;
INSERT INTO `languages` (language_name) VALUES ('Spanish'),
                                              ('English'),
                                              ('Hindi'),
                                              ('Arabic'),
                                              ('Portuguese'),
                                              ('Bengali'),
                                              ('Russian'),
                                              ('Japanese'),
                                              ('Punjabi'),
                                              ('German'),
                                              ('Javanese'),
                                              ('Wu (inc.Shanghainese)'),
                                              ('Malay (inc.Indonesian and Malaysian)'),
                                              ('Telugu'),
                                              ('Vietnamese'),
                                              ('Korean'),
                                              ('French'),
                                              ('Marathi'),
                                              ('Tamil'),
                                              ('Urdu'),
                                              ('Turkish'),
                                              ('Italian'),
                                              ('Yue (inc.Cantonese)'),
                                              ('Thai'),
                                              ('Gujarati'),
                                              ('Jin'),
                                              ('Southern Min (inc.Hokkien and Teochew)'),
                                              ('Persian'),
                                              ('Polish'),
                                              ('Pashto'),
                                              ('Kannada'),
                                              ('Xiang'),
                                              ('Malayalam'),
                                              ('Sundanese'),
                                              ('Hausa'),
                                              ('Odia (Oriya)'),
                                              ('Burmese'),
                                              ('Hakka'),
                                              ('Ukrainian'),
                                              ('Bhojpuri'),
                                              ('Tagalog (Filipino)'),
                                              ('Yoruba'),
                                              ('Maithili'),
                                              ('Uzbek'),
                                              ('Sindhi'),
                                              ('Amharic'),
                                              ('Fula'),
                                              ('Romanian'),
                                              ('Oromo'),
                                              ('Igbo'),
                                              ('Azerbaijani'),
                                              ('Awadhi'),
                                              ('Gan'),
                                              ('Cebuano (Visayan)'),
                                              ('Dutch'),
                                              ('Kurdish'),
                                              ('Serbo-Croatian'),
                                              ('Malagasy'),
                                              ('Saraiki'),
                                              ('Nepali'),
                                              ('Sinhala'),
                                              ('Chittagonian'),
                                              ('Zhuang'),
                                              ('Khmer'),
                                              ('Turkmen'),
                                              ('Assamese'),
                                              ('Madurese'),
                                              ('Somali'),
                                              ('Marwari'),
                                              ('Magahi'),
                                              ('Haryanvi'),
                                              ('Hungarian'),
                                              ('Chhattisgarhi'),
                                              ('Greek'),
                                              ('Chewa'),
                                              ('Deccan'),
                                              ('Akan'),
                                              ('Kazakh'),
                                              ('Northern Min[disputed–discuss]'),
                                              ('Sylheti'),
                                              ('Zulu'),
                                              ('Czech'),
                                              ('Kinyarwanda'),
                                              ('Dhundhari'),
                                              ('Haitian Creole'),
                                              ('Eastern Min (inc.Fuzhou dialect)'),
                                              ('Ilocano'),
                                              ('Quechua'),
                                              ('Kirundi'),
                                              ('Swedish'),
                                              ('Hmong'),
                                              ('Shona'),
                                              ('Uyghur'),
                                              ('Hiligaynon/Ilonggo (Visayan)'),
                                              ('Mossi'),
                                              ('Xhosa'),
                                              ('Belarusian'),
                                              ('Balochi'),
                                              ('Konkani');
UNLOCK TABLES;

LOCK TABLES `roles` WRITE;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
UNLOCK TABLES;