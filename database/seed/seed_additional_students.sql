USE crs_db;

-- Additional seed data: 30 students
-- S101-S115 = low CGPA / failed students
-- S116-S130 = passing students
-- Assumes course_components contains these component names for C101 and C102:
-- Assignment, Quiz, Final Exam

INSERT INTO students (student_id, first_name, last_name, major, study_year, email)
VALUES
('S101', 'Fail1', 'Student', 'Computer Science', 'Freshman', 's101@university.edu'),
('S102', 'Fail2', 'Student', 'Engineering', 'Sophomore', 's102@university.edu'),
('S103', 'Fail3', 'Student', 'Business', 'Junior', 's103@university.edu'),
('S104', 'Fail4', 'Student', 'Mathematics', 'Senior', 's104@university.edu'),
('S105', 'Fail5', 'Student', 'Physics', 'Freshman', 's105@university.edu'),
('S106', 'Fail6', 'Student', 'Statistics', 'Sophomore', 's106@university.edu'),
('S107', 'Fail7', 'Student', 'Literature', 'Junior', 's107@university.edu'),
('S108', 'Fail8', 'Student', 'History', 'Senior', 's108@university.edu'),
('S109', 'Fail9', 'Student', 'Philosophy', 'Freshman', 's109@university.edu'),
('S110', 'Fail10', 'Student', 'Biology', 'Sophomore', 's110@university.edu'),
('S111', 'Fail11', 'Student', 'Computer Science', 'Junior', 's111@university.edu'),
('S112', 'Fail12', 'Student', 'Engineering', 'Senior', 's112@university.edu'),
('S113', 'Fail13', 'Student', 'Business', 'Freshman', 's113@university.edu'),
('S114', 'Fail14', 'Student', 'Mathematics', 'Sophomore', 's114@university.edu'),
('S115', 'Fail15', 'Student', 'Physics', 'Junior', 's115@university.edu'),
('S116', 'Pass1', 'Student', 'Mathematics', 'Sophomore', 's116@university.edu'),
('S117', 'Pass2', 'Student', 'Physics', 'Junior', 's117@university.edu'),
('S118', 'Pass3', 'Student', 'Statistics', 'Senior', 's118@university.edu'),
('S119', 'Pass4', 'Student', 'Literature', 'Freshman', 's119@university.edu'),
('S120', 'Pass5', 'Student', 'History', 'Sophomore', 's120@university.edu'),
('S121', 'Pass6', 'Student', 'Philosophy', 'Junior', 's121@university.edu'),
('S122', 'Pass7', 'Student', 'Biology', 'Senior', 's122@university.edu'),
('S123', 'Pass8', 'Student', 'Computer Science', 'Freshman', 's123@university.edu'),
('S124', 'Pass9', 'Student', 'Engineering', 'Sophomore', 's124@university.edu'),
('S125', 'Pass10', 'Student', 'Business', 'Junior', 's125@university.edu'),
('S126', 'Pass11', 'Student', 'Mathematics', 'Senior', 's126@university.edu'),
('S127', 'Pass12', 'Student', 'Physics', 'Freshman', 's127@university.edu'),
('S128', 'Pass13', 'Student', 'Statistics', 'Sophomore', 's128@university.edu'),
('S129', 'Pass14', 'Student', 'Literature', 'Junior', 's129@university.edu'),
('S130', 'Pass15', 'Student', 'History', 'Senior', 's130@university.edu');

