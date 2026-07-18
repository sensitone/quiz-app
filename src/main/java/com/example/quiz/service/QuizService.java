package com.example.quiz.service;

import com.example.quiz.entity.Choice;
import com.example.quiz.entity.Question;
import com.example.quiz.repository.ChoiceRepository;
import com.example.quiz.repository.QuestionRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    public QuizService(QuestionRepository questionRepository, ChoiceRepository choiceRepository) {
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
    }

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Question findQuestion(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("問題が見つかりません: " + questionId));
    }

    public boolean isCorrect(Long questionId, Long choiceId) {
        Choice choice = choiceRepository.findByIdAndQuestionId(choiceId, questionId)
                .orElseThrow(() -> new IllegalArgumentException("選択肢が問題に属していません"));
        return choice.isCorrect();
    }

    public Choice findChoice(Long questionId, Long choiceId) {
        return choiceRepository.findByIdAndQuestionId(choiceId, questionId)
                .orElseThrow(() -> new IllegalArgumentException("選択肢が問題に属していません"));
    }

    public boolean belongsToQuestion(Question question, Long choiceId) {
        return question.getChoices().stream()
                .map(Choice::getId)
                .anyMatch(id -> Objects.equals(id, choiceId));
    }
}
