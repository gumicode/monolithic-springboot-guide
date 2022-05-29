# Monolithic

모놀리식 Spring boot + JPA 기반 프로젝트 패키지 구조 및 코드 켄벤션 등을 가이드 하는 문서 입니다.

## 빌드 방법

```
git clone https://github.com/gumicode/monolithic-springboot-guide
```

내려받은 git 프로젝트를 그대로 intellij IDE 를 사용하여 open 하시면 모든 모듈이 자동으로 설치 됩니다. 
만약 정상적으로 설치 되지 않을 경우 JDK 버전이 17이상인지 확인 하시면 됩니다.

Gradle 사용시

```
./gradlew build
java -jar ./build/lib/monolithic-0.0.1-SNAPSHOT.jar
```


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

### 데이터베이스

- H2
- 프로젝트 실행 이후 http://localhost:8080/h2 로 접속하면 DB 를 볼 수 있습니다.  

## Package Structure

아래 패키지 구조는 개념을 이애하는데 도움이 되기 위해서 만든 것이지 반드시 이래야 한다는 강제적인 규칙이 아닙니다.
여기서 가져가야 하는 부분은 어떤 개념을 적용했는지만 가져 가시면 됩니다.

아래 패키지 구조는 도메인 모델 패턴을 기준으로 정렬 했습니다.

```
project
    ├─ global
        ├─ configuration
        ├─ controller
        ├─ service
        ├─ repository
        ├─ entity
        ├─ dto
        ├─ error
        ├─ exception
        ├─ handler
        ├─ property
        ├─ serializer
    ├─ domain
        ├─ member
            ├─ controller
            ├─ service
            ├─ repository
            ├─ entity
            ├─ dto
            ├─ property
    ├─ infra
```

## Package Description

- 패키지명은 소문자만 사용 합니다.
- 복수형 패키지 이름을 사용하지 않습니다. controllers (X) , controller (O)
- 패키지 명에는 두개 이상의 단어를 결합하지 않습니다.
- domain 하위 패키지 구조는 global 과 동일한 이름 규칙을 따라 갑니다.
- domain 을 구분하는 범위는 서로 연관관계가 없이 쪼갤수 최소 단위로 결정 합니다.
- domain 은 다른 domain 의 범위를 절대 침범하지 않습니다.
- 대신 개발을 하다보면 다른 도메인을 필연적으로 사용할 수 밖에 없는데 이때 EventHandler 를 사용 합니다.
- 패키지 이름이나 클래스 이름은 약어를 사용하지 않고 풀네임을 사용 합니다. config (X) , configuration (O)

### global

프로젝트에서 공통적으로 사용하는 부분을 별도로 분리한 패키지 입니다.

### domain

프로젝트에서 서로 연관관계가 없이 쪼갤수 최소 단위의 도메인 입니다.  DDD 에서는 애그리거트라고 합니다.

- 도메인 안에는 여러 entity 를 가질 수 있고 연관 관계를 가질 수 있습니다. 
- 다른 도메인 끼리는 연관관계를 가질 수 없습니다. <code>@OneToMany</code> (X)
- 도메인 끼리 완전히 격리하는 이유는 1)책임을 분리하여 시스템의 복잡성을 낮추기 위함 입니다. 2)추후 마이크로서비스로 분리 할때 용이할 수 있습니다.
- DB 특성상 다른 도메인의 연관 정보가 필요할 수 있으므로 이때는 연관관례를 맺는게 아닌, 해당 도메인의 ID 만 컬럼으로 저장 합니다.

### infra

프로젝트에서 사용하는 외부 모듈을 분리해 둔 패키지 입니다.

- aws s3 파일서버에 파일 업로드가 필요 할 경우 해당 모듈을 패키지 구조에 넣습니다.
- 외부 API 호출을 필요할 때 특정 모듈을 저장할 수 있습니다.

## REST API 가이드

[REST API 가이드](https://blog.gumicode.com/docs/development-guide/REST%20API%20%EA%B0%80%EC%9D%B4%EB%93%9C.html)

## GIT 가이드

[GIT 가이드](https://blog.gumicode.com/docs/development-guide/Git%20%EA%B0%80%EC%9D%B4%EB%93%9C.html)

## Exception 전략

Exception 전략은 아래와 같이 RuntimeException 을 상속받는 최상위 BusinessException 을 사용 합니다. 

공통으로 사용되는 Exception 은 global 패키지에서 생성합니다. 특정 도메인에서만 사용할 exception 이라면 해당 도메인에서 직접 선언하여 사용 합니다.

```
RuntimeException
    ├─ BusinessException
        ├─ DomainNotFoundException
        ├─ DomainValidationException
        ...
```

- <code>BusinessException</code> : 프로젝트 내부에서 발생한 최상위 exception
- <code>DomainNotFoundException</code> : 데이터베이스로 특정 ID 로 조회하는데 데이터가 없는 경우
- <code>DomainValidationException</code> : 도메인 생성 규칙에 어긋났을 때 발생 주로, 정규식 오류

### GlobalExceptionHandler

프로젝트 내부에서 exception 발생시 핸들링 하는 클래스 입니다. 

```java
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorResponseHelper errorResponseHelper;

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDomainNotFoundException(DomainNotFoundException e) {
        log.error("DomainNotFoundException : ", e);
        if (e.getId() != null) {
            log.error("ID not found : {}", e.getId());
        }
        return errorResponseHelper.code(e.getErrorCode());
    }
}
```

예를들어 특정 ID로 DB를 조회를 시도했는데 ID 가 존재하지 않을 경우 <code>DomainNotFoundException</code> 를 발생시키고
이후 핸들러에서 해당 Exception 을 핸들링 하여 어떤 json 타입으로 client 에게 응답할 지 지정 해 줍니다.

### GlobalErrorCode

Exception 마다 어떤 오류 메세지를 전달 하는지 Enum 코드로 정의 합니다.

```java
public enum GlobalErrorCode implements ErrorCode {
    G0000(HttpStatus.INTERNAL_SERVER_ERROR)
    ,G0001(HttpStatus.BAD_REQUEST)
    ,G0002(HttpStatus.INTERNAL_SERVER_ERROR)
    ,G0003(HttpStatus.BAD_REQUEST)
    ,G0004(HttpStatus.NOT_FOUND)
    ,G0005(HttpStatus.BAD_REQUEST)
    ;
}
```

오류 코드에 정의된 메세지는 <code>src/main/resources/i18n/</code> 경로에서 확인할 수 있습니다. 

오류 메세지는 다국어 처리가 지원 되므로 한국어, 영어 2가지 언어를 지원하고 있고 필요시 추가하면 됩니다. 

properties 파일이 깨질 경우 UTF-8 설정이 되어 있는지 확인 하시길 바랍니다.

<code>src/main/resources/i18n/errors_ko_KR.properties</code>
```properties
G0000=정의되지 않은 오류
G0001=잘못된 요청값 입니다.
G0002=존재하지 않는 경로 입니다.
G0003=잘못된 값 : {0}
G0004=지원하지 않는 메소드
G0005=필수 요청 본문이 누락되었습니다.
```

<code>src/main/resources/i18n/errors_en_US.properties</code>
```properties
G0000=undefined error
G0001=Invalid request value.
G0002=The path does not exist.
G0003=invalid value : {0}
G0004=Request method not supported
G0005=Required request body is missing
```