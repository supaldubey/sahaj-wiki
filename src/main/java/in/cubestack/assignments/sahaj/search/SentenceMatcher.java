package in.cubestack.assignments.sahaj.search;

import in.cubestack.assignments.sahaj.domain.Question;
import in.cubestack.assignments.sahaj.domain.Sentence;
import in.cubestack.assignments.sahaj.score.RelevanceCalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SentenceMatcher {

    private final RelevanceCalculator relevanceCalculator;
    private final KeywordSplitter keywordSplitter;

    public SentenceMatcher(RelevanceCalculator relevanceCalculator, KeywordSplitter keywordSplitter) {
        this.relevanceCalculator = relevanceCalculator;
        this.keywordSplitter = keywordSplitter;
    }

    public List<SentenceScore> getScores(Question question, List<Sentence> sentences) {
        var keywords = keywordSplitter.findKeywords(question.getText());

        List<SentenceScore> sentenceScores = new ArrayList<>();
        for (Sentence sentence : sentences) {
            int score = relevanceCalculator.calculate(sentence.getText(), keywords);
            sentenceScores.add(new SentenceScore(sentence, score));
        }
        return sentenceScores;
    }

    public Sentence getMatchingSentence(Question question, List<Sentence> sentences) {
        List<SentenceScore> sentenceScores = getScores(question, sentences);
        return getMatchingSentence(sentenceScores);
    }

    public Sentence getMatchingSentence(List<SentenceScore> sentenceScores) {
        return sentenceScores.stream().max(Comparator.comparing(SentenceScore::getScore))
                .map(SentenceScore::getSentence)
                .orElseThrow(() -> new RuntimeException("No matching Sentence found for question"));
    }
}
