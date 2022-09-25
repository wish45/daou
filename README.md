# daou

로그파일 저장 경로
C:\Users\newhyodong\daou\log

결제정보파일 읽어들이는 경로
C:\Users\newhyodong\daou


# 작업내용

1. 스케쥴러를 통한 결제정보 저정 api 구현

2. 결제정보 등록, 수정, 삭제, 조회 api 구현
<ol>application.yml에 설정된 ip에 한에서 접근가능</ol>
<ol>aop를 통한 메서드 전후에 실행시간 로그기록</ol>

3. api호출시 spring security를 통한 인증 및 인가 작업 구현
<ol> - rate limit(진행중)</ol>

4. api 테스트 코드 작성
<ol> -test code (진행중)</ol>


# 서버구동 방법

1. data.sql에 쿼리문으로 h2디비에 데이터 생성

2. http://localhost:8080/api/authenticate  를 호출하여 token 생성

3. api 호출
<ol>get- http://localhost:8080/payment</ol>
<ol>post- http://localhost:8080/payment</ol>
<ol>del - http://localhost:8080/payment/deletePayment</ol>
<ol>patch - http://localhost:8080/payment/updatePayment</ol>

4. 스케쥴러는 매 12시정각에 C:\Users\newhyodong\daou 위치에서 데이터를 읽어서 디비에 저장.
