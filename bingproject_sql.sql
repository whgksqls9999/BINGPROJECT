DROP SCHEMA IF EXISTS BINGPROJECT;
CREATE SCHEMA BINGPROJECT;
use BINGPROJECT;

-- USE ssafy_board;
-- select * from board;reg_date

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

select * from user;

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
    content VARCHAR(300) NOT NULL,
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    location_id INT(10),
    view_cnt INT(10) DEFAULT 0,
    FOREIGN KEY(community_id) REFERENCES community(community_id),
    FOREIGN KEY(location_id) REFERENCES location(location_id)
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
    reg_date DATE NOT NULL,
    is_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (board_id) REFERENCES board(board_id)
);

INSERT INTO user
VALUES ('ssafy', '1234', 111, 11, '김싸피', '남', '닉네임'),
('admin','1234',111,11,'관리자','남','닉네임2');
INSERT INTO location
VALUES (1, '김밥천국', 0,0);
INSERT INTO favorite
VALUES (1, 'ssafy', 1);
INSERT INTO community
VALUES (1,1,'admin',1);
INSERT INTO board
VALUES (1, 1, 1,'a','b','c','d',0,0,1,1);

SELECT * FROM favorite
WHERE user_email = 'ssafy';
select * from favorite;
SELECT * FROM board;
SELECT * FROM user;
SELECT * FROM location;
SELECT * FROM reply;
SELECT * FROM community;