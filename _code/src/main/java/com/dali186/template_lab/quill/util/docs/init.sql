USE TEMPLATE_LAB;

CREATE TABLE BOARD (
    BOARD_ID INT AUTO_INCREMENT PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    CONTENT TEXT NOT NULL,
    AUTHOR VARCHAR(100) NOT NULL,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO BOARD (TITLE, CONTENT, AUTHOR)
VALUES
('게시물 제목 1', '이것은 게시물 내용 1입니다.', '작성자1'),
('게시물 제목 2', '이것은 게시물 내용 2입니다.', '작성자2'),
('게시물 제목 3', '이것은 게시물 내용 3입니다.', '작성자3'),
('게시물 제목 4', '이것은 게시물 내용 4입니다.', '작성자1'),
('게시물 제목 5', '이것은 게시물 내용 5입니다.', '작성자2'),
('게시물 제목 6', '이것은 게시물 내용 6입니다.', '작성자3'),
('게시물 제목 7', '이것은 게시물 내용 7입니다.', '작성자1'),
('게시물 제목 8', '이것은 게시물 내용 8입니다.', '작성자2'),
('게시물 제목 9', '이것은 게시물 내용 9입니다.', '작성자3'),
('게시물 제목 10', '이것은 게시물 내용 10입니다.', '작성자1');

CREATE TABLE FILE (
    FILE_ID INT AUTO_INCREMENT PRIMARY KEY,
    TYPE VARCHAR(10) NOT NULL,
    BOARD_ID INT NOT NULL,
    FILE_ORIGIN_NAME VARCHAR(255) NOT NULL,
    FILE_NAME VARCHAR(255) NOT NULL,
    FILE_PATH VARCHAR(500) NOT NULL,
    UPLOADED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
