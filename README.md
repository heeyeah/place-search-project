# place-search-project

## 과제 내용
오픈 API를 이용한 **장소 검색 서비스** 웹페이지 구현


## 실행방법
#### server
1. [v1.0-release](https://github.com/heeyeah/place-search-project/releases) 실행가능한 jar파일 다운로드
2. `cd <<Download경로>>`
3. `java -jar place-search-project-1.0.0.jar`

#### frontend
1. `git clone https://github.com/heeyeah/place-search-project.git`
2. `cd place-search-project/vue-front`
3. `npm install`
4. `npm run serve`
5. [장소검색서비스-http://localhost:8080](http://localhost:8080) 접속

<br/>

## 프로젝트 구성환경(Feature)

#### server
* Spring Boot
  * RESTful API
  * Build with Gradle
* Java8
* H2 in-memory DB
* Swagger *(API 문서 자동화를 위해 사용)*
* Junit

#### frontend
* Vue.js
  * node & npm
  * vue-cli 3.0

<br/>

## 구현과정
* 동일 출처 정책(SOP, Same Origin Policy) 제약 사항 CORS 처리
* API 명세를 자동으로 보기 위해 Swagger 사용
* AOP 개념을 사용해 Exception Handling 구현
* 사용자 데이터 저장 및 비밀번호 암호화
  * 어플리케이션이 구동되는 시점에 USER00 ... USER19, 총 20명의 사용자 데이터를 in-memory DB에 저장
  * 비밀번호는 사용자ID(ex. USER00)를 암호화한 값 저장
* 테스트 케이스
  * 암호화된 비밀번호 값 저장 확인 테스트케이스 작성
  * Controller 레벨의 테스트케이스 작성




---
### 기본 요구사항

1. 로그인
    - 사용자의 아이디와 비번으로 로그인을 할 수 있어야 합니다. 단, 사용자 데이터는 애플리케이션 실행 시점에 생성합니다.
    - 비밀번호는 암호화해서 저장해 주세요.

2. 장소 검색
    - 키워드를 통해 장소를 검색할 수 있어야 합니다.
    - 검색 결과는 Pagination 형태로 제공해 주세요.
    - 검색 소스는 카카오 API의 키워드로 장소 검색(https://developers.kakao.com/docs/restapi/local#키워드-검색)을 활용합니다.

3. 검색된 장소의 상세 조회
    - 각 검색 결과의 상세 정보(지번, 도로명주소, 전화번호 등)를 확인할 수 있어야 합니다.
    - 상세 정보에는 Daum 지도 바로가기 URL이 제공되어야 합니다.
    - Daum 지도 Web API의 바로가기(http://apis.map.daum.net/web/guide/#bigmapurl)를 참고해 주세요.

4. 내 검색 히스토리
    - 나의 검색 히스토리(키워드, 검색 일시)를 최신 순으로 보여 주세요.

5. 인기 키워드 목록
    - 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공합니다.
    - 키워드 별로 검색된 횟수도 함께 표기해 주세요.

### 우대사항

1. Front-end 추가 요건
    - SPA(Single Page Application)으로 구현해 주세요.
    - 상세 정보에는 지도가 제공되어야 합니다.
        - 지도 표기는 Daum 지도 Web API(http://apis.map.daum.net/web/)를 이용합니다.
        - 검색된 장소를 나타내는 커스텀 오버레이를 띄우고, 장소 이름과 주소를 표기해 주세요.

2. Back-end 추가 요건
    - JPA를 사용하여 구현해 주세요.


### 제약사항

- JAVA 8 이상
- Spring Boot 사용
- Gradle 또는 Maven 기반의 프로젝트
- DB는 메모리 DB 사용
- 클라이언트 구현에 Javascript 사용
- 외부 라이브러리 및 오픈소스 사용 가능 (단, README 파일에 사용한 오픈 소스와 사용 목적을 명시해 주세요)
