CREATE DATABASE  IF NOT EXISTS `applicants_system`;
USE `applicants_system`;

--
-- Table structure for table `applicants`
--

DROP TABLE IF EXISTS `applicants`;

CREATE TABLE `applicants` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `first_name` varchar(45) DEFAULT NULL,
                              `last_name` varchar(45) DEFAULT NULL,
                              `email` varchar(45) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `applicants` VALUES
                             (1,'Greta','V','leslie@vu.lt'),
                             (2,'Julius','V','emma@vu.lt'),
                             (3,'Domantas','Gupta','avani@vu.lt'),
                             (4,'Olga','Petrova','yuri@vu.lt'),
                             (5,'Oleg','S','juan@vu.lt');

DROP TABLE IF EXISTS `companies`;

CREATE TABLE `companies` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(45) DEFAULT NULL,
                             `description` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `companies` VALUES
                            (1, 'UAB Lauma','Software company working with fish'),
                            (2, 'UAB Sound', 'Company working with sound systems'),
                            (3, 'UAB Demelita', 'Company working with demolition');

DROP TABLE IF EXISTS `applications`;

CREATE TABLE `applications` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `applicant_id` int NOT NULL,
                                `company_id` int NOT NULL,
                                PRIMARY KEY (`id`),
                                FOREIGN KEY (`applicant_id`) REFERENCES `applicants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `qualifications`;

CREATE TABLE `qualifications` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `name` varchar(45) DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `qualifications` VALUES
                                 (1, 'fisherman'),
                                 (2, 'builder'),
                                 (3, 'programmer');

DROP TABLE IF EXISTS `applicants_qualifications`;

CREATE TABLE `applicants_qualifications` (
                                             `id` int NOT NULL AUTO_INCREMENT,
                                             `qualification_id` int NOT NULL,
                                             `applicant_id` int NOT NULL,
                                             PRIMARY KEY (`id`),
                                             FOREIGN KEY (`applicant_id`) REFERENCES `applicants` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                             FOREIGN KEY (`qualification_id`) REFERENCES `qualifications` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;