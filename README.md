# springboot-board

## 오류 list-up
#### 1. user table 이 비어있는 경우 게시글 추가 에러 발생
user 테이블에 직접 데이터 추가.
``` mysql
INSERT INTO user (name)
VALUE ('user1'), ('user2'), ('user3'), ('user4'), ('user5');
```
