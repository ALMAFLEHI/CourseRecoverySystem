package com.crs.service;

import com.crs.dao.AuditLogDao;
import com.crs.model.AuditLog;
import com.crs.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DashboardService {

    private static final String COUNT_USERS_SQL = "SELECT COUNT(*) FROM users";
    private static final String COUNT_STUDENTS_SQL = "SELECT COUNT(*) FROM students";
    private static final String COUNT_COURSES_SQL = "SELECT COUNT(*) FROM courses";
    private static final String COUNT_RECOVERY_PLANS_SQL = "SELECT COUNT(*) FROM recovery_plans";
    private static final String COUNT_EMAIL_LOGS_SQL = "SELECT COUNT(*) FROM email_logs";

    private static final String COUNT_INELIGIBLE_LATEST_SQL = """
            SELECT COUNT(*)
            FROM (
                SELECT ec.student_id
                FROM eligibility_checks ec
                INNER JOIN (
                    SELECT student_id, MAX(check_id) AS max_check_id
                    FROM eligibility_checks
                    GROUP BY student_id
                ) latest
                ON ec.student_id = latest.student_id
                AND ec.check_id = latest.max_check_id
                WHERE ec.eligible = FALSE
           ) AS latest_ineligible
            """;

    private final AuditLogDao auditLogDao = new AuditLogDao();

    public int getTotalUsers() {
        return getCount(COUNT_USERS_SQL);
    }

    public int getTotalStudents() {
        return getCount(COUNT_STUDENTS_SQL);
    }

    public int getTotalCourses() {
        return getCount(COUNT_COURSES_SQL);
    }

    public int getTotalRecoveryPlans() {
        return getCount(COUNT_RECOVERY_PLANS_SQL);
    }

    public int getTotalEmailLogs() {
        return getCount(COUNT_EMAIL_LOGS_SQL);
    }

    public int getTotalIneligibleStudents() {
        return getCount(COUNT_INELIGIBLE_LATEST_SQL);
    }

    public List<AuditLog> getRecentAuditLogs(int limit) {
        if (limit <= 0) {
            limit = 5;
        }
        return auditLogDao.findRecent(limit);
    }

    private int getCount(String sql) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting dashboard count", e);
        }

        return 0;
    }
}
