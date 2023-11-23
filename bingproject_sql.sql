DROP SCHEMA IF EXISTS BINGPROJECT;
CREATE SCHEMA BINGPROJECT;

USE BINGPROJECT;

CREATE TABLE user (
    email VARCHAR(300) PRIMARY KEY NOT NULL,
    pw VARCHAR(300) NOT NULL,
    name VARCHAR(300) NOT NULL,
    gender VARCHAR(30) NOT NULL,
    nickname VARCHAR(300) NOT NULL UNIQUE,
    withdraw_text VARCHAR(1000) NOT NULL
);
-- User 데이터
INSERT INTO user (email,pw,name,gender,nickname,withdraw_text)
VALUES ('ssafy', '1234!!!!', '김싸피', '남', '닉네임', '안녕히계세요 여러분 저는 사회의 굴레 벗어나고 어쩌구'),
('ssafy2','1234','이싸피','남','닉네임2','안녕히계세요'),
('ssafy3','1234','이싸피','남','닉네임3','빠잉'); 
SELECT * FROM user;

CREATE TABLE location (
    location_id INT(100) PRIMARY KEY NOT NULL,
    place_name VARCHAR(100) NOT NULL,
    address_name VARCHAR(100) NOT NULL,
    fav_cnt INT(10) DEFAULT 0,
    longitude DECIMAL(10,7) NOT NULL,
    latitude DECIMAL(10,7) NOT NULL
);

INSERT INTO location (location_id, place_name, address_name, fav_cnt, longitude, latitude)
VALUES (1, '장소이름','주소',0,00.0000,00.0000);
SELECT * FROM location;

CREATE TABLE community (
    community_id INT(10) AUTO_INCREMENT PRIMARY KEY,
    community_name VARCHAR(100) NOT NULL,
    community_description VARCHAR(300) NOT NULL
);
-- Community 데이터
INSERT INTO community (community_name, community_description)
VALUES ('프리다이빙','프리다이빙을 위한 커뮤니티'),
('스킨스쿠버','스킨스쿠버를 위한 커뮤니티');
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
    location_id INT(100),
    fav_cnt INT(10) DEFAULT 0,
    FOREIGN KEY (community_id) REFERENCES community(community_id) ON DELETE CASCADE,
    FOREIGN KEY (writer) REFERENCES user(nickname) ON DELETE CASCADE ON UPDATE CASCADE
);
-- Board 데이터
INSERT INTO board (community_id, num, header, title, writer, content)
VALUES (1, 1,'자유','제목1','닉네임','내용1'),
(1, 2,'자유','제목2','닉네임','내용2'),
(1, 3,'자유','제목3','닉네임','내용3'),
(2, 1,'자유','제목4','닉네임2','내용4'),
(2, 2,'자유','제목5','닉네임','내용5'),
(1, 4,'자유','제목3','닉네임','내용3'),
(1, 5,'자유','제목3','닉네임','내용3'),
(1, 6,'자유','제목3','닉네임','내용3'),
(1, 7,'자유','제목3','닉네임','내용3'),
(1, 8,'자유','제목3','닉네임','내용3'),
(1, 9,'자유','제목3','닉네임','내용3'),
(1, 10,'자유','제목3','닉네임','내용3'),
(1, 11,'자유','제목3','닉네임','내용3'),
(1, 12,'자유','제목3','닉네임','내용3'),
(1, 13,'자유','제목3','닉네임','내용3'),
(1, 14,'자유','제목3','닉네임','내용3'),
(1, 15,'자유','제목3','닉네임','내용3'),
(1, 16,'자유','제목3','닉네임','내용3'),
(1, 17,'자유','제목3','닉네임','내용3'),
(1, 3,'자유','제목3','닉네임','내용3'),
(1, 3,'자유','제목3','닉네임','내용3');
SELECT * FROM board;

CREATE TABLE favorite_board (
    favorite_boardId INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    board_id INT(10) NOT NULL,
    community_id INT(10) NOT NULL,
    writername VARCHAR(300) NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board(board_id) ON DELETE CASCADE,
    FOREIGN KEY (writername) REFERENCES user(nickname) ON DELETE CASCADE ON UPDATE CASCADE,
  	FOREIGN KEY (community_id) REFERENCES board(community_id) ON DELETE CASCADE ON UPDATE CASCADE

);
INSERT INTO favorite_board
VALUES (1,1,1,'닉네임'),
(2,2,1,'닉네임2'),
(3,2,2,'닉네임2');
SELECT * FROM
favorite_board;

CREATE TABLE favorite_location (
    favorite_locationId INT(10) AUTO_INCREMENT PRIMARY KEY,
    writername VARCHAR(300) NOT NULL,
    location_id INT(100) NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE,
    FOREIGN KEY (writername) REFERENCES user(nickname) ON DELETE CASCADE ON UPDATE CASCADE
);
SELECT * FROM favorite_location;
CREATE TABLE reply (
    reply_id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    board_id INT(10) NOT NULL,
    community_id INT(10) NOT NULL,
    writer VARCHAR(300) NOT NULL,
    content TEXT NOT NULL,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (board_id) REFERENCES board(board_id) ON DELETE CASCADE,
    FOREIGN KEY (writer) REFERENCES user(nickname) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Reply 데이터
INSERT INTO reply (board_id, community_id, writer, content, reg_date, is_modified)
VALUES (1, 1, '닉네임', '댓글내용1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 1, '닉네임2', '댓글내용1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 2, '닉네임2', '댓글내용1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, '닉네임', '댓글내용1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
SELECT * FROM reply;

CREATE TABLE follow (
	follow_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    follow_from VARCHAR(300) NOT NULL,
    follow_to VARCHAR(300) NOT NULL,
    FOREIGN KEY (follow_from) REFERENCES user(email) ON DELETE CASCADE,
    FOREIGN KEY (follow_to) REFERENCES user(email) ON DELETE CASCADE
);
INSERT INTO follow (follow_from, follow_to)
VALUES ('ssafy', 'ssafy2'),
('ssafy', 'ssafy3'),
('ssafy2','ssafy'),
('ssafy2','ssafy3');
SELECT * FROM follow;

SELECT b.writer, b.content, b.reg_date  FROM board as b INNER JOIN favorite_board as fb ON b.board_id = fb.board_id;