package com.example.quiz.controller;

import com.example.quiz.entity.Choice;
import com.example.quiz.entity.Question;
import com.example.quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

    private static final String QUESTION_IDS = "questionIds";
    private static final String CURRENT_INDEX = "currentIndex";
    private static final String SCORE = "score";
    private static final String LAST_RESULT = "lastResult";

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/quiz";
    }

    @GetMapping("/quiz")
    public String showQuiz(HttpSession session, Model model) {
        initializeQuizIfNeeded(session);

        List<Long> questionIds = getQuestionIds(session);
        int currentIndex = getInteger(session, CURRENT_INDEX);
        if (currentIndex >= questionIds.size()) {
            if (session.getAttribute(LAST_RESULT) != null) {
                Question lastQuestion = quizService.findQuestion(
                        (Long) session.getAttribute("lastQuestionId"));
                model.addAttribute("question", lastQuestion);
                model.addAttribute("questionNumber", questionIds.size());
                model.addAttribute("totalQuestions", questionIds.size());
                model.addAttribute("lastResult", session.getAttribute(LAST_RESULT));
                model.addAttribute("lastExplanation", session.getAttribute("lastExplanation"));
                model.addAttribute("finalQuestion", true);
                return "quiz";
            }
            return "redirect:/quiz/result";
        }

        Question question = quizService.findQuestion(questionIds.get(currentIndex));
        model.addAttribute("question", question);
        model.addAttribute("questionNumber", currentIndex + 1);
        model.addAttribute("totalQuestions", questionIds.size());
        model.addAttribute("lastResult", session.getAttribute(LAST_RESULT));
        model.addAttribute("lastExplanation", session.getAttribute("lastExplanation"));
        model.addAttribute("finalQuestion", false);
        return "quiz";
    }

    @PostMapping("/quiz/answer")
    public String answer(
            @RequestParam Long questionId,
            @RequestParam Long choiceId,
            HttpSession session) {
        initializeQuizIfNeeded(session);
        List<Long> questionIds = getQuestionIds(session);
        int currentIndex = getInteger(session, CURRENT_INDEX);
        if (currentIndex >= questionIds.size() || !questionIds.get(currentIndex).equals(questionId)) {
            return "redirect:/quiz";
        }

        Question question = quizService.findQuestion(questionId);
        if (!quizService.belongsToQuestion(question, choiceId)) {
            return "redirect:/quiz";
        }

        Choice choice = quizService.findChoice(questionId, choiceId);
        if (choice.isCorrect()) {
            session.setAttribute(SCORE, getInteger(session, SCORE) + 1);
        }
        session.setAttribute(LAST_RESULT, choice.isCorrect());
        session.setAttribute("lastExplanation", question.getExplanation());
        session.setAttribute("lastQuestionId", questionId);
        session.setAttribute(CURRENT_INDEX, currentIndex + 1);
        return "redirect:/quiz";
    }

    @GetMapping("/quiz/result")
    public String result(HttpSession session, Model model) {
        List<Long> questionIds = getQuestionIds(session);
        int score = getInteger(session, SCORE);
        model.addAttribute("score", score);
        model.addAttribute("totalQuestions", questionIds.size());
        model.addAttribute("percentage", questionIds.isEmpty() ? 0 : score * 100 / questionIds.size());
        session.removeAttribute(LAST_RESULT);
        session.removeAttribute("lastExplanation");
        session.removeAttribute("lastQuestionId");
        return "result";
    }

    @PostMapping("/quiz/restart")
    public String restart(HttpSession session) {
        session.invalidate();
        return "redirect:/quiz";
    }

    private void initializeQuizIfNeeded(HttpSession session) {
        if (session.getAttribute(QUESTION_IDS) == null) {
            List<Long> questionIds = quizService.findAllQuestions().stream()
                    .map(Question::getId)
                    .toList();
            session.setAttribute(QUESTION_IDS, questionIds);
            session.setAttribute(CURRENT_INDEX, 0);
            session.setAttribute(SCORE, 0);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Long> getQuestionIds(HttpSession session) {
        return (List<Long>) session.getAttribute(QUESTION_IDS);
    }

    private int getInteger(HttpSession session, String key) {
        return (Integer) session.getAttribute(key);
    }
}
