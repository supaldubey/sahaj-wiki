package in.cubestack.assignments.sahaj.search;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KeywordSplitter {

    private static final Set<String> IGNORED_KEYWORDS = Set.of("why", "who", "when", "how", "what", "a", " ", "\"", "'");

    public List<String> findKeywords(String text) {
        String inputText = text.replace("?", "");
        return Arrays.stream(inputText.split(" "))
                .map(String::toLowerCase)
                .filter(s -> !IGNORED_KEYWORDS.contains(s.trim()))
                .map(s -> s.replace("\"", ""))
                .map(s -> s.replace("'", ""))
                .collect(Collectors.toList());
    }
}
