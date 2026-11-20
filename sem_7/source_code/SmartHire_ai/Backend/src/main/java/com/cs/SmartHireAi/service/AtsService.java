package com.cs.SmartHireAi.service;

import com.cs.SmartHireAi.model.AtsResponse;
import com.cs.SmartHireAi.repository.ApplicantProfileRepository;
import com.cs.SmartHireAi.repository.AtsScoreRepository;
import com.cs.SmartHireAi.utils.ResumeParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AtsService {

    private final ApplicantProfileRepository applicantProfileRepo;
    private final AtsScoreRepository atsScoreRepo;

    public AtsResponse calculateATS(MultipartFile resume, String jobDescription,
                                    Long applicantId, Long jobId) throws Exception {

        InputStream is = resume.getInputStream();
        String resumeText = (resume.getOriginalFilename() != null
                && resume.getOriginalFilename().endsWith(".pdf"))
                ? ResumeParser.parsePDF(is)
                : ResumeParser.parseDOCX(is);

        resumeText = resumeText != null ? resumeText.toLowerCase() : "";
        jobDescription = jobDescription != null ? jobDescription.toLowerCase() : "";

        Set<String> resumeWords = new HashSet<>(Arrays.asList(resumeText.split("\\W+")));
        String[] jobWords = jobDescription.split("\\W+");

        List<String> missingSkills = new ArrayList<>();
        List<String> matchedKeywords = new ArrayList<>();
        int matched = 0;
        int validJobWords = 0;

        for (String word : jobWords) {
            String clean = word.trim();
            if (clean.length() <= 1) continue; // ignore single character or whitespace
            validJobWords++;
            if (resumeWords.contains(clean)) {
                matched++;
                if (!matchedKeywords.contains(clean)) {
                    matchedKeywords.add(clean);
                }
            } else {
                if (!missingSkills.contains(clean)) {
                    missingSkills.add(clean);
                }
            }
        }

        int score = validJobWords > 0 ? (int) ((matched * 100.0) / validJobWords) : 0;

        // Save per-job ATS score to ats_scores table if applicantId and jobId provided
        if (applicantId != null && jobId != null) {
            atsScoreRepo.save(applicantId, jobId, score);
            applicantProfileRepo.updateAtsScore(applicantId, score);
        }

        return new AtsResponse(score, missingSkills, generateSuggestions(missingSkills), matchedKeywords);
    }

    private String generateSuggestions(List<String> missing) {
        if (missing.isEmpty()) return "Excellent resume match!";
        return "Add these keywords to improve your ATS score: " + String.join(", ", missing);
    }
}
