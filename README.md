# 4択問題集 Webアプリ

Spring Boot + Thymeleaf + JPA + H2で作成した、初心者向けの4択問題集MVPです。

## 必要な環境

- Java 17以上
- Maven 3.9以上（またはMaven Wrapper）

## 起動方法

```bash
./mvnw spring-boot:run
```

Mavenがインストール済みの場合は次でも起動できます。

```bash
mvn spring-boot:run
```

ブラウザで http://localhost:8080/ を開いてください。

## 実装済みの機能

- DBの初期データから問題を取得
- 4つの選択肢を表示
- サーバー側で正誤判定
- 解説表示
- 全問終了後の正解数・正答率表示
- セッションを使ったクイズの最初からやり直し

## DB

開発用にH2のインメモリDBを使っています。アプリを停止するとデータは初期状態に戻ります。
H2コンソールは http://localhost:8080/h2-console で利用できます。

- JDBC URL: `jdbc:h2:mem:quizdb`
- User Name: `sa`
- Password: 空欄

## 今後の拡張

- Category Entityの追加
- 管理者向け問題CRUD
- QuizAttemptによる回答履歴
- ユーザー認証
- PostgreSQLへの切り替え
