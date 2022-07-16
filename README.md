## 개발환경
JAVA 11, Spring boot, JPA 사용

## DB 설계
* 주어진 예제와 같은 테이블 생성 

        CREATE TABLE test.product_info (
          seq int(11) auto_increment NOT NULL,
          category varchar(100) NOT NULL,
          brand varchar(100) NOT NULL,
          price int(11) NOT NULL,
          CONSTRAINT product_info_pk PRIMARY KEY (seq)
        )
        ENGINE=InnoDB
        DEFAULT CHARSET=utf8mb4
        COLLATE=utf8mb4_general_ci;
        
## API 명세

### 1) 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가 조회 API

#### 1. URL

```http
GET /api/campaigns/?api_key=12345678901234567890123456789012
```

```javascript
{
  "message" : string,
  "success" : bool,
  "data"    : string
}
```
