AOP 공부
-------
기록 : [쿠키린 티스토리-AOP 카테고리](https://hyeonddobbi.tistory.com/category/%E2%97%86SPRING%20BOOT/AOP)


## AOP가 필요한 상황
모든 메소드의 호출 시간을 측정하고 싶다면?
ㄴ 메소드가 1000개라면?
공통 관심 사항 VS 핵심 관심 사항
회원 가입 시간, 회원 조회 시간 측정하고 싶다면?
<br/>
aop 등록 관리(경로) : src/main/java/hello/aop/SpringConfig.java


## h2 데이터베이스(경량 DB, 공부할때 사용하기 좋음.)
설치 : https://h2database.com/html/main.html<br/>
build.gradle 설정:
---
	implementation 'org.springframework.boot:spring-boot-starter-jdbc'
	runtimeOnly 'com.h2database:h2'
---
검색창 : h2 console 실행
http://localhost:8082/login.jsp?jsessionid=f58f8fe3fc3abbb0b2cb5bed0d39d216
---
select *from member
<br/>
insert into member(name) values('member2')
---
DDL 관리(경로) : sql/ddl.sql <br/>