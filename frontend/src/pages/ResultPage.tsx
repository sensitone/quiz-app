import { Link } from 'react-router-dom'
import { LastAnswer } from '../App'

export function ResultPage({ answer }: { answer: LastAnswer | null }) {
  if (!answer) {
    return (
      <section className="empty-state">
        <h1>回答結果はありません</h1>
        <Link to="/" className="primary-button">問題一覧へ戻る</Link>
      </section>
    )
  }

  return (
    <section className="result-card">
      <p className="eyebrow">RESULT</p>
      <div className={`result-icon ${answer.correct ? 'correct' : 'incorrect'}`}>{answer.correct ? '○' : '×'}</div>
      <h1>{answer.correct ? '正解です！' : '不正解です'}</h1>
      <p className="result-message">{answer.quizTitle}</p>
      <div className="answer-summary">
        <p><span>あなたの回答</span>{answer.selectedText}</p>
        {!answer.correct && <p><span>正しい回答</span>{answer.correctText}</p>}
      </div>
      <Link to="/" className="primary-button">問題一覧へ戻る</Link>
    </section>
  )
}
