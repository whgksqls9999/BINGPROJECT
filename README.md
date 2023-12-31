
---

## 프로젝트 개요

- Spring Boot, Vue.js 기반 온라인 커뮤니티 웹 사이트 구축
- 개발 기간 : 23.11.13 ~ 23.11.24 (2주)
---

## 팀원 소개 및 역할 분담

- 공통 사항
    - 데이터베이스 설계 및 SPRING BOOT REST API 구현
    - axios 기반 프론트엔드 - 백엔드 데이터 통신 기능 구현
- 김은지
    - CSS 레이아웃 디자인 설계 및 구현
    - 유저 계정, 게시글, 댓글 관련 CRUD 기능 구현
    - 공공데이터포탈 기반 날씨 API 기능 구현
- 조한빈
    - CSS 레이아웃 디자인 구현
    - 게시글, 장소 좋아요(찜) / 유저간 팔로잉 기능 구현
    - 카카오맵 API 활용 지도 기능 구현
---
## 기술 스택

### 개발 환경

<img src="https://img.shields.io/badge/Visual%20Studio%20Code-007ACC.svg?&style=for-the-badge&logo=Visual%20Studio%20Code&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">

### 개발
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">   <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> 
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 

### 커뮤니케이션

<img src="https://img.shields.io/badge/Mattermost-0058CC?style=for-the-badge&logo=Mattermost&logoColor=white"><img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"><img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white">

---
## 구현 기능

- 유저 계정
    - 로그인
        - JWT 토큰 이용, 로그인 시 DB 내 ID, PW 데이터 조회하여 유효성 검사 후 Session에 User Token값 저장
    - 로그아웃
        - Session에 저장된 Token 값 삭제
    - 회원 가입
        - DB내 저장된 유저 계정 값을 확인하여 중복 아이디, 닉네임 검사
        - 프론트엔드 단에서 패스워드 유효성 검사(특수문자, 8글자 이상)
    - 회원 탈퇴
        - DB 내 해당 계정 데이터 제거
- 게시판(커뮤니티) 관리
    - Community 데이터 베이스 테이블을 통해 2개의 게시판을 관리
- 게시글/댓글
    - Spring boot를 통해 CRUD API 작성, 프론트엔드 단에서 axios을 이용하며 해당 작업 수행
    - 게시글/댓글 작성 시 작성자 정보와 로그인 유저의 정보를 확인하여 수정/삭제 버튼 활성화 여부 체크
    - 게시글 등록 화면은 Vue.js의 네비게이션 가드를 통해 비로그인시 접근하지 못하게 구성
    - 게시글 작성시 ‘장소추천’ 카테고리의 경우, 카카오맵 API를 이용하며 원하는 장소를 검색 및 이를 포함하여 등록이 가능하게 함
    - 이전글/다음글 보기 버튼을 통해 커뮤니티 내 이전/다음글 조회 가능
        - 이전글, 다음글이 없을 시 Alert창을 통해 표시
- 날씨
    - 공공데이터포털에서 제공하는 날씨 API를 이용
    - 3시간마다 갱신되는 정보를 화면 헤더 좌상단에 출력
- 게시글/장소 좋아요
    - 로그인 여부에 따라 좋아요 버튼 표기/미표기
    - 좋아요 버튼 눌렀을 시, 좋아요 취소 버튼으로 전환
- 마이페이지
    - 내 정보 확인
        - 회원 정보 조회 가능
        - 회원 정보 중, 팔로워/팔로잉 버튼 클릭 시 로그인 유저의 팔로우 정보를 모달로 출력
    - 내 정보 수정
        - 회원 정보 수정 기능
        - 개인정보 수정 시 비밀번호 유효성 검사를 재수행
    - 좋아요 정보
        - 좋아요 버튼을 눌러 저장한 게시글/장소 표로 출력
    - 작성 게시물 정보
        - 작성했던 게시물, 댓글 정보를 표로 출력
    - 사이드바
        - 사이드바 버튼위에 마우스를 올리면 숨겨져 있던 사이드바가 움직여 화면에 출력

---
## 화면 구성

- 메인화면
    - 웹페이지 소개
    - <img width="80%" src="https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/9ddb0280-1f7d-4a50-a431-57798bd6b01c"/>
    - 로그인![2_로그인](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/878096c7-5189-495d-b570-95a556ae968c)
    - 회원가입![3_회원가입](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/543129f0-1c0f-41b9-b50c-fe66d6cb7af9)

- 커뮤니티 화면![4_커뮤니티 화면](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/4ea1c09f-23a6-4c71-83ac-6c412fdbd1ef)

- 게시글 리스트 화면![5_게시글 리스트](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/56e6168e-acc7-4141-abba-8641e438576f)

- 게시글 디테일 화면
    - 일반
    - ![6_게시글 디테일(일반)](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/838b2021-a5d3-433a-82c7-4ee536fe8d70)

    - 지도
    - ![7_게시글 디테일(지도)](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/d93428b7-1014-4970-8b7a-c675ada04eaa)

- 게시글 작성 화면
    - 일반
    - ![8_게시글 작성(일반)](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/ae43e297-970e-4071-840e-4af4280a629a)

    - 지도
    - ![9_게시글 작성(지도)](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/fc6177e0-0bb1-40dd-912a-a1676d2d1573)
![10_게시글 작성(장소 선택)](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/efc70071-52b3-44c6-aa7a-fed475e15bed)
![11_게시글 작성(장소 선택 후)](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/0152c93e-c09c-4586-80d9-fb7cdfec28b2)

- 댓글 작성 
- ![12_댓글 작성](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/358f6253-5daa-4f39-b370-63a5262f53c4)
- 댓글 수정
![13_댓글 수정](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/467aea43-f085-4d8f-877c-e0b0de1497b9)

- 마이페이지
- ![14_마이 페이지](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/880a4e76-7611-4d12-9354-03571ab63d69)

    - 내 정보 수정![15_개인정보 수정](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/93af5eff-c5cd-4e19-8dea-edc985b52d55)

    - 작성 게시물 목록![16_내가 쓴 게시물](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/bf3be353-b579-421e-a699-f5dd12ba0a53)

    - 찜 정보 목록![17_내가 찜한 게시물](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/80d5f080-d53a-4f6c-9331-c250ca1e424d)
 
    - 타 유저 정보 확인![18_타 유저 정보 확인](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/b3d46869-e326-4d13-9dd5-46a1d765171d)
    - 
    - 내 팔로우 확인![19_내 팔로우정보](https://github.com/BINGPROJECTS/BINGPROJECT/assets/85666312/3bc26614-80d3-4d2e-a683-9be076ec12cd)

