CREATE DATABASE IF NOT EXISTS crs_db;
USE crs_db;

-- =========================================
-- 1) USERS
-- =========================================
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('COURSE_ADMIN', 'ACADEMIC_OFFICER') NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 2) STUDENTS
-- =========================================
CREATE TABLE IF NOT EXISTS students (
    student_id VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    major VARCHAR(100) NOT NULL,
    study_year INT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 3) COURSES
-- =========================================
CREATE TABLE IF NOT EXISTS courses (
    course_id VARCHAR(20) PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    semester INT NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    capacity INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 4) COURSE COMPONENTS
-- =========================================
CREATE TABLE IF NOT EXISTS course_components (
    component_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id VARCHAR(20) NOT NULL,
    component_name VARCHAR(100) NOT NULL,
    weightage DECIMAL(5,2) NOT NULL,
    component_type VARCHAR(50),
    pass_required BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_course_components_course
        FOREIGN KEY (course_id) REFERENCES courses(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT uq_course_component_name
        UNIQUE (course_id, component_name),

    CONSTRAINT chk_component_weightage
        CHECK (weightage >= 0 AND weightage <= 100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;