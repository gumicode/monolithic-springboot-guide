# Monolithic

모놀리식 Spring boot + JPA 기반 프로젝트를 만들시 일반적으로 지켜야 하는 패키지 구조 및 코드 켄벤션 등을 가이드 하는 문서 입니다.

## 사용한 기술 스텍 & 라이브러리

### 언어

- Java 17

일반적으로 Java 8 또는 11 버전을 많이 사용할텐데 굳이 신규 프로젝트를 낮은버전의 자바를 사용할 필요는 없습니다. 
컴파일 성능과 새로운 문법이 많이 추가 되었기에 가능하다면 최신버전을 사용합니다.
현재 18버전까지 나왔으나, 17버전은 LTS 버전이 제공 되므로 17버전으로 사용 합니다.

### 프레임워크 & 라이브러리

- spring boot : 자바 기반 프레임워크
- spring JPA : 데이터베이스 ORM 라이브러리
- QueryDSL : 복잡한 Database Select Query 를 편리하게 생성해주는 라이브러리
- awssdk.s3 : 아마존 S3 (파일서버) 파일 업로드 라이브러리

### 데이터베이스

- MySQL : 메인 데이터 베이스
- H2 : 테스트 코드용 데이터 베이스

## Package Structure

```
project
    ├─ global
    ├─ domain
    ├─ infra
```

## Package Description

- global : 공통으로 사용하는 패키지
- domain : 특정 도메인에 종속된 패키지 도메인 모듈
- infra : 외부 인프라 모듈

