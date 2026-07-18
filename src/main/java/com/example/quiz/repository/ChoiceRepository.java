package com.example.quiz.repository;

import com.example.quiz.entity.Choice;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    Optional<Choice> findByIdAndQuestionId(Long id, Long questionId);
}
