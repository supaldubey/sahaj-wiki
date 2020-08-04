package in.cubestack.assignments.sahaj.domain;

public class Sentence {
    private final String text;
    private Answer answer;

    public Sentence(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void assignAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "text='" + text + '\'' +
                ", answer=" + answer +
                '}';
    }
}
