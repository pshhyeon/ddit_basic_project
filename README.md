# JAVA & ORACLE 사용 CRUD 프로젝트
 - Tools :
<img src="https://github.com/pshhyeon/ddit_basic_project/assets/130214802/a191ee0a-bd26-4a23-9837-48ce7be73fe0" alt="eclipseide-color" width="15"> Eclipse
/ <img src="https://github.com/pshhyeon/ddit_basic_project/assets/130214802/2ca2ab1d-0f91-4b1b-b4f8-e33dad57a7a8" alt="oracle-color" width="15"> Oracle Developer
/ <img src="https://github.com/pshhyeon/ddit_basic_project/assets/130214802/c083d33d-c35b-481f-b738-1523b0d677fd" alt="git-color" width="15"> Git
/ <img src="https://github.com/pshhyeon/ddit_basic_project/assets/130214802/70c96c6f-69e1-4e88-a092-eb92088ff683" alt="github-color" width="15"> Github

<br>

## 자바 콘솔로 구현하는 간단한 MVC패턴 [강의 시스템] 구현

<br>

## 주요기능
- 1. 사용자 구분: 일반회원, 강사, 관리자
- 2. 수강신청, 강의 등록, 리뷰 작성 등 기본적인 기능 구현
- 3. 내 강의실에서 현재 수강중인 기능, 과거 수강 내역 등 조회
- 4. 조회된 각 리스트 검색 & 페이징 처리를 통해 편리한 접근 가능
- 5. 다양한 타입 구분으로 편리한 강의 등록
- 6. 불건전, 악성 리뷰 블라인드 처리

<br>

## 제작 과정
### 1. 사용자 요구사항 분석 및 정의 후 테이블명세서 추출

### 2. 테이블 명세서에 따른 ERD설계

### ![ERD](https://github.com/pshhyeon/ddit_basic_project/assets/130214802/74e86e24-2222-493c-ae71-e3b0504989b9)

### 3. DB생성
![111111111](https://github.com/pshhyeon/ddit_basic_project/assets/130214802/4da7c5a6-6238-4efd-b1fa-ef26e677d124)
![2222222222222222](https://github.com/pshhyeon/ddit_basic_project/assets/130214802/ea3217a7-7d96-4eaf-8d4b-5221528990b5)
### 4. JAVA 코딩
 - Controller <> Service <> Dao <> DB
1. Controller: 사용자에게 정보 제공
2. Service: 데이터 편집
3. Dao: DB에 데이터 요청
4. DB: 데이터 저장

<br>
