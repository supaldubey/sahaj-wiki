package in.cubestack.assignments.sahaj;

import in.cubestack.assignments.sahaj.io.ConsoleResultListener;
import in.cubestack.assignments.sahaj.io.FileReader;
import in.cubestack.assignments.sahaj.match.PassageQuestionAnswerMatcher;
import in.cubestack.assignments.sahaj.result.ResultListener;
import in.cubestack.assignments.sahaj.score.RelevanceCalculator;
import in.cubestack.assignments.sahaj.score.WordLengthRelevanceCalculator;
import in.cubestack.assignments.sahaj.search.KeywordSplitter;
import in.cubestack.assignments.sahaj.search.SentenceMatcher;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {

    private static final String FILE_PATH = "/input.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {
        new Main().solve();
    }

    private void solve() throws IOException, URISyntaxException {
        ResultListener resultListener = new ConsoleResultListener();
        RelevanceCalculator relevanceCalculator = new WordLengthRelevanceCalculator();
        KeywordSplitter keywordSplitter = new KeywordSplitter();
        SentenceMatcher sentenceMatcher = new SentenceMatcher(relevanceCalculator, keywordSplitter);
        FileReader fileReader = new FileReader();

        PassageQuestionAnswerMatcher passageQuestionAnswerMatcher = new PassageQuestionAnswerMatcher(resultListener, sentenceMatcher);
        List<String> lines = fileReader.readContent(FILE_PATH);

        passageQuestionAnswerMatcher.provideAnswers(lines);
    }
}
