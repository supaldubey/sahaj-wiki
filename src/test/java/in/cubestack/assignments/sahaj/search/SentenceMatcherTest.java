package in.cubestack.assignments.sahaj.search;

import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.domain.Sentence;
import in.cubestack.assignments.sahaj.score.WordLengthRelevanceCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class SentenceMatcherTest {

    private SentenceMatcher sentenceMatcher;
    private Question question;
    private List<Sentence> sentences;

    @Before
    public void init() {
        question = new Question("Which Zebras are endangered?");
        sentenceMatcher = new SentenceMatcher(new WordLengthRelevanceCalculator(), new KeywordSplitter());
        sentences = List.of(
                new Sentence("Grévy's zebra and the mountain zebra are endangered"),
                new Sentence("Zebras are several species of African equids (horse family) united by their distinctive black and white stripes"),
                new Sentence("Their stripes come in different patterns, unique to each individual")
        );
    }

    @Test
    public void testScores() {
        var scores = sentenceMatcher.getScores(question, sentences);

        assertEquals(scores.size(), 3);

        scores.sort(Comparator.comparingInt(SentenceScore::getScore).reversed());
        System.out.println(scores);

        assertEquals(scores.get(0).getSentence().getText(), "Grévy's zebra and the mountain zebra are endangered");
    }

}