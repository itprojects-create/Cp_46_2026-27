package com.cs.SmartHireAi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtsResponse {
    private int score;
    private List<String> missingSkills;
    private String suggestions;
    private List<String> matchedKeywords;

    public AtsResponse(int score, List<String> missingSkills, String suggestions) {
        this.score = score;
        this.missingSkills = missingSkills;
        this.suggestions = suggestions;
    }
}
