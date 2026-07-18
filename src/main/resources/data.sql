INSERT INTO questions (question_text, explanation) VALUES
('Javaでクラスを継承するときに使うキーワードはどれですか？', 'Javaではextendsを使ってクラスを継承します。'),
('HTTPステータスコード200が表す意味はどれですか？', '200はリクエストが正常に処理されたことを表します。'),
('Spring Bootで依存性注入を表す代表的なアノテーションはどれですか？', 'Springでは@Autowiredなどを使って依存性を注入できます。');

INSERT INTO choices (question_id, choice_text, choice_order, correct) VALUES
(1, 'implements', 1, FALSE),
(1, 'extends', 2, TRUE),
(1, 'inherits', 3, FALSE),
(1, 'super', 4, FALSE),
(2, 'リダイレクト', 1, FALSE),
(2, '認証が必要', 2, FALSE),
(2, '正常終了', 3, TRUE),
(2, 'サーバーエラー', 4, FALSE),
(3, '@Autowired', 1, TRUE),
(3, '@Override', 2, FALSE),
(3, '@Entity', 3, FALSE),
(3, '@RequestBody', 4, FALSE);
