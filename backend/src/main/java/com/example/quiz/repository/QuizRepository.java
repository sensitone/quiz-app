package com.example.quiz.repository;

import com.example.quiz.entity.Quiz;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @EntityGraph(attributePaths = "choices")
    Optional<Quiz> findWithChoicesById(Long id);
}