INSERT INTO student_course_results
(student_id, course_id, attempt_no, semester, academic_year, grade_letter, grade_point, total_score, final_status, credits_earned)
VALUES
('S101', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S101', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S102', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S102', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S103', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S103', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S104', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S104', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S105', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S105', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S106', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S106', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S107', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S107', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S108', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S108', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S109', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S109', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S110', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S110', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S111', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S111', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S112', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S112', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S113', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S113', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S114', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S114', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S115', 'C101', 1, 'Spring', '2025', 'F', 0.00, 40.00, 'FAIL', 0),
('S115', 'C102', 1, 'Spring', '2025', 'D', 1.00, 49.00, 'FAIL', 0),
('S116', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S116', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S117', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S117', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S118', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S118', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S119', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S119', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S120', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S120', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S121', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S121', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S122', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S122', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S123', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S123', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S124', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S124', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S125', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S125', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S126', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S126', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S127', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S127', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S128', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S128', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S129', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S129', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3),
('S130', 'C101', 1, 'Spring', '2025', 'B+', 3.30, 80.00, 'PASS', 3),
('S130', 'C102', 1, 'Spring', '2025', 'A-', 3.70, 85.00, 'PASS', 3);

INSERT INTO student_component_results
(student_id, course_id, component_id, attempt_no, score, status)
SELECT d.student_id, d.course_id, cc.component_id, d.attempt_no, d.score, d.status
FROM (
    SELECT 'S101' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S101' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S101' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S101' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S101' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S101' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S102' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S102' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S102' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S102' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S102' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S102' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S103' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S103' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S103' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S103' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S103' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S103' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S104' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S104' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S104' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S104' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S104' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S104' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S105' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S105' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S105' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S105' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S105' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S105' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S106' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S106' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S106' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S106' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S106' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S106' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S107' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S107' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S107' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S107' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S107' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S107' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S108' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S108' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S108' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S108' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S108' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S108' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S109' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S109' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S109' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S109' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S109' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S109' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S110' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S110' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S110' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S110' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S110' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S110' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S111' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S111' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S111' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S111' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S111' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S111' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S112' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S112' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S112' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S112' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S112' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S112' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S113' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S113' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S113' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S113' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S113' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S113' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S114' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S114' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S114' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S114' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S114' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S114' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S115' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 10.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S115' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 8.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S115' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 22.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S115' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 12.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S115' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 11.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S115' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 26.00 AS score, 'FAIL' AS status
    UNION ALL SELECT 'S116' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S116' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S116' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S116' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S116' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S116' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S117' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S117' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S117' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S117' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S117' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S117' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S118' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S118' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S118' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S118' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S118' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S118' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S119' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S119' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S119' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S119' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S119' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S119' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S120' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S120' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S120' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S120' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S120' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S120' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S121' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S121' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S121' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S121' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S121' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S121' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S122' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S122' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S122' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S122' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S122' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S122' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S123' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S123' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S123' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S123' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S123' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S123' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S124' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S124' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S124' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S124' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S124' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S124' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S125' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S125' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S125' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S125' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S125' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S125' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S126' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S126' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S126' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S126' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S126' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S126' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S127' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S127' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S127' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S127' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S127' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S127' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S128' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S128' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S128' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S128' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S128' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S128' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S129' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S129' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S129' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S129' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S129' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S129' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S130' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 24.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S130' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 17.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S130' AS student_id, 'C101' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 39.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S130' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Assignment' AS component_name, 26.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S130' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Quiz' AS component_name, 18.00 AS score, 'PASS' AS status
    UNION ALL SELECT 'S130' AS student_id, 'C102' AS course_id, 1 AS attempt_no, 'Final Exam' AS component_name, 41.00 AS score, 'PASS' AS status
) d
JOIN course_components cc
  ON cc.course_id = d.course_id
 AND cc.component_name = d.component_name;

INSERT INTO eligibility_checks
(student_id, cgpa, failed_course_count, eligible, remarks, checked_by)
VALUES
('S101', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S102', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S103', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S104', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S105', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S106', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S107', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S108', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S109', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S110', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S111', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S112', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S113', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S114', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S115', 0.50, 2, FALSE, 'Not eligible: CGPA is below 2.0 and the student failed 2 courses.', 2),
('S116', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S117', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S118', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S119', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S120', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S121', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S122', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S123', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S124', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S125', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S126', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S127', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S128', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S129', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2),
('S130', 3.50, 0, TRUE, 'Eligible: CGPA meets the minimum requirement and no failed courses were recorded.', 2);

-- End of additional seed data