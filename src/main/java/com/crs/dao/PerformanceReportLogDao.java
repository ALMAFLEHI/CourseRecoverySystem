package com.crs.dao;

import com.crs.model.PerformanceReportLog;
import com.crs.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerformanceReportLogDao {

    private static final String INSERT_SQL = """
            INSERT INTO performance_report_logs
            (student_id, semester, academic_year, generated_by, summary_note)
            VALUES (?, ?, ?, ?, ?)
            """;

    private static final String FIND_BY_STUDENT_ID_SQL = """
            SELECT report_log_id, student_id, semester, academic_year, generated_by, summary_note, generated_at
            FROM performance_report_logs
            WHERE student_id = ?
            ORDER BY report_log_id DESC
            """;

    private static final String FIND_LATEST_BY_STUDENT_ID_SQL = """
            SELECT report_log_id, student_id, semester, academic_year, generated_by, summary_note, generated_at
            FROM performance_report_logs
            WHERE student_id = ?
            ORDER BY report_log_id DESC
            LIMIT 1
            """;

    public boolean insert(PerformanceReportLog reportLog) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setString(1, reportLog.getStudentId());
            ps.setString(2, reportLog.getSemester());
            ps.setString(3, reportLog.getAcademicYear());
            ps.setInt(4, reportLog.getGeneratedBy());
            ps.setString(5, reportLog.getSummaryNote());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting performance report log", e);
        }
    }

    public List<PerformanceReportLog> findByStudentId(String studentId) {
        List<PerformanceReportLog> logs = new ArrayList<>();

        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_STUDENT_ID_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    logs.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding performance report logs by student id", e);
        }

        return logs;
    }

    public PerformanceReportLog findLatestByStudentId(String studentId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_LATEST_BY_STUDENT_ID_SQL)) {

            ps.setString(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding latest performance report log", e);
        }

        return null;
    }

    private PerformanceReportLog mapRow(ResultSet rs) throws SQLException {
        PerformanceReportLog log = new PerformanceReportLog();
        log.setReportLogId(rs.getInt("report_log_id"));
        log.setStudentId(rs.getString("student_id"));
        log.setSemester(rs.getString("semester"));
        log.setAcademicYear(rs.getString("academic_year"));
        log.setGeneratedBy(rs.getInt("generated_by"));
        log.setSummaryNote(rs.getString("summary_note"));
        log.setGeneratedAt(rs.getTimestamp("generated_at"));
        return log;
    }
}
