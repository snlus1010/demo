## 개발환경
JAVA 11, Spring boot, JPA 사용

## DB 설계
* 주어진 예제와 같이 간단하게 테이블 생성 
* 첨부된 sample.sql 파일에 Create, Insert 문 추가 
-> application.properties 의 `spring.jpa.hibernate.ddl-auto=create` 옵션 주석 해제하면 자동으로 create table 가능
-> 단점은 application 재실행시 계속해서 테이블생성하여 데이터가 삭제됨. 
* Datasource 는 application.properties 에서 수정가능 ( 현재 mariadb, oracle 만 포함되어있음 )

| Column | Type | Description |
| :--- | :--- | :--- |
| `seq` | `int` | 상품번호 |
| `category` | `string` | 카테고리 |
| `brand` | `string` | 브랜드 |
| `price` | `int` | 가격 |
 
        
## API 명세

### 1. 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가 조회 API

#### 1) URL
```http
GET /searcher/search/lowest-price
```
#### 2) Response
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `timestamp` | `string` | 시간(yyyy-MM-dd HH:mm:ss) |
| `status` | `int` | 상태 코드 |
| `message` | `string` | 내용 |
| `result` | `Object` | 결과 |
|`productInfoVo` | | |
|  | `seq` (`int`)  |상품 번호|
|  | `category` (`string`) | 카테고리|
|  | `brand`(`string`) |  브랜드명|
|  | `price`(`int`) | 가격|
|  | `totalPrice`(`int`)| 총액|


```javascript
{
  {
    "timestamp": "2022-07-17 00:23:54",
    "status": 200,
    "result": {
        "productInfoVo": [
            {
                "seq": 5,
                "category": "가방",
                "brand": "A",
                "price": 2000
            },
            {
                "seq": 30,
                "category": "모자",
                "brand": "D",
                "price": 1500
            },
            ....
        ],
        "totalPrice": 34300
    },
    "message": "SUCCESS"
}
}
```

### 2) 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회  API

#### 1) URL
```http
GET /searcher/search/one-brand
```
#### 2) Response
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `timestamp` | `string` | 시간(yyyy-MM-dd HH:mm:ss) |
| `status` | `int` | 상태 코드 |
| `message` | `string` | 내용 |
| `result` | `Object` | 결과 |
|  | `brand`(`string`)|브랜드명|
|  | `totalPrice`(`string`) | 브랜드명|


```javascript
{    
    "timestamp": "2022-07-17 00:35:03",
    "status": 200,
    "result": {
        "brand": "D",
        "totalPrice": 36100
    },
    "message": "SUCCESS"
}
```

### 3) 각 카테고리 이름으로 최소, 최대 가격 조회 API

#### 1) URL
```http
GET /searcher/search/high-low-price
```

#### 2) Request
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `category` | `string` | 카테고리 |


#### 3) Response
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `timestamp` | `string` | 시간(yyyy-MM-dd HH:mm:ss) |
| `status` | `int` | 상태 코드 |
| `message` | `string` | 내용 |
| `result` | `Object` | 결과 |
|  | `lowestProduct`  | 최저가 상품|
|  | `highestProduct` | 최고가 상품|
|  | `seq` (`int`) | 상품번호 |
|  | `category` (`string`) | 카테고리 |
|  | `brand` (`string`) | 브랜드 |
|  | `price` (`int`)| 가격 |


```javascript
{
    "timestamp": "2022-07-17 00:38:58",
    "status": 200,
    "result": {
        "lowestProduct": {
            "seq": 4,
            "category": "스니커즈",
            "brand": "A",
            "price": 9000
        },
        "highestProduct": {
            "seq": 36,
            "category": "스니커즈",
            "brand": "E",
            "price": 9900
        }
    },
    "message": "SUCCESS"
}
```

### 4) 상품등록 API

#### 1) URL
```http
POST /product/add
```

#### 2) Request
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `category` | `string` | 카테고리 |
| `brand` | `string` | 브랜드 |
| `price` | `int` | 가격 |


#### 3) Response
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `timestamp` | `string` | 시간(yyyy-MM-dd HH:mm:ss) |
| `status` | `int` | 상태 코드 |
| `message` | `string` | 내용 |
| `result` | `Object` | 결과 |


```javascript
{
    "timestamp": "2022-07-17 00:49:32",
    "status": 200,
    "result": "SUCCESS",
    "message": null
}
```

### 5) 상품수정 API

#### 1) URL
```http
PUT /product/update
```

#### 2) Request
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `category` | `string` | 카테고리 |
| `brand` | `string` | 브랜드 |
| `price` | `int` | 가격 |


#### 3) Response
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `timestamp` | `string` | 시간(yyyy-MM-dd HH:mm:ss) |
| `status` | `int` | 상태 코드 |
| `message` | `string` | 내용 |
| `result` | `Object` | 결과 |


```javascript
{
    "timestamp": "2022-07-17 00:49:32",
    "status": 200,
    "result": "SUCCESS",
    "message": null
}
```

### 6) 상품삭제 API

#### 1) URL
```http
POST /product/remove
```

#### 2) Request
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `seq` | `int` | 상품번호 |



#### 3) Response
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `timestamp` | `string` | 시간(yyyy-MM-dd HH:mm:ss) |
| `status` | `int` | 상태 코드 |
| `message` | `string` | 내용 |
| `result` | `Object` | 결과 |


```javascript
{
    "timestamp": "2022-07-17 00:51:20",
    "status": 200,
    "result": "SUCCESS",
    "message": null
}
```

## 개선사항
1. 상품 등록, 수정, 삭제 url 변경 및 예외처리 필요
2. 2번의 최저가 브랜드와 가격을 구하는 로직에서, 쿼리에서 하나의 객체를 가져올수있도록 개선 필요
3. 문자열 상수들도 따로 관리할 수 있도록 수정 필요,
4. 예외 처리 메세지 관리 필요
