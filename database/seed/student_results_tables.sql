USE crs_db;

-- =========================================
-- 5) STUDENT COURSE RESULTS
-- =========================================
CREATE TABLE IF NOT EXISTS student_course_results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    course_id VARCHAR(20) NOT NULL,
    attempt_no TINYINT NOT NULL,
    semester VARCHAR(20) NOT NULL,
    academic_year VARCHAR(20) NOT NULL,
    grade_letter VARCHAR(5) NOT NULL,
    grade_point DECIMAL(3,2) NOT NULL,
    total_score DECIMAL(5,2) NOT NULL,
    final_status ENUM('PASS', 'FAIL') NOT NULL,
    credits_earned INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_results_student
        FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_results_course
        FOREIGN KEY (course_id) REFERENCES courses(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT uq_student_course_attempt
        UNIQUE (student_id, course_id, attempt_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 6) STUDENT COMPONENT RESULTS
-- =========================================
CREATE TABLE IF NOT EXISTS student_component_results (
    component_result_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    course_id VARCHAR(20) NOT NULL,
    component_id INT NOT NULL,
    attempt_no TINYINT NOT NULL,
    score DECIMAL(5,2) NOT NULL,
    status ENUM('PASS', 'FAIL', 'PENDING') NOT NULL,
    submitted_at TIMESTAMP NULL DEFAULT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_component_results_student
        FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_component_results_course
        FOREIGN KEY (course_id) REFERENCES courses(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_component_results_component
        FOREIGN KEY (component_id) REFERENCES course_components(component_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT uq_student_component_attempt
        UNIQUE (student_id, course_id, component_id, attempt_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
