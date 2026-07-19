package com.example.quiz.config;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;
import java.util.List;
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

            List<Quiz> quizzes = List.of(
                    quiz("たんぱく質の主な働き", "筋肉や皮膚など体をつくる材料になる", "体内の酸素をすべてつくる", "視力を必ずよくする", "睡眠を必要なくする"),
                    quiz("アミノ酸とは", "たんぱく質をつくる成分", "水だけでできた成分", "酸素だけでできた成分", "食物繊維だけでできた成分"),
                    quiz("たんぱく質の消化", "消化されると主にアミノ酸になる", "消化されると酸素だけになる", "消化されると食塩だけになる", "消化されずそのまま吸収される"),
                    quiz("成長期とたんぱく質", "体をつくる材料が必要だから", "成長期は水を飲まなくてよいから", "たんぱく質だけで全ての栄養が足りるから", "運動をしなくてよくなるから"),
                    quiz("アルブミン", "血しょうに含まれるたんぱく質の一種", "骨の中だけにある無機質", "赤血球そのもの", "消化管の中だけで働く酵素"),
                    quiz("血しょう", "血液の液体成分", "骨のかたい部分", "筋肉の収縮部分", "皮膚の表面だけ"),
                    quiz("赤血球", "酸素を運ぶ", "食べ物をかみくだく", "骨を動かす", "汗をつくる"),
                    quiz("白血球", "病原体などから体を守る", "酸素だけを運ぶ", "食べ物を胃へ送る", "体温を直接測る"),
                    quiz("血小板", "血液を固めて出血を止めることに関わる", "酸素を運ぶ", "食べ物を消化する", "筋肉を大きくする"),
                    quiz("炭水化物", "体を動かすためのエネルギーになる", "血液を固める材料だけになる", "骨の長さだけを決める", "病原体をすべて消す"),
                    quiz("脂質", "エネルギー源や体温を保つことに関わる", "酸素を運ぶ赤血球になる", "食べ物をかむ働きだけをする", "水分を全く必要としなくする"),
                    quiz("ビタミンと無機質", "体の調子を整え、体の機能を助ける", "全てのエネルギーを一度に作る", "たんぱく質を全く必要なくする", "睡眠時間を短くする"),
                    quiz("バランスのよい食事", "主食・主菜・副菜などを組み合わせる", "同じ食品だけを毎日食べる", "野菜だけを食べる", "食事を一食だけにする"),
                    quiz("朝食の利点", "午前中の活動のエネルギーを補給する", "運動をしなくてよくする", "睡眠を必要なくする", "好きな食品だけで栄養をそろえる"),
                    quiz("栄養分の吸収", "小腸", "気管", "心臓", "腎臓"),
                    quiz("デンプンの消化を始める消化液", "だ液", "汗", "胆汁だけ", "血しょう"),
                    quiz("胃の働き", "食べ物を一時的にたくわえ、消化を進める", "酸素を全身に運ぶ", "尿をつくる", "体を動かす信号を出す"),
                    quiz("水の役割", "血液などの成分となり、物質を運ぶことに関わる", "体内の水分を全てなくす", "酸素を必要なくする", "食事をしなくてよくする"),
                    quiz("運動時のエネルギー源", "炭水化物", "ビタミンだけ", "無機質だけ", "食物繊維だけ"),
                    quiz("栄養素のとり方", "いろいろな食品を組み合わせてとる", "一つの栄養素だけを多くとる", "食事を抜いて調整する", "年齢や活動量を考えない")
            );

            quizRepository.saveAll(quizzes);
        };
    }

    private Quiz quiz(String title, String correct, String choice2, String choice3, String choice4) {
        Quiz quiz = new Quiz(title);
        quiz.addChoice(correct, true);
        quiz.addChoice(choice2, false);
        quiz.addChoice(choice3, false);
        quiz.addChoice(choice4, false);
        return quiz;
    }
}
