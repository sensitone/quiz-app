import { FormEvent, useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import { fetchQuiz, QuizDetail, submitAnswer } from '../api'
import { LastAnswer } from '../App'

type Props = {
  onAnswered: (answer: LastAnswer) => void
}

export function DetailPage({ onAnswered }: Props) {
  const { id } = useParams()
  const navigate = useNavigate()
  const [quiz, setQuiz] = useState<QuizDetail | null>(null)
  const [selectedId, setSelectedId] = useState<number | null>(null)
  const [error, setError] = useState('')

  useEffect(() => {
    if (id) fetchQuiz(Number(id)).then(setQuiz).catch(() => setError('問題を読み込めませんでした。'))
  }, [id])

  async function handleSubmit(event: FormEvent) {
    event.preventDefault()
    if (!quiz || selectedId === null) return
    try {
      const result = await submitAnswer(quiz.id, selectedId)
      const selected = quiz.choices.find((choice) => choice.id === selectedId)
      const correct = quiz.choices.find((choice) => choice.id === result.correctChoiceId)
      if (selected && correct) {
        onAnswered({
          quizTitle: quiz.title,
          selectedText: selected.text,
          correctText: correct.text,
          correct: result.correct,
        })
        navigate('/result')
      }
    } catch {
      setError('回答を送信できませんでした。')
    }
  }

  if (error) return <p className="error">{error}</p>
  if (!quiz) return <p>読み込み中...</p>

  return (
    <section className="quiz-detail">
      <Link to="/" className="back-link">← 問題一覧へ</Link>
      <p className="eyebrow">QUESTION</p>
      <h1>{quiz.title}</h1>
      <p className="lead">正しいと思う選択肢を1つ選んでください。</p>
      <form onSubmit={handleSubmit}>
        <div className="choices">
          {quiz.choices.map((choice, index) => (
            <label className={`choice ${selectedId === choice.id ? 'selected' : ''}`} key={choice.id}>
              <input
                type="radio"
                name="choice"
                value={choice.id}
                checked={selectedId === choice.id}
                onChange={() => setSelectedId(choice.id)}
              />
              <span className="choice-number">{String.fromCharCode(65 + index)}</span>
              <span>{choice.text}</span>
            </label>
          ))}
        </div>
        <button className="primary-button" type="submit" disabled={selectedId === null}>回答する</button>
      </form>
    </section>
  )
}
