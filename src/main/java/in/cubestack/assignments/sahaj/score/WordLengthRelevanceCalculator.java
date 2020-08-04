package in.cubestack.assignments.sahaj.score;

import java.util.List;

public class WordLengthRelevanceCalculator implements RelevanceCalculator {

    @Override
    public int calculate(String input, List<String> keywords) {
        int currentScore = 0;
        String text = input.toLowerCase();
        for (String keyword : keywords) {
            if (hasKeyword(text, keyword)) {
                currentScore += scoreFor(keyword);
            } else if (isPlural(keyword) && hasKeyword(text, trimPluralCharacter(keyword))) {
                currentScore += scoreFor(keyword);
            }
        }
        return currentScore;
    }

    // This function should eventually be capable of ranking keywords,
    // for example "a" / "the" should be less and high context words such as endangered should be high
    private int scoreFor(String keyword) {
        return keyword.length();
    }

    private String trimPluralCharacter(String word) {
        return word.substring(0, word.length() - 1);
    }

    private boolean hasKeyword(String text, String keyword) {
        return text.contains(keyword);
    }

    // Dirty hack to check plural, but no point building dictionary for this. :)
    private boolean isPlural(String keyword) {
        return keyword.endsWith("s");
    }
}
