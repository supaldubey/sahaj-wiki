package in.cubestack.assignments.sahaj.match;

import in.cubestack.assignments.sahaj.domain.Answer;
import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.domain.Sentence;
import in.cubestack.assignments.sahaj.search.SentenceMatcher;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerMatcher {

    private final SentenceMatcher sentenceMatcher;
    private final List<Answer> answers;
    private final List<Sentence> sentences;

    public QuestionAnswerMatcher(SentenceMatcher sentenceMatcher, List<Answer> answers, List<Sentence> sentences) {
        this.sentenceMatcher = sentenceMatcher;
        this.answers = new ArrayList<>(answers);
        this.sentences = sentences;
    }

    public Answer findAnswerFor(Question question) {
        Sentence sentence = findSentence(question);
        return findAnswerForSentence(sentence);
    }

    private Sentence findSentence(Question question) {
        return sentenceMatcher.getMatchingSentence(question, sentences);
    }

    private Answer findAnswerForSentence(Sentence sentence) {
        Answer matchedAnswer = null;
        for (Answer answer : answers) {
            if (ansMatches(sentence, answer)) {
                matchedAnswer = answer;
                sentence.assignAnswer(answer);
                break;
            }
        }
        if (matchedAnswer != null) {
            answers.remove(matchedAnswer);
        }

        return matchedAnswer;
    }

    private boolean ansMatches(Sentence sentence, Answer answer) {
        return sentence.getText().toLowerCase().contains(answer.getText().toLowerCase());
    }
}
