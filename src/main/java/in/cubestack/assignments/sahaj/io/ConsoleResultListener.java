package in.cubestack.assignments.sahaj.io;

import in.cubestack.assignments.sahaj.domain.Answer;
import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.result.ResultListener;

public class ConsoleResultListener implements ResultListener {

    @Override
    public void onResult(Question question, Answer answer) {
        System.out.println(answer.getText());
    }
}
