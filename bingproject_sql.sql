DROP SCHEMA IF EXISTS BINGPROJECT;
CREATE SCHEMA BINGPROJECT;

use BINGPROJECT;


create table user (
    -- id VARCHAR(100) AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(300) PRIMARY KEY NOT NULL,
    pw VARCHAR(300) NOT NULL,
    height INT(10) NOT NULL,
    weight INT(10) NOT NULL,
    name VARCHAR(40) NOT NULL,
    gender VARCHAR(30) NOT NULL,
    nickname VARCHAR(100) NOT NULL
);

CREATE TABLE community(
    community_id INT(10) AUTO_INCREMENT PRIMARY KEY,  -- 커뮤니티 식별자
    community_name VARCHAR(100) NOT NULL,             -- 커뮤니티 이름
    admin_id VARCHAR(300) NOT NULL,                   -- 해당 커뮤니티를 개설한 유저
    community_description VARCHAR(300) NOT NULL,      -- 커뮤니티 설명
    FOREIGN KEY (admin_id) REFERENCES user(email)
);

create table location(
    location_id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    location_name VARCHAR(100) NOT NULL,
    longitude DECIMAL(10,7) NOT NULL,
    latitude DECIMAL(10,7) NOT NULL
);

CREATE TABLE board(
    board_id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    community_id INT(10) NOT NULL,
    num INT(10) NOT NULL,
    header VARCHAR(10) NOT NULL, -- 말머리
    title VARCHAR(100) NOT NULL,
    writer VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    view_cnt INT(10) DEFAULT 0, 
	location_id INT(10),  -- 말머리(headr)가 장소 추천일때만 존재
    FOREIGN KEY(community_id) REFERENCES community(community_id)
    -- FOREIGN KEY(location_id) REFERENCES location(location_id) location_id 값을 등록 안해도 작성이 돼야하는데 외래키로 둬서 그런가 이게 안됨,,, 임시 주석
);

CREATE TABLE favorite(
    favorite_id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL, 
    user_email VARCHAR(100) NOT NULL,
    location_id INT(10) NOT NULL,
    FOREIGN KEY(user_email) REFERENCES user(email),
    FOREIGN KEY(location_id) REFERENCES location(location_id)
);

CREATE TABLE reply (
    reply_id INT(10) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    board_id INT(10) NOT NULL,
    writer VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (board_id) REFERENCES board(board_id)
);

-- User 데이터
INSERT INTO user
VALUES ('ssafy', '1234', 111, 11, '김싸피', '남', '닉네임'),
('admin','1234',111,11,'관리자','남','닉네임2');

-- Location 데이터
INSERT INTO location
VALUES (0, 'none', 0, 0);

INSERT INTO location (location_name, longitude, latitude)
VALUES ('김밥천국', 0,0);

-- Favorite 데이터
INSERT INTO favorite
VALUES (1, 'ssafy', 1);

-- Community 데이터
INSERT INTO community
VALUES (1,1,'admin',1);

-- Board 데이터
INSERT INTO board (community_id, num, header, title, writer, content)
VALUES (1, 1,'자유','제목1','ssafy','내용1'),
(1, 2,'자유','제목2','ssafy','내용2'),
(1, 3,'자유','제목3','ssafy','내용3');

-- Reply 데이터
INSERT INTO reply (board_id, writer, content, reg_date, is_modified)
VALUES (1, 'admin', '댓글내용1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

select * from favorite;
SELECT * FROM board;
SELECT * FROM user;
SELECT * FROM location;
SELECT * FROM reply;
SELECT * FROM community;