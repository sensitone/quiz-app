export type QuizSummary = {
  id: number
  title: string
}

export type Choice = {
  id: number
  text: string
}

export type QuizDetail = QuizSummary & {
  choices: Choice[]
}

export type AnswerResult = {
  correct: boolean
  correctChoiceId: number
}

const API_BASE_URL = 'http://localhost:8080/api'

async function request<T>(path: string, options?: RequestInit): Promise<T> {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    headers: { 'Content-Type': 'application/json' },
    ...options,
  })
  if (!response.ok) {
    throw new Error('データの取得に失敗しました')
  }
  return response.json() as Promise<T>
}

export const fetchQuizzes = () => request<QuizSummary[]>('/quizzes')
export const fetchQuiz = (id: number) => request<QuizDetail>(`/quizzes/${id}`)
export const submitAnswer = (id: number, choiceId: number) =>
  request<AnswerResult>(`/quizzes/${id}/answer`, {
    method: 'POST',
    body: JSON.stringify({ choiceId }),
  })
