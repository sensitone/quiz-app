package com.example.quiz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "choices")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private String choiceText;

    private int choiceOrder;

    private boolean correct;

    protected Choice() {
    }

    public Long getId() {
        return id;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public int getChoiceOrder() {
        return choiceOrder;
    }

    public boolean isCorrect() {
        return correct;
    }
}
