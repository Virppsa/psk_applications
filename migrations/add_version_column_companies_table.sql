USE `applicants_system`;

ALTER TABLE `companies` ADD COLUMN LOCK_VERSION INT DEFAULT 0;