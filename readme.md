## 요구사항 정리

- 방탈출 시간표가 정해져 있는데 직접 입력하기 번거로워서 선택하는 방식으로 수정하려합니다.
  -> 비즈니스 규칙 추가
- 기존에 구현한 예약 기능에서 시간을 시간 테이블에 저장된 값만 선택할 수 있도록 수정하세요.

## 구현 사항

- [x] 시간 추가 기능 구현
    - POST /times HTTP/1.1
- [x] 시간 조회 기능 구현
    - GET /times HTTP/1.1
- [x] 시간 삭제 기능 구현
    - DELETE /times/1 HTTP/1.1
- [x] Time을 구현
- [x] Time 스키마 추가
- [x] TimeDao를 구현
    - [x] Time 저장로직
    - [x] Time 조회로직
    - [x] Time 삭제로직

- top -> down 밖에서부터 안으로
    - 얘는 내부 구현방향이 정확하지 않을 때 짜기 좋은거임.
- bottom -> up 안에서부터 밖으로
    - 내부 구현이나 추상화가 이미 머리속에 잘 정리 때 쓰면 좋다.

---

### 9단계

- [x] templates/reservation.html 대신 templates/new-reservation.html 파일을 활용하세요
- [x] 테이블 스키마 재정의
    - 외래키 지정을 통해 reservation 테이블과 time 테이블의 관계를 설정해주세요.
- [x] 예약 쿼리수정
    - [x] 예약 추가 쿼리 수정
    - [x] 예약 조회 쿼리 수정
- [ ] 시간 타입을 String에서 Time 객체로 수정하세요.


