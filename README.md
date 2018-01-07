# java logging 연습용 저장소
자바는 로깅 한 번 할려면 뭔까 빡세보여서 하도 헷갈려서 연습삼아 만들어 본 저장소

## 로깅 관련 라이브러리들
Java에는 여러가지 로깅 라이브러리가 있다.  

* Apache Commons Logging
아마 제일 오래 된 로거가 아닐까...
* Log4j  
java.util.logging 보다 일찍 나온 애지만 그래도 로깅 전용 라이브러리이다 보니 java.util.logging 보다는 좋은 모양인가 보다. (그 덕에 많은 사람들이 사용 중)  
Log4j는 더이상 maintainable하지 않고, Log4j2를 사용해야하는 모양이다.  
둘의 차이는 아래 링크를 참조...  
[Log4j2 why would you use it over log4j?](https://stackoverflow.com/questions/30019585/log4j2-why-would-you-use-it-over-log4j)  
* java.util.logging  
Java4부터 나온 모양이다...  
그 전에는 어쩔 수 없이(?) Log4j를 많이 사용한 듯...
* Logback  
가장 최근에 나온 로깅용 라이브러리이며 가장 좋은 듯...?  
자세한 내용은 아래 링크를 참조해보자.  
[logback 사용해야 하는 이유](https://beyondj2ee.wordpress.com/2012/11/09/logback-%EC%82%AC%EC%9A%A9%ED%95%B4%EC%95%BC-%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0-reasons-to-prefer-logback-over-log4j/)  

하지만 각각의 로깅 라이브러리들의 사용 방법은 제각각일 수 있다.  
따라서 라이브러리를 변경했을 때 수정해야하는 곳이 여러 곳일테고... 그 부분들을 다 수정하다보면 conflict가 엄청 날 것이다...  

따라서 우리는 로깅 라이브러리의 facade(인터페이스? 추상체?)를 써야한다.  
facade의 종류는 두 가지가 있다.  

* JCL(Jakarta Commons-Logging)  
자카르타 재단에서 만들었단다.  
자세한 건 나도 잘 모르니 패스...

* Slf4j(Simple Logging Facade for Java)  
JCL의 단점을 보완해서 만들었다는 것 같다...  
Lombok이 의존성을 가지고 있기 때문에 Lombok만 설치해도 자동으로 딸려온다.  
이걸 모르고 Slf4j와 Lombok을 따로 생각하는 경우도 있다...  

따라서 나는 설레발 주도 개발자이므로 Logback과 Slf4j를 Gradle을 가지고 설정해봤다.  
spring-boot에서 확인하기 위해선 spring-boot 브랜치를 확인해보자.  

## Logback 설정
gradle을 이용하면 resources 폴더 안의 내용이 자동으로 classpath에 모이므로 클래스를 실행할 때 귀찮게 logback.xml의 path를 설정하지 않아도 돼서 좋다.  
여튼 logback.xml을 보면 대충 다 이해가 가는데 헷갈리는 부분 두 가지가 있어서 짚고 넘어간다.  

```xml
<filter class="ch.qos.logback.classic.filter.ThresholdFilter">
    <level>WARN</level>
</filter>
```
해당 레벨 이상부터 로그를 찍겠다는 내용이다.  
콘솔에 찍는 STDOUT과 날짜 별로 파일로 빼내는 RollingFileAppender의 로깅 레벨을 달리 하고 싶어서 저렇게 설정하였다.  

```xml
<root level="INFO">
    <appender-ref ref="dailyRollingFileAppender" />
    <appender-ref ref="STDOUT" />
</root>
```

root 엘리먼트 안에 넣어야 실제로 로거가 작동한다.

## 사용하기
클래스에서 아래 코드를 넣어야한다.  

```java
class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);
    
    public static void main(String[] args) {
        logger.warn("warn");
        logger.info("info");
    }
}
```

