package com.example.quiz.service;

import com.example.quiz.dto.AnswerResponse;
import com.example.quiz.dto.ChoiceResponse;
import com.example.quiz.dto.QuizDetail;
import com.example.quiz.dto.QuizSummary;
import com.example.quiz.entity.Choice;
import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.ChoiceRepository;
import com.example.quiz.repository.QuizRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final ChoiceRepository choiceRepository;

    public QuizService(QuizRepository quizRepository, ChoiceRepository choiceRepository) {
        this.quizRepository = quizRepository;
        this.choiceRepository = choiceRepository;
    }

    public List<QuizSummary> findAll() {
        return quizRepository.findAll().stream()
                .map(quiz -> new QuizSummary(quiz.getId(), quiz.getTitle()))
                .toList();
    }

    public QuizDetail findById(Long id) {
        Quiz quiz = quizRepository.findWithChoicesById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "問題が見つかりません"));
        return new QuizDetail(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getChoices().stream().map(choice -> new ChoiceResponse(choice.getId(), choice.getText())).toList()
        );
    }

    public AnswerResponse answer(Long quizId, Long choiceId) {
        getQuiz(quizId);
        Choice selected = choiceRepository.findByIdAndQuizId(choiceId, quizId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "選択肢が問題に属していません"));
        Long correctChoiceId = choiceRepository.findFirstByQuizIdAndCorrectTrue(quizId)
                .map(Choice::getId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "正解が設定されていません"));
        return new AnswerResponse(selected.isCorrect(), correctChoiceId);
    }

    private Quiz getQuiz(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "問題が見つかりません"));
    }
}
