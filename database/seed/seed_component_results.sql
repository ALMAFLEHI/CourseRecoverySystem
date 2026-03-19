USE crs_db;

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C101', cc.component_id, 1, 24.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C101' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C101', cc.component_id, 1, 14.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C101' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C101', cc.component_id, 1, 40.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C101' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C102', cc.component_id, 1, 12.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C102' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C102', cc.component_id, 1, 8.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C102' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C102', cc.component_id, 1, 22.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C102' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C103', cc.component_id, 1, 27.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C103' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C103', cc.component_id, 1, 17.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C103' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S001', 'C103', cc.component_id, 1, 44.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C103' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C101', cc.component_id, 1, 19.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C101' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C101', cc.component_id, 1, 12.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C101' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C101', cc.component_id, 1, 34.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C101' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C104', cc.component_id, 1, 10.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C104' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C104', cc.component_id, 1, 7.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C104' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C104', cc.component_id, 1, 21.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C104' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C105', cc.component_id, 1, 13.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C105' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C105', cc.component_id, 1, 6.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C105' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C105', cc.component_id, 1, 22.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C105' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C106', cc.component_id, 1, 11.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C106' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C106', cc.component_id, 1, 5.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C106' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S002', 'C106', cc.component_id, 1, 20.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C106' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C107', cc.component_id, 1, 25.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C107' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C107', cc.component_id, 1, 16.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C107' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C107', cc.component_id, 1, 41.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C107' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C108', cc.component_id, 1, 28.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C108' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C108', cc.component_id, 1, 18.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C108' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C108', cc.component_id, 1, 44.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C108' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C109', cc.component_id, 1, 22.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C109' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C109', cc.component_id, 1, 15.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C109' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S003', 'C109', cc.component_id, 1, 39.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C109' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C110', cc.component_id, 1, 11.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C110' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C110', cc.component_id, 1, 8.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C110' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C110', cc.component_id, 1, 21.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C110' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C111', cc.component_id, 1, 12.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C111' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C111', cc.component_id, 1, 9.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C111' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C111', cc.component_id, 1, 22.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C111' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C112', cc.component_id, 1, 10.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C112' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C112', cc.component_id, 1, 8.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C112' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C112', cc.component_id, 1, 21.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C112' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C113', cc.component_id, 1, 9.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C113' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C113', cc.component_id, 1, 7.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C113' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S004', 'C113', cc.component_id, 1, 19.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C113' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S005', 'C114', cc.component_id, 1, 25.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C114' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S005', 'C114', cc.component_id, 1, 16.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C114' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S005', 'C114', cc.component_id, 1, 6.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C114' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S005', 'C114', cc.component_id, 2, 49.00, 'FAIL'
FROM course_components cc
WHERE cc.course_id = 'C114' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S005', 'C114', cc.component_id, 3, 23.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C114' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S005', 'C114', cc.component_id, 3, 15.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C114' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S005', 'C114', cc.component_id, 3, 40.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C114' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C115', cc.component_id, 1, 26.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C115' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C115', cc.component_id, 1, 17.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C115' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C115', cc.component_id, 1, 42.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C115' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C116', cc.component_id, 1, 24.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C116' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C116', cc.component_id, 1, 16.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C116' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C116', cc.component_id, 1, 39.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C116' AND cc.component_name = 'Final Exam';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C117', cc.component_id, 1, 20.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C117' AND cc.component_name = 'Assignment';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C117', cc.component_id, 1, 13.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C117' AND cc.component_name = 'Quiz';

INSERT INTO student_component_results
    (student_id, course_id, component_id, attempt_no, score, status)
SELECT 'S006', 'C117', cc.component_id, 1, 35.00, 'PASS'
FROM course_components cc
WHERE cc.course_id = 'C117' AND cc.component_name = 'Final Exam';
