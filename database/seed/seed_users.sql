USE crs_db;

INSERT INTO users (full_name, email, username, password_hash, role, status)
VALUES
('System Administrator', 'admin@crs.local', 'admin', SHA2('Admin@123', 256), 'COURSE_ADMIN', 'ACTIVE'),
('Academic Officer', 'officer@crs.local', 'officer', SHA2('Officer@123', 256), 'ACADEMIC_OFFICER', 'ACTIVE');
