# 공공 와이파이 웹서비스

<img  alt="HTML5" src="https://img.shields.io/badge/html5-E34F26?style=flat-square&logo=html5&logoColor=white"> <img alt="CSS3" src="https://img.shields.io/badge/css3-1572B6?style=flat-square&logo=CSS3&logoColor=white">
<img alt="JavaScript" src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black">
<img alt="jQuery" src="https://img.shields.io/badge/jquery-0769AD?style=flat-square&logo=jquery&logoColor=white">
<img alt="Bootstrap" src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat-square&logo=Bootstrap&logoColor=white">
<img alt="Apache Tomcat" src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat-square&logo=Apache Tomcat&logoColor=black">
<img alt="Gradle" src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white">
<img alt="MySQL" src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white">

내 위치 기반 공공 와이파이 정보를 제공하는 웹서비스 개발

# 주요 기능

- 공공 데이터 Wifi 조회 후 페이징 처리 및 DataBase 저장
- 내 위치 조회 후 근처 와이파이 정보 20개 조회
- 히스토리 조회 및 삭제

# 프로젝트 구조 및 설명
[document](https://github.com/donghun93/public-wifi-service/tree/master/document)

document에는 ERD 및 아키텍처 구성도가 있습니다.

[servlet 패키지](https://github.com/donghun93/public-wifi-service/tree/master/src/main/java/com/zerobase/servlet)

servlet 패키지는 프론트 컨트롤러 패턴을 적용하여 사용자 요청을 특정 컨트롤러에 위임하는 기능이 구현되었습니다.
  - [ZeroDispatcherServlet](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/servlet/ZeroDispatcherServlet.java) - 프론트 컨트롤러 패턴 적용
  - [HandlerMappingsManager](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/servlet/manager/HandlerAdaptersManager.java) - 핸들러 목록 초기화
  - [HandlerAdaptersManager](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/servlet/manager/HandlerAdaptersManager.java) - 핸들러 어댑터 초기화
  - [ModelAndView](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/servlet/mvc/ModelAndView.java) - 컨트롤러에서 View Path 및 Model 사용 용도
  - [View](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/servlet/mvc/View.java) - 화면 렌더링

[api 패키지](https://github.com/donghun93/public-wifi-service/tree/master/src/main/java/com/zerobase/api)

api 패키지는 외부 api와 연동 후 오브젝트로 파싱하는 기능이 구현되었습니다.
  - [OkHttp3WifiApiProcessor](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/api/processor/okhttp3/OkHttp3WifiApiProcessor.java) - Wifi JSON 데이터 요청 처리
  - [GsonWifiApiMessageParser](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/api/parser/okhttp3/GsonWifiApiMessageParser.java) - JSON 데이터 오브젝트로 파싱
  - [WifiApiServiceImpl](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/api/WifiApiServiceImpl.java) - Wifi Api 서비스
  
[publicwifiservice 패키지](https://github.com/donghun93/public-wifi-service/tree/master/src/main/java/com/zerobase/publicwifiservice)

publicwifiservice 패키지는 레이어드 아키텍처(Layered Architecture) 구조로 역할과 책임이 분리하여 구성되었습니다. 
  - [WifiController](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/publicwifiservice/controller/WifiController.java) - 사용자 요청 처리
  - [WifiService](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/publicwifiservice/service/WifiService.java) - 와이파이 비즈니스 로직 처리
  - [HistoryService](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/publicwifiservice/service/HistoryService.java) - 히스토리 비즈니스 로직 처리
  - WifiRepository - 와이파이 데이터 처리 추상화
    - [ZeroBaseJdbcWifiRepository](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/publicwifiservice/repository/jdbc/ZeroBaseJdbcWifiRepository.java) - 와이파이 데이터 처리 구현 (템플릿/콜백 패턴 적용)
  - HistoryRepository - 히스토리 데이터 처리 추상화
    - [ZeroBaseJdbcHistoryRepository](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/publicwifiservice/repository/jdbc/ZeroBaseJdbcHistoryRepository.java) - 히스토리 데이터 처리 구현 (템플릿/콜백 패턴 적용)
  - Template Callback Pattern 
    - [ZeroBaseJdbcTemplate](https://github.com/donghun93/public-wifi-service/blob/master/src/main/java/com/zerobase/publicwifiservice/repository/jdbc/jdbctemplate/ZeroBaseJdbcTemplate.java) - JDBC의 반복되는 try / catch / finally 코드 분리
    
[리소스](https://github.com/donghun93/public-wifi-service/tree/master/src/main/resources)   
- application.properties : api, 데이터베이스 접속 정보 설정
- data.sql : 데이터베이스, 테이블 생성 스크립트


# Dependencies

| 라이브러리명               | 버전        |
|----------------------|-----------|
| okhttp3              | v4.9.3   |
| gson                 | v2.9.1   |
| tomcat-servlet-api   | v8.5.82  |
| mysql-connector-java | v8.0.30  |
| jstl | v1.2     |
| lombok | v1.18.24 |
