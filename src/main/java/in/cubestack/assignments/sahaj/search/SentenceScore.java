package in.cubestack.assignments.sahaj.search;

import in.cubestack.assignments.sahaj.domain.Sentence;

public class SentenceScore {

    private final Sentence sentence;
    private final int score;

    public SentenceScore(Sentence sentence, int score) {
        this.sentence = sentence;
        this.score = score;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "SentenceScore{" +
                "sentence=" + sentence +
                ", score=" + score +
                '}';
    }

}
