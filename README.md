# 学習用クイズWebアプリ

Java 21 + Spring Boot 3（Spring MVC）と React + TypeScript + Vite で構成した、4択問題のモノレポです。

## 構成

- `backend/`: Gradle、Spring Web、Spring Data JPA、Validation、H2
- `frontend/`: React、TypeScript、Vite

## 必要な環境

- Java 21
- Node.js 20以上
- npm

## Backendの起動

```bash
cd backend
../gradlew bootRun
```

バックエンドは `http://localhost:8080` で起動します。起動時にH2のインメモリDBへサンプル問題を投入します。

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
