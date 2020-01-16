CREATE DATABASE blog_20200115 CHARSET=utf8mb4;---固定写法
-- 设置mysql  jdbc:mysql://localhost:3306/blog_20200115?useSSL=false&characterEncoding=utf8
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(200) NOT NULL UNIQUE,
  nickname VARCHAR(200) NOT NULL,
  password CHAR(64) NOT NULL COMMENT "不保存明文密码，保存的密码的HASH值，HASH算法 SHA-256"
);

CREATE TABLE articles (
  id INT PRIMARY KEY AUTO_INCREMENT,
  author_id INT NOT NULL,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  published_at DATETIME NOT NULL
);

CREATE TABLE comments (
  id INT PRIMARY KEY AUTO_INCREMENT,
  author_id INT NOT NULL,
  article_id INT NOT NULL,
  content VARCHAR(200),
  published_at DATETIME NOT NULL
);

CREATE TABLE user_like_article_relations (
  user_id INT NOT NULL,
  article_id INT NOT NULL,
  PRIMARY KEY (user_id, article_id)
);

-- 用户管理：1. 注册用户: username 重复
INSERT INTO users (username, nickname, password) VALUES (?, ?, ?);

-- 用户管理：2. 用户登陆
SELECT id, username, nickname FROM users WHERE username = ? AND password = ?;
