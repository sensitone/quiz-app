import { useState } from 'react'
import { Link, Navigate, Route, Routes } from 'react-router-dom'
import { DetailPage } from './pages/DetailPage'
import { ListPage } from './pages/ListPage'
import { ResultPage } from './pages/ResultPage'

export type LastAnswer = {
  quizTitle: string
  selectedText: string
  correctText: string
  correct: boolean
}

export default function App() {
  const [lastAnswer, setLastAnswer] = useState<LastAnswer | null>(null)

  return (
    <div className="app-shell">
      <header className="site-header">
        <Link to="/" className="brand">学習クイズ</Link>
      </header>
      <main className="page-container">
        <Routes>
          <Route path="/" element={<ListPage />} />
          <Route path="/quizzes/:id" element={<DetailPage onAnswered={setLastAnswer} />} />
          <Route path="/result" element={<ResultPage answer={lastAnswer} />} />
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </main>
    </div>
  )
}
