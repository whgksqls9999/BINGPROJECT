DROP SCHEMA IF EXISTS BINGPROJECT;
CREATE SCHEMA BINGPROJECT;

use BINGPROJECT;


create table user (
    -- id VARCHAR(100) AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(300) PRIMARY KEY NOT NULL,
    pw VARCHAR(300) NOT NULL,
    name VARCHAR(40) NOT NULL,
    gender VARCHAR(30) NOT NULL,
    nickname VARCHAR(100) NOT NULL
);

CREATE TABLE community(
    community_id INT(10) AUTO_INCREMENT PRIMARY KEY,  -- 커뮤니티 식별자
    community_name VARCHAR(100) NOT NULL,             -- 커뮤니티 이름
    -- admin_id VARCHAR(300) NOT NULL,                -- 해당 커뮤니티를 개설한 유저 : 요거는 추가 커뮤니티 안 만들거니까 일단 삭제
    community_description VARCHAR(300) NOT NULL      -- 커뮤니티 설명
    -- FOREIGN KEY (admin_id) REFERENCES user(email)
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
    header VARCHAR(10) NOT NULL, -- 말머리 Category
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
    favorite_type VARCHAR(10) NOT NULL, -- 'location' 과 'board' 두개 중 하나로 구분
    user_email VARCHAR(100) NOT NULL,
    location_id INT(10),
    board_id INT(10),
    FOREIGN KEY(user_email) REFERENCES user(email)
    -- FOREIGN KEY(location_id) REFERENCES location(location_id) : faovrite_type이 게시물인지, 장소에 따라 location_id가 안 들어올 수도 있음. 그래서 FK로 둘 수 없다.
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
INSERT INTO user (email,pw,height,weight,name,gender,nickname)
VALUES ('ssafy', '1234', 111, 11, '김싸피', '남', '닉네임'),
('ssafy2','1234',111,11,'이싸피','남','닉네임2'),
('ssafy3','1234',111,11,'이싸피','남','닉네임2');

-- Location 데이터
INSERT INTO location
VALUES (0, 'none', 0, 0);

INSERT INTO location (location_name, longitude, latitude)
VALUES ('김밥천국', 0,0);

-- Community 데이터
INSERT INTO community (community_name, community_description)
VALUES ('프리다이빙','프리다이빙을 위한 커뮤니티'),
('스킨스쿠버','스킨스쿠버를 위한 커뮤니티'),
('해녀','해녀를 위한 커뮤니티');

-- Board 데이터
INSERT INTO board (community_id, num, header, title, writer, content)
VALUES (1, 1,'자유','제목1','ssafy','내용1'),
(1, 2,'자유','제목2','ssafy','내용2'),
(1, 3,'자유','제목3','ssafy','내용3');

-- Favorite 데이터
INSERT INTO favorite (favorite_type, user_email, location_id, board_id)
VALUES ('location', 'ssafy', 1, null),
('board','ssafy', null, 1);

-- Reply 데이터
INSERT INTO reply (board_id, writer, content, reg_date, is_modified)
VALUES (1, 'ssafy', '댓글내용1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

select * from favorite;
SELECT * FROM board;
SELECT * FROM user;
SELECT * FROM location;
SELECT * FROM reply;
SELECT * FROM community;