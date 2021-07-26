DROP DATABASE IF EXISTS `my_dictionary_database`;

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

LOCK TABLES `language` WRITE;
INSERT INTO `language` (language_name) VALUES ('Spanish'),
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