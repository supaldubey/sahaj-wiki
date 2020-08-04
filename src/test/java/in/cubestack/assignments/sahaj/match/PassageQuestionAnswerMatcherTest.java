package in.cubestack.assignments.sahaj.match;

import in.cubestack.assignments.sahaj.domain.Answer;
import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.result.ResultListener;
import in.cubestack.assignments.sahaj.score.WordLengthRelevanceCalculator;
import in.cubestack.assignments.sahaj.search.KeywordSplitter;
import in.cubestack.assignments.sahaj.search.SentenceMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


public class PassageQuestionAnswerMatcherTest {
    private PassageQuestionAnswerMatcher passageQuestionAnswerMatcher;
    MockResultListener mockResultListener;

    @Before
    public void init() {
        SentenceMatcher sentenceMatcher = new SentenceMatcher(new WordLengthRelevanceCalculator(), new KeywordSplitter());
        mockResultListener = new MockResultListener();

        passageQuestionAnswerMatcher = new PassageQuestionAnswerMatcher(mockResultListener, sentenceMatcher);
    }

    @Test
    public void testEndToEnd() {
        String input = "Zebras are several species of African equids (horse family) united by their distinctive black and white stripes. Their stripes come in different patterns, unique to each individual. They are generally social animals that live in small harems to large herds. Unlike their closest relatives, horses and donkeys, zebras have never been truly domesticated. There are three species of zebras: the plains zebra, the Grévy's zebra and the mountain zebra. The plains zebra and the mountain zebra belong to the subgenus Hippotigris, but Grévy's zebra is the sole species of subgenus Dolichohippus. The latter resembles an ass, to which it is closely related, while the former two are more Page 2 / 3 horse-like. All three belong to the genus Equus, along with other living equids. The unique stripes of zebras make them one of the animals most familiar to people. They occur in a variety of habitats, such as grasslands, savannas, woodlands, thorny scrublands, mountains, and coastal hills. However, various anthropogenic factors have had a severe impact on zebra populations, in particular hunting for skins and habitat destruction. Grévy's zebra and the mountain zebra are endangered. While plains zebras are much more plentiful, one subspecies - the Quagga - became extinct in the late 19th century. Though there is currently a plan, called the Quagga Project, that aims to breed zebras that are phenotypically similar to the Quagga, in a process called breeding back.\n" +
                "Which Zebras are endangered?\n" +
                "What is the aim of the Quagga Project?\n" +
                "Which animals are some of their closest relatives?\n" +
                "Which are the three species of zebras?\n" +
                "Which subgenus do the plains zebra and the mountain zebra belong to?\n" +
                "subgenus Hippotigris; the plains zebra, the Grévy's zebra and the mountain zebra;horses and donkeys;aims to breed zebras that are phenotypically similar to the quagga; Grévy's zebra and the mountain zebra";

        List<String> inputText = Arrays.stream(input.split("\n")).collect(Collectors.toList());
        passageQuestionAnswerMatcher.provideAnswers(inputText);


        assertEquals(mockResultListener.answers.size(), 5);
        assertEquals(mockResultListener.answers.get(0).getText(), "Grévy's zebra and the mountain zebra");
        assertEquals(mockResultListener.answers.get(1).getText(), "aims to breed zebras that are phenotypically similar to the quagga");
        assertEquals(mockResultListener.answers.get(2).getText(), "horses and donkeys");
        assertEquals(mockResultListener.answers.get(3).getText(), "the plains zebra, the Grévy's zebra and the mountain zebra");
        assertEquals(mockResultListener.answers.get(4).getText(), "subgenus Hippotigris");
    }

    static class MockResultListener implements ResultListener {
        List<Answer> answers = new ArrayList<>();

        @Override
        public void onResult(Question question, Answer answer) {
            answers.add(answer);
        }
    }
}