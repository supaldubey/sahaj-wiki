package in.cubestack.assignments.sahaj.match;

import in.cubestack.assignments.sahaj.domain.Answer;
import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.domain.Sentence;
import in.cubestack.assignments.sahaj.result.ResultListener;
import in.cubestack.assignments.sahaj.search.SentenceMatcher;

import java.util.ArrayList;
import java.util.List;

import static in.cubestack.assignments.sahaj.util.AssertionUtils.assertThat;

public class PassageQuestionAnswerMatcher {

    private final ResultListener resultListener;
    private final SentenceMatcher sentenceMatcher;

    public static final String ANSWER_DELIMITER = ";";
    public static final String SENTENCE_SPLITTER = "\\.";

    private final List<Sentence> sentences = new ArrayList<>();
    private final List<Question> questions = new ArrayList<>();
    private final List<Answer> answers = new ArrayList<>();

    public PassageQuestionAnswerMatcher(ResultListener resultListener, SentenceMatcher sentenceMatcher) {
        this.resultListener = resultListener;
        this.sentenceMatcher = sentenceMatcher;
    }

    public void provideAnswers(List<String> input) {
        assertThat(input.size() == 7, "Input should be six sentences");
        initialize(input);

        QuestionAnswerMatcher questionAnswerMatcher = new QuestionAnswerMatcher(sentenceMatcher, answers, sentences);
        PassageAnswerMatcher passageAnswerMatcher = new PassageAnswerMatcher(resultListener, questionAnswerMatcher);

        passageAnswerMatcher.match(questions);
    }

    private void initialize(List<String> input) {
        String sentenceText = input.get(0);
        for (String sentence : sentenceText.split(SENTENCE_SPLITTER)) {
            sentences.add(new Sentence(sentence.trim()));
        }

        for (int index = 0; index < 5; index++) {
            questions.add(new Question(input.get(1 + index).trim()));
        }

        String answerText = input.get(6);
        for (String answer : answerText.split(ANSWER_DELIMITER)) {
            answers.add(new Answer(answer.trim()));
        }
    }
}
