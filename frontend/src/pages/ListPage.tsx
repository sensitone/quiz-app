import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { fetchQuizzes, QuizSummary } from '../api'

export function ListPage() {
  const [quizzes, setQuizzes] = useState<QuizSummary[]>([])
  const [error, setError] = useState('')

  useEffect(() => {
    fetchQuizzes().then(setQuizzes).catch(() => setError('問題一覧を読み込めませんでした。'))
  }, [])

  return (
    <section>
      <p className="eyebrow">QUIZ LIBRARY</p>
      <h1>学びたいテーマを選ぶ</h1>
      <p className="lead">4択問題に答えて、知識を楽しく確認しましょう。</p>
      {error && <p className="error">{error}</p>}
      <div className="quiz-grid">
        {quizzes.map((quiz) => (
          <Link className="quiz-card" to={`/quizzes/${quiz.id}`} key={quiz.id}>
            <span className="card-label">QUIZ {String(quiz.id).padStart(2, '0')}</span>
            <strong>{quiz.title}</strong>
            <span className="card-action">挑戦する →</span>
          </Link>
        ))}
      </div>
    </section>
  )
}
