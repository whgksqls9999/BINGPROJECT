DROP SCHEMA IF EXISTS BINGPROJECT;
CREATE SCHEMA BINGPROJECT;

USE BINGPROJECT;

CREATE TABLE user (
    email VARCHAR(300) PRIMARY KEY NOT NULL,
    pw VARCHAR(300) NOT NULL,
    name VARCHAR(300) NOT NULL,
    gender VARCHAR(30) NOT NULL,
    nickname VARCHAR(300) NOT NULL UNIQUE
);
-- User 데이터
INSERT INTO user (email,pw,name,gender,nickname)
VALUES ('ssafy', '1234', '김싸피', '남', '닉네임'),
('ssafy2','1234','이싸피','남','닉네임2'),
('ssafy3','1234','이싸피','남','닉네임3');
SELECT * FROM user;

CREATE TABLE location (
    location_id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    location_name VARCHAR(100) NOT NULL,
    longitude DECIMAL(10,7) NOT NULL,
    latitude DECIMAL(10,7) NOT NULL
);
-- Location 데이터
INSERT INTO location (location_name, longitude, latitude)
VALUES ('김밥천국', 0,0);
SELECT * FROM location;


CREATE TABLE community (
    community_id INT(10) AUTO_INCREMENT PRIMARY KEY,
    community_name VARCHAR(100) NOT NULL,
    community_description VARCHAR(300) NOT NULL
);
-- Community 데이터
INSERT INTO community (community_name, community_description)
VALUES ('프리다이빙','프리다이빙을 위한 커뮤니티'),
('스킨스쿠버','스킨스쿠버를 위한 커뮤니티'),
('해녀','해녀를 위한 커뮤니티');
SELECT * FROM community;


CREATE TABLE board (
    board_id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    community_id INT(10) NOT NULL,
    num INT(10) NOT NULL,
    header VARCHAR(100) NOT NULL,
    title VARCHAR(300) NOT NULL,
    writer VARCHAR(300) NOT NULL,
    content TEXT NOT NULL,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    view_cnt INT(10) DEFAULT 0, 
    location_id INT(10),
    FOREIGN KEY (community_id) REFERENCES community(community_id) ON DELETE CASCADE,
    FOREIGN KEY (writer) REFERENCES user(nickname) ON DELETE CASCADE
);
-- Board 데이터
INSERT INTO board (community_id, num, header, title, writer, content)
VALUES (1, 1,'자유','제목1','닉네임','내용1'),
(1, 2,'자유','제목2','닉네임','내용2'),
(1, 3,'자유','제목3','닉네임','내용3'),
(2, 1,'자유','제목4','닉네임2','내용4'),
(2, 2,'자유','제목5','닉네임','내용5'),
(2, 3,'자유','제목6','닉네임','내용6'),
(3, 3,'자유','제목6','닉네임','내용7'),
(3, 3,'자유','제목6','닉네임','내용7');
SELECT * FROM board;


CREATE TABLE favorite_board (
    favorite_boardId INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    board_id INT(10) NOT NULL,
    writername VARCHAR(300) NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board(board_id) ON DELETE CASCADE,
	FOREIGN KEY (writername) REFERENCES user(nickname) ON DELETE CASCADE
);
INSERT INTO favorite_board
VALUES (1,1,'닉네임'),
(2,2,'닉네임2'),
(3,2,'닉네임2'),
(4,3,'닉네임3');
SELECT * FROM
favorite_board;

CREATE TABLE favorite_location (
    favorite_locationId INT(10) AUTO_INCREMENT PRIMARY KEY,
    writername VARCHAR(300) NOT NULL,
    location_id INT(10) NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE,
    FOREIGN KEY (writername) REFERENCES user(nickname) ON DELETE CASCADE
);

CREATE TABLE reply (
    reply_id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    board_id INT(10) NOT NULL,
    writer VARCHAR(300) NOT NULL,
    content TEXT NOT NULL,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (board_id) REFERENCES board(board_id) ON DELETE CASCADE,
    FOREIGN KEY (writer) REFERENCES user(nickname) ON DELETE CASCADE
);
-- Reply 데이터
INSERT INTO reply (board_id, writer, content, reg_date, is_modified)
VALUES (1, '닉네임', '댓글내용1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
SELECT * FROM reply;

-- SELECT b.writer, b.content, b.reg_date  FROM board as b INNER JOIN favorite_board as fb ON b.board_id = fb.board_id;

