# 学習用クイズWebアプリ

Java 21 + Spring Boot 3（Spring MVC）と React + TypeScript + Vite で構成した、4択問題のモノレポです。

## 構成

- `backend/`: Gradle、Spring Web、Spring Data JPA、Validation、PostgreSQL
- `frontend/`: React、TypeScript、Vite
- `docker-compose.yml`: 開発用PostgreSQL

## 必要な環境

- Java 21
- Node.js 20以上
- npm
- Docker / Docker Compose（ローカルPostgreSQLを使う場合）

## Backendの起動

```bash
cd backend
../gradlew bootRun
```

バックエンドは `http://localhost:8080` で起動します。起動時にPostgreSQLへサンプル問題を投入します。

### PostgreSQLの起動

別のターミナルで、バックエンド起動前に実行します。

```bash
docker compose up -d postgres
export POSTGRES_PASSWORD=question_app_password
```

デフォルトの接続情報は、DB `question_app`、ユーザー `question_app_user`、パスワード `question_app_password`、ホスト `localhost:5432` です。既存のPostgreSQLを使う場合は、`POSTGRES_HOST`、`POSTGRES_PORT`、`POSTGRES_DB`、`POSTGRES_USER`、`POSTGRES_PASSWORD` を環境変数で上書きできます。

## Frontendの起動

別のターミナルで実行します。

```bash
cd frontend
npm install
npm run dev
```

フロントエンドは `http://localhost:5173` で起動します。

## REST API

- `GET /api/quizzes`: 問題一覧（`id`, `title`）
- `GET /api/quizzes/{id}`: 問題詳細（選択肢を含む。正解フラグは含まない）
- `POST /api/quizzes/{id}/answer`: `{ "choiceId": 1 }` を受け取り、`correct` と `correctChoiceId` を返す

開発用に `http://localhost:5173` からのCORSアクセスを許可しています。

## PostgreSQLの停止

```bash
docker compose down
```

データも削除して初期化する場合は `docker compose down -v` を使います。
