package in.cubestack.assignments.sahaj.score;

import java.util.List;

public interface RelevanceCalculator {
    int calculate(String sentence, List<String> keywords);
}
