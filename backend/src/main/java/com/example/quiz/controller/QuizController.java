package com.example.quiz.controller;

import com.example.quiz.dto.AnswerRequest;
import com.example.quiz.dto.AnswerResponse;
import com.example.quiz.dto.QuizDetail;
import com.example.quiz.dto.QuizSummary;
import com.example.quiz.service.QuizService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<QuizSummary> list() {
        return quizService.findAll();
    }

    @GetMapping("/{id}")
    public QuizDetail detail(@PathVariable Long id) {
        return quizService.findById(id);
    }

    @PostMapping("/{id}/answer")
    public AnswerResponse answer(@PathVariable Long id, @Valid @RequestBody AnswerRequest request) {
        return quizService.answer(id, request.choiceId());
    }
}
