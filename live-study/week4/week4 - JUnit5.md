# 4주차 - JUnit 5
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.  
**[JUnit 5 공식 문서](https://junit.org/junit5/docs/current/user-guide/#overview)를 참고하였습니다.**

<br/>

## 목표
JUnit 5가 무엇인지, 어떻게 사용하는 것인지 알아보고 테스트에 익숙해져 보자.

<br/>

## 4-1 JUnit 5란?
JUnit 5는 크게 세 가지의 서브 프로젝트의 여러개의 모듈로 이루어져있다.
```
JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
```
* Platform
  * JVM에서 테스트를 하기 위한 기초적인 역할을 담당한다.
    * 테스트를 하기 위한 TestEngine API 제공 
    * JUnit 4(하위 버전) 기반 테스트 제공
* Jupiter
  * JUnit 5에서 테스트 및 확장하기 위한 프로그래밍 모델과 확장 모델의 조합이다.
  * Platform에서 사용하는 TestEngine은 Jupiter를 통해 제공하는 것이다
* Vintage
  * 하위 버전들(JUnit 3/4) 기반의 테스트를 실행시기키 위해 해당 TestEngine을 제공한다.
* **정리**
  * 문서 설명을 보면 Platform이 가장 상단에서 개발자들에게 JUnit의 전반적인 테스트 API들을 제공하며, 이를 실제적으로 구현한 Jupiter와 Vintage가 존재한다라고 할 수 있을것 같다.

<br/>

## 4-2 Annotations
**JUnit Jupiter는 테스트와 프레임워크의 확성을을 위해 다음과 같은 어노테이션을 지원합니다.**

| Annotation | Description|
| -- | -- |
|@Test|테스트 메소드임을 알림<br/> Junit 4와는 다르게 속성을 정의 X<br/> 이는 Jupiter에선 이미 해당 어노테이션들이 존재하기 때문
@ParameterizedTest|여러가지 매개변수를 통해 다양한 테스트 진행
@RepeatedTest|반복 횟수만큼 테스트를 진행
@TestFactory|동적 테스트를 위한 테스트 팩토리
@TestTemplate|일반 테스트가 아닌 테스트 케이스의 템플릿
@TestMethodOrder|테스트 메서드의 실행 순서를 구성하는데 사용(Junit 4의 @FixMethodOrder와 유사)
@TestInstance|테스트 인스턴스 생명주기를 구성하는데 사용
@DisplayName|테스트 클래스 혹은 메소드에 대한 이름을 선언
@DisplayNameGeneration|테스트 클래스에 대한 Display name generator를 선언
@BeforeEach|현재 클래스에서 @Test, @RepeatedTest, @ParameterizedTest, @TestFactory가 적힌 각각의 메소드들 보다 먼저 실행 (JUnit 4의 @Before와 동일)
@AfterEach|현재 클래스에서 @Test, @RepeatedTest, @ParameterizedTest, @TestFactory가 적힌 각각의 메소드들 보다 나중에 실행(JUnit 4의 @After와 동일)
@BeforeAll|현재 클래스에서 @Test, @RepeatedTest, @ParameterizedTest, @TestFactory가 적힌 모든 메소드들 보다 먼저 실행(JUnit 4의 @BeforeClass와 동일)
@AfterAll|현재 클래스에서 @Test, @RepeatedTest, @ParameterizedTest, @TestFactory가 적힌 모든 메소드들 보다 나중에 실행(JUnit 4의 @AfterClass와 동일)
@Nested|중첩된 테스트 클래스임을 알림 <br/>각 클래스의 테스트 인스턴스 생명주기를 사용하지 않는 한 @BeforeAll과 @AfterAll 메소드는 사용 X
@Tag|테스트 필더링을 위한 태그를 선언하는데 사용
@Disabled|테스트 클래스 혹은 메소드를 비활성하는데 사용(JUnit 4의 @Ignore와 유사)
@Timeout|주어진 시간을 초과할 경우, 테스트 실패를 나타내기 위해 사용
@ExtendWit|확장을 선언적으로 등록하는데 사용
@RegisterExtension|필드를 통해 프로그래밍 방식으로 확장을 등록하는데 사용
@TempDir|필드 주입 또는 매개변수 주입을 통해 임시 디렉토리를 제공하는데 사용

<br/>

## 4-3 Assertions
**Assertion을 프로그래밍 관점에서 해석하면 표명, 가정 설정문으로 할 수 있으며, 이를 통해 자신의 로직이 정확한지 테스트 해보는 것이다. Jupiter에서는 기존 버전에 존재했던 Assertion 메소드를 포함하고, 람다와 함께 사용하기 적합한 추가적인 메소드를 제공한다. 모든 Assertion은 정적 메소드로 정의되어 있다.**

```java
class AssertionsTest {
    private final Calculator calculator = new Calculator();
    //기본
    @Test
    void standardAssertions(){
        assertEquals(2, calculator.add(1,1));
        assertEquals(4, calculator.multiply(2,2), "실패 메세지1");

        assertTrue('a' < 'b', "실패 메세지2");
    }
    //그룹
    @Test
    void groupedAssertions(){
        assertAll(
                ()->assertEquals(2, calculator.add(1,1)),
                ()->assertEquals(4, calculator.multiply(2,2))
        );
    }
    //의존
    @Test
    void dependentAssertions(){
        assertAll(
                ()->{
                    assertEquals(2, calculator.add(1,1));
                },
                ()->{
                    assertEquals(4, calculator.multiply(2,2));
                }
        );
    }
    //예외
    @Test
    void exception(){
        Exception e = assertThrows(ArithmeticException.class, () -> calculator.div(1, 0));
        assertEquals("/ by zero", e.getMessage());
    }

    //제한시간
    @Test
    void timeoutNotExceeded() {
        assertTimeout(ofMinutes(2), () -> {
            // 2분 미만의 로직만 통과
        });
    }
}
```

<br/>

### **다양한 어노테이션과 테스트 코드를 작성할 때마다 내용을 추가할 예정이다.**

<br/>

# 참고
* https://junit.org/junit5/docs/current/user-guide/#overview
* https://github.com/jongnan/Java_Study_With_Whiteship/blob/master/week4/week4_0.md