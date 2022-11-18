DROP DATABASE IF EXISTS `clinic_management_system_db`;

CREATE DATABASE `clinic_management_system_db`CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE `clinic_management_system_db`;

CREATE TABLE `user` (
    `id` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(50) NOT NULL,
    `registration_date` DATE NOT NULL DEFAULT (CURRENT_DATE),
    `email` VARCHAR(255) UNIQUE	,
    `phone` VARCHAR(15) NOT NULL,
    `date_of_birth` DATE NOT NULL,
    `gender` ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    `role` ENUM('ADMIN', 'DOCTOR', 'PATIENT') NOT NULL,
    `status` ENUM('ACTIVE', 'CLOSED') NOT NULL DEFAULT 'ACTIVE',
    PRIMARY KEY (`id`)
);		

CREATE TABLE `address` (
    `id` BIGINT AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `street` VARCHAR(255),
    `city` VARCHAR(100) NOT NULL,
    `state` VARCHAR(100) NOT NULL,
    `country` VARCHAR(100),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);

CREATE TABLE `doctor_specialist` (
    `id` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    `description` VARCHAR(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `doctor` (
    `id` BIGINT,
    `specialist_id` BIGINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`specialist_id`) REFERENCES `doctor_specialist` (`id`)
);

CREATE TABLE `patient` (
    `id` BIGINT,
    `blood_group` ENUM('A_PLUS', 'A_MINUS', 'B_PLUS', 'B_MINUS', 'AB_PLUS', 'AB_MINUS', 'O_PLUS', 'O_MINUS') NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `admin` (
    `id` BIGINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `user` (`id`)
);

CREATE TABLE `disease` (
    `id` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    `description` VARCHAR(255),
    `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`id`)
);

CREATE TABLE `medicine` (
    `id` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    `description` VARCHAR(255),
    `deleted` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`id`)
);

CREATE TABLE `prescription` (
    `id` BIGINT AUTO_INCREMENT,
    `visit_date` DATE NOT NULL DEFAULT (CURRENT_DATE),
    `visit_time` TIME NOT NULL DEFAULT (CURRENT_TIME),
    `next_visit_date` DATE,
    `patient_id` BIGINT NOT NULL,
    `doctor_id` BIGINT NOT NULL,
    `systolic_blood_pressure` INT,
    `diastolic_blood_pressure` INT,
    `temperature` FLOAT,
    `pulse_rate` INT,
    `note` TEXT(500),
    PRIMARY KEY (`id`)
);

CREATE TABLE `medicine_dosage` (
    `id` BIGINT AUTO_INCREMENT,
    `prescription_id` BIGINT,
    `medicine_id` BIGINT NOT NULL,
    `morning_dosage` FLOAT NOT NULL DEFAULT 0,
    `afternoon_dosage` FLOAT NOT NULL DEFAULT 0,
    `evening_dosage` FLOAT NOT NULL DEFAULT 0,
    `night_dosage` FLOAT NOT NULL DEFAULT 0,
    `quantity` INT NOT NULL,
    `duration` INT NOT NULL,
    `comment` VARCHAR(255),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`id`)
);

