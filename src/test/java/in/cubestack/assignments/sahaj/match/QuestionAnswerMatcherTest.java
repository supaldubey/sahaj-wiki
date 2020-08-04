package in.cubestack.assignments.sahaj.match;

import in.cubestack.assignments.sahaj.domain.Answer;
import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.domain.Sentence;
import in.cubestack.assignments.sahaj.score.WordLengthRelevanceCalculator;
import in.cubestack.assignments.sahaj.search.KeywordSplitter;
import in.cubestack.assignments.sahaj.search.SentenceMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class QuestionAnswerMatcherTest {

    private QuestionAnswerMatcher questionAnswerMatcher;

    @Before
    public void init() {
        List<Answer> answers = List.of(
                new Answer("unique to each individual"),
                new Answer("Grévy's zebra and the mountain zebra")
        );

        List<Sentence> sentences = List.of(
                new Sentence("Grévy's zebra and the mountain zebra are endangered"),
                new Sentence("Zebras are several species of African equids (horse family) united by their distinctive black and white stripes"),
                new Sentence("Their stripes come in different patterns, unique to each individual")
        );

        questionAnswerMatcher = new QuestionAnswerMatcher(
                new SentenceMatcher(new WordLengthRelevanceCalculator(), new KeywordSplitter()),
                answers,
                sentences
        );
    }

    @Test
    public void testQuestionAnswer() {
        Answer answer = questionAnswerMatcher.findAnswerFor(new Question("Which zebra's are endangered"));

        assertEquals(answer.getText(), "Grévy's zebra and the mountain zebra");
    }

}