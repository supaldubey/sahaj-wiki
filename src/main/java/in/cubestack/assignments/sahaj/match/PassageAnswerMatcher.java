package in.cubestack.assignments.sahaj.match;

import in.cubestack.assignments.sahaj.domain.Answer;
import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.result.ResultListener;

import java.util.List;

public class PassageAnswerMatcher {
    private final ResultListener resultListener;
    private final QuestionAnswerMatcher questionAnswerMatcher;

    public PassageAnswerMatcher(ResultListener resultListener, QuestionAnswerMatcher questionAnswerMatcher) {
        this.resultListener = resultListener;
        this.questionAnswerMatcher = questionAnswerMatcher;
    }


    public void match(List<Question> questions) {
        for (Question question : questions) {
            Answer answer = questionAnswerMatcher.findAnswerFor(question);
            resultListener.onResult(question, answer);
        }
    }
}
