package com.example.quiz.dto;

import jakarta.validation.constraints.NotNull;

public record AnswerRequest(@NotNull Long choiceId) {
}
