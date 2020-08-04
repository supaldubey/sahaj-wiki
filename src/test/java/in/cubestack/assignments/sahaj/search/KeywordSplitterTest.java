package in.cubestack.assignments.sahaj.search;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;


public class KeywordSplitterTest {

    private KeywordSplitter keywordSplitter;

    @Before
    public void init() {
        keywordSplitter = new KeywordSplitter();
    }

    @Test
    public void testSplitRemovedQuestionKeywords() {
        List<String> keywords = keywordSplitter.findKeywords("Who Are You");

        assertArrayEquals(keywords.toArray(), new String[] {"are", "you"});
    }

    @Test
    public void testSplitRemovedQuestionKeywordsAndQuestionMark() {
        List<String> keywords = keywordSplitter.findKeywords("Who Are You?");

        assertArrayEquals(keywords.toArray(), new String[] {"are", "you"});
    }

    @Test
    public void testSplit() {
        List<String> keywords = keywordSplitter.findKeywords("Hello my friend");

        assertArrayEquals(keywords.toArray(), new String[] {"hello", "my", "friend"});
    }

}