package in.cubestack.assignments.sahaj.score;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WordLengthRelevanceCalculatorTest {

    private WordLengthRelevanceCalculator wordLengthRelevanceCalculator;

    @Before
    public void init() {
        wordLengthRelevanceCalculator = new WordLengthRelevanceCalculator();
    }

    @Test
    public void testScoreHigherForBetterMatch() {
        int score = wordLengthRelevanceCalculator.calculate("Hi How are you sir", List.of("hi", "sir"));
        int shouldMatchScore = wordLengthRelevanceCalculator.calculate("Hi How are you sir", List.of("hi", "sir", "are"));

        assertTrue(shouldMatchScore > score);
    }

    @Test
    public void testScores() {
        int score = wordLengthRelevanceCalculator.calculate("Hi", List.of("hi"));
        assertEquals(score, 2);
    }

}