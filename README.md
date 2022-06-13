# hanghae99Homework02




5. CORS 해결하기
- CORS는 한 도메인이 다른 도메인 (도메인 간 요청)을 가진 리소스에 액세스 할 수 있게하는 보안 메커니즘이다.
- CORS는 동일한 출처의 리소스에만 접근하도록 제한한다. 여기서 출처는 프로토콜, 호스트명, 포트가 같다는 것을 의미한다.
- CORS를 가장 쉽게 적용시키는 방법은 JWT토큰을 사용하는 것이라고 생각해 Session대신 Jwt를 적용했다.



# 트러블슈팅!
1. 스파르타 강의에서는 UserDetailsImpl에 User 객체를 넣어서 관리 했지만 객체를 넣을 경우 db쿼리문이 계속 발생하는 문제가 있다.(강의에서 정정하는것을 퀴즈로 내주고 답은 공개하지 않았다.) 
- 해결 - UserDetailsImpl의 User객체를 id,password,roles String타입으로 바꿔서 저장했다.

2. (should be mapped with insert="false" update="false") 에러 board테이블에서 자꾸 해당 에러가 뜬다.
- 해결 - JoinColumn의 name이 중복되서 나타나는 문제였다. name은 내 테이블에 설정한 문자로 컬럼을 생성하라는 뜻으로 상대 테이블과 매핑시켜주는 역할을 하지 않는다.

3. (More than one row with the given identifier was found) 에러 게시글을 3개 작성했을 때 나온다.
- 해결 - 게시글과 유저는 OneToMany 관계인데 OneToOne을 사용했을 때 나오는 에러였다..

4. 게시글 작성시 userDetailsImpl 객체에서 유저 정보를 받아오지만 Board객체를 생성할때엔 User객체가 필요해 결국 User관련 DB쿼리문이 2번 발생한다. 1번문제 다시 발생..  객체대신 아이디만 저장해야할까?(그럼 외래키는 어떻게 해야하지?)
