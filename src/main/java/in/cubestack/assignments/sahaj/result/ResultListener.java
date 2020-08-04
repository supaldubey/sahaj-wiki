package in.cubestack.assignments.sahaj.result;

import in.cubestack.assignments.sahaj.domain.Answer;
import in.cubestack.assignments.sahaj.domain.Question;

public interface ResultListener {
    void onResult(Question question, Answer answer);
}
