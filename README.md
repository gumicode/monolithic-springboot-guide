# Monolithic

모놀리식 Spring boot + JPA 기반 프로젝트를 만들시 일반적으로 지켜야 하는 패키지 구조 및 코드 켄벤션 등을 가이드 하는 문서 입니다.

## Index

- [Package Structure](#Package Structure)
    - [Package Description](#Package Description)

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

