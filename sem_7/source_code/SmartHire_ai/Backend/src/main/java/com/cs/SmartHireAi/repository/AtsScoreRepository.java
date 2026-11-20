package com.cs.SmartHireAi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AtsScoreRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save(Long applicantId, Long jobId, int score) {
        if (applicantId == null || jobId == null) {
            return;
        }

        try {
            Long resolvedApplicantId = applicantId;

            // Check if applicantId directly exists in users table
            Integer userCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM users WHERE id = ?", Integer.class, applicantId);

            // If not found in users table, check if applicantId is an application_id from applications table
            if (userCount == null || userCount == 0) {
                List<Long> actualUserIds = jdbcTemplate.query(
                        "SELECT applicant_id FROM applications WHERE id = ?",
                        (rs, rn) -> rs.getLong("applicant_id"), applicantId);
                if (!actualUserIds.isEmpty()) {
                    resolvedApplicantId = actualUserIds.get(0);
                    userCount = 1;
                }
            }

            // Check if jobId exists in jobs table
            Integer jobCount = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM jobs WHERE id = ?", Integer.class, jobId);

            // Only insert/update if both applicant (user) and job exist
            if (userCount != null && userCount > 0 && jobCount != null && jobCount > 0) {
                String checkSql = "SELECT id FROM ats_scores WHERE applicant_id = ? AND job_id = ?";
                List<Map<String, Object>> existing = jdbcTemplate.queryForList(checkSql, resolvedApplicantId, jobId);

                if (existing.isEmpty()) {
                    jdbcTemplate.update(
                            "INSERT INTO ats_scores (applicant_id, job_id, score, calculated_at) VALUES (?, ?, ?, NOW())",
                            resolvedApplicantId, jobId, score);
                } else {
                    jdbcTemplate.update(
                            "UPDATE ats_scores SET score = ?, calculated_at = NOW() WHERE applicant_id = ? AND job_id = ?",
                            score, resolvedApplicantId, jobId);
                }
            } else {
                System.out.println("Notice: Skipping ats_scores insert because user (" + applicantId 
                        + ") or job (" + jobId + ") does not exist in database.");
            }
        } catch (Exception e) {
            System.err.println("Warning: could not save ATS score to database: " + e.getMessage());
        }
    }

    public List<Map<String, Object>> findByApplicantId(Long applicantId) {
        return jdbcTemplate.queryForList(
                "SELECT * FROM ats_scores WHERE applicant_id = ? ORDER BY calculated_at DESC", applicantId);
    }
}
