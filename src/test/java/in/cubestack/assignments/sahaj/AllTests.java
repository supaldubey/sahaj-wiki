package in.cubestack.assignments.sahaj;

import in.cubestack.assignments.sahaj.match.PassageAnswerMatcherTest;
import in.cubestack.assignments.sahaj.match.PassageQuestionAnswerMatcherTest;
import in.cubestack.assignments.sahaj.match.QuestionAnswerMatcherTest;
import in.cubestack.assignments.sahaj.score.WordLengthRelevanceCalculatorTest;
import in.cubestack.assignments.sahaj.search.KeywordSplitterTest;
import in.cubestack.assignments.sahaj.search.SentenceMatcherTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SentenceMatcherTest.class,
                KeywordSplitterTest.class,
                WordLengthRelevanceCalculatorTest.class,
                PassageAnswerMatcherTest.class,
                PassageQuestionAnswerMatcherTest.class,
                QuestionAnswerMatcherTest.class})
public class AllTests {
}
