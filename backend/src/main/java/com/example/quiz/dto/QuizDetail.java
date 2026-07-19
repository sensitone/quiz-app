package com.example.quiz.dto;

import java.util.List;

public record QuizDetail(Long id, String title, List<ChoiceResponse> choices) {
}
