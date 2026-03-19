USE crs_db;

-- =========================================
-- 7) RECOVERY PLANS
-- =========================================
CREATE TABLE IF NOT EXISTS recovery_plans (
    plan_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    course_id VARCHAR(20) NOT NULL,
    based_on_result_id INT NOT NULL,
    attempt_no TINYINT NOT NULL,
    recommendation_text TEXT NOT NULL,
    plan_status ENUM('DRAFT', 'ACTIVE', 'COMPLETED', 'CLOSED') NOT NULL DEFAULT 'ACTIVE',
    created_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_recovery_plans_student
        FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_recovery_plans_course
        FOREIGN KEY (course_id) REFERENCES courses(course_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_recovery_plans_result
        FOREIGN KEY (based_on_result_id) REFERENCES student_course_results(result_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_recovery_plans_created_by
        FOREIGN KEY (created_by) REFERENCES users(user_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 8) RECOVERY MILESTONES
-- =========================================
CREATE TABLE IF NOT EXISTS recovery_milestones (
    milestone_id INT AUTO_INCREMENT PRIMARY KEY,
    plan_id INT NOT NULL,
    week_label VARCHAR(50) NOT NULL,
    task_title VARCHAR(150) NOT NULL,
    description TEXT,
    due_date DATE NOT NULL,
    status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED', 'OVERDUE') NOT NULL DEFAULT 'PENDING',
    progress_percent DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_recovery_milestones_plan
        FOREIGN KEY (plan_id) REFERENCES recovery_plans(plan_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 9) RECOVERY PROGRESS LOGS
-- =========================================
CREATE TABLE IF NOT EXISTS recovery_progress_logs (
    progress_log_id INT AUTO_INCREMENT PRIMARY KEY,
    plan_id INT NOT NULL,
    milestone_id INT NULL,
    updated_by INT NOT NULL,
    note TEXT NOT NULL,
    grade_entry DECIMAL(5,2) NULL,
    status_after_update ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED', 'OVERDUE') NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_recovery_progress_logs_plan
        FOREIGN KEY (plan_id) REFERENCES recovery_plans(plan_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_recovery_progress_logs_milestone
        FOREIGN KEY (milestone_id) REFERENCES recovery_milestones(milestone_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,

    CONSTRAINT fk_recovery_progress_logs_updated_by
        FOREIGN KEY (updated_by) REFERENCES users(user_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 10) ELIGIBILITY CHECKS
-- =========================================
CREATE TABLE IF NOT EXISTS eligibility_checks (
    check_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    cgpa DECIMAL(4,2) NOT NULL,
    failed_course_count INT NOT NULL,
    eligible BOOLEAN NOT NULL,
    remarks TEXT,
    checked_by INT NOT NULL,
    checked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_eligibility_checks_student
        FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_eligibility_checks_checked_by
        FOREIGN KEY (checked_by) REFERENCES users(user_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 11) ENROLMENTS
-- =========================================
CREATE TABLE IF NOT EXISTS enrolments (
    enrolment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    eligibility_check_id INT NOT NULL,
    approved_by INT NOT NULL,
    enrolment_status ENUM('PENDING', 'APPROVED', 'REJECTED') NOT NULL DEFAULT 'PENDING',
    remarks TEXT,
    approved_at TIMESTAMP NULL,

    CONSTRAINT fk_enrolments_student
        FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_enrolments_eligibility_check
        FOREIGN KEY (eligibility_check_id) REFERENCES eligibility_checks(check_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_enrolments_approved_by
        FOREIGN KEY (approved_by) REFERENCES users(user_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 12) PERFORMANCE REPORT LOGS
-- =========================================
CREATE TABLE IF NOT EXISTS performance_report_logs (
    report_log_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    semester VARCHAR(20) NOT NULL,
    academic_year VARCHAR(20) NOT NULL,
    generated_by INT NOT NULL,
    summary_note TEXT,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_performance_report_logs_student
        FOREIGN KEY (student_id) REFERENCES students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_performance_report_logs_generated_by
        FOREIGN KEY (generated_by) REFERENCES users(user_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 13) EMAIL LOGS
-- =========================================
CREATE TABLE IF NOT EXISTS email_logs (
    email_log_id INT AUTO_INCREMENT PRIMARY KEY,
    recipient_email VARCHAR(100) NOT NULL,
    subject VARCHAR(150) NOT NULL,
    message_type ENUM('ACCOUNT_CREATED', 'PASSWORD_RESET', 'RECOVERY_PLAN', 'MILESTONE_REMINDER', 'REPORT') NOT NULL,
    related_entity_type VARCHAR(50),
    related_entity_id INT NULL,
    send_status ENUM('SENT', 'FAILED', 'QUEUED') NOT NULL DEFAULT 'QUEUED',
    sent_by_user_id INT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_email_logs_sent_by_user
        FOREIGN KEY (sent_by_user_id) REFERENCES users(user_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- =========================================
-- 14) AUDIT LOGS
-- =========================================
CREATE TABLE IF NOT EXISTS audit_logs (
    audit_log_id INT AUTO_INCREMENT PRIMARY KEY,
    actor_user_id INT NULL,
    action_type VARCHAR(100) NOT NULL,
    entity_type VARCHAR(50) NOT NULL,
    entity_id INT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_audit_logs_actor_user
        FOREIGN KEY (actor_user_id) REFERENCES users(user_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;