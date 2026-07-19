package com.example.quiz.config;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleDataConfig {

    @Bean
    CommandLineRunner loadSampleData(QuizRepository quizRepository) {
        return args -> {
            if (quizRepository.count() > 0) {
                return;
            }

            Quiz javaQuiz = new Quiz("Javaの基礎");
            javaQuiz.addChoice("クラスを継承するキーワードは implements", false);
            javaQuiz.addChoice("クラスを継承するキーワードは extends", true);
            javaQuiz.addChoice("クラスを継承するキーワードは inherits", false);
            javaQuiz.addChoice("クラスを継承するキーワードは super", false);

            Quiz httpQuiz = new Quiz("HTTPステータスコード");
            httpQuiz.addChoice("200はリダイレクトを表す", false);
            httpQuiz.addChoice("200は認証が必要なことを表す", false);
            httpQuiz.addChoice("200はリクエストの成功を表す", true);
            httpQuiz.addChoice("200はサーバーエラーを表す", false);

            Quiz springQuiz = new Quiz("Spring Bootの基礎");
            springQuiz.addChoice("依存性注入に使う代表的なアノテーションは @Autowired", true);
            springQuiz.addChoice("依存性注入に使う代表的なアノテーションは @Override", false);
            springQuiz.addChoice("依存性注入に使う代表的なアノテーションは @Entity", false);
            springQuiz.addChoice("依存性注入に使う代表的なアノテーションは @RequestBody", false);

            quizRepository.save(javaQuiz);
            quizRepository.save(httpQuiz);
            quizRepository.save(springQuiz);
        };
    }
}
