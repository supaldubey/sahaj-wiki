package in.cubestack.assignments.sahaj.match;

import in.cubestack.assignments.sahaj.domain.Answer;
import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.domain.Sentence;
import in.cubestack.assignments.sahaj.result.ResultListener;
import in.cubestack.assignments.sahaj.score.WordLengthRelevanceCalculator;
import in.cubestack.assignments.sahaj.search.KeywordSplitter;
import in.cubestack.assignments.sahaj.search.SentenceMatcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PassageAnswerMatcherTest {

    private QuestionAnswerMatcher questionAnswerMatcher;

    private void init() {
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

    private void initAllQuestions() {
        List<Answer> answers = List.of(
                new Answer("subgenus Hippotigris"),
                new Answer("the plains zebra, the Grévy's zebra and the mountain zebra"),
                new Answer("horses and donkeys"),
                new Answer("aims to breed zebras that are phenotypically similar to the quagga"),
                new Answer("Grévy's zebra and the mountain zebra")
        );

        List<Sentence> sentences = List.of(
                new Sentence("Zebras are several species of African equids (horse family) united by their distinctive black and white stripes"),
                new Sentence(" Their stripes come in different patterns, unique to each individual"),
                new Sentence(" They are generally social animals that live in small harems to large herds"),
                new Sentence(" Unlike their closest relatives, horses and donkeys, zebras have never been truly domesticated"),
                new Sentence(" There are three species of zebras: the plains zebra, the Grévy's zebra and the mountain zebra"),
                new Sentence(" The plains zebra and the mountain zebra belong to the subgenus Hippotigris, but Grévy's zebra is the sole species of subgenus Dolichohippus"),
                new Sentence(" The latter resembles an ass, to which it is closely related, while the former two are more Page 2 / 3 horse-like"),
                new Sentence(" All three belong to the genus Equus, along with other living equids"),
                new Sentence(" The unique stripes of zebras make them one of the animals most familiar to people"),
                new Sentence(" They occur in a variety of habitats, such as grasslands, savannas, woodlands, thorny scrublands, mountains, and coastal hills"),
                new Sentence(" However, various anthropogenic factors have had a severe impact on zebra populations, in particular hunting for skins and habitat destruction"),
                new Sentence(" Grévy's zebra and the mountain zebra are endangered"),
                new Sentence(" While plains zebras are much more plentiful, one subspecies - the Quagga - became extinct in the late 19th century"),
                new Sentence(" Though there is currently a plan, called the Quagga Project, that aims to breed zebras that are phenotypically similar to the Quagga, in a process called breeding back")

        );

        questionAnswerMatcher = new QuestionAnswerMatcher(
                new SentenceMatcher(new WordLengthRelevanceCalculator(), new KeywordSplitter()),
                answers,
                sentences
        );
    }

    @Test
    public void testQuestionAnswer() {
        init();
        MockResultListener mockResultListener = new MockResultListener();
        PassageAnswerMatcher passageAnswerMatcher = new PassageAnswerMatcher(mockResultListener, questionAnswerMatcher);
        passageAnswerMatcher.match(List.of(new Question("Which zebra's are endangered")));

        assertEquals(mockResultListener.answers.size(), 1);
        assertEquals(mockResultListener.answers.get(0).getText(), "Grévy's zebra and the mountain zebra");
    }

    @Test
    public void testQuestionAnswerEndToEnd() {

        initAllQuestions();

        MockResultListener mockResultListener = new MockResultListener();
        PassageAnswerMatcher passageAnswerMatcher = new PassageAnswerMatcher(mockResultListener, questionAnswerMatcher);
        passageAnswerMatcher.match(List.of(
                new Question("Which Zebras are endangered?"),
                new Question("What is the aim of the Quagga Project?"),
                new Question("Which animals are some of their closest relatives?"),
                new Question("Which are the three species of zebras?"),
                new Question("Which subgenus do the plains zebra and the mountain zebra belong to?")
        ));

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