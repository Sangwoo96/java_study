# 12주차 - Annotation
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

# ✔ 목표
자바의 어노테이션에 대해 학습하세요.

<br/>

# ✔ 목차
* 어노테이션 정의하는 방법
* @Target, @Retention, @Documented
* 런타임 시 어노테이션 정보 사용하기
* 어노테이션 프로세서

<br/>

# 💡 12-1 어노테이션 정의하는 방법

## **어노테이션이란?**

* 어노테이션은 주석이라는 뜻을 가지고 있다.
* 어노테이션(Annotation)은 메타데이터(metadata)라고 볼 수 있다.
* 메타데이터란 컴파일 과정과 실행 과정에서 코드를 어떻게 컴파일하고 처리할 것인지를 알려주는 정보이다.

<br/>

<br/>

## **어노테이션의 용도**

* 컴파일러에게 코드 문법 에러를 체크하도록 정보를 제공한다.

  * 대표적인 예로는 `@Override` 어노테이션이 있다.
  * 메소드가 재정의된 것임을 컴파일러에게 알려주어 컴파일러가 오버라이드 검사를 하도록 해준다. 

* 소프트웨어 개발 툴이 빌드나 배치 시 코드를 자동으로 생성할 수 있도록 정보를 제공한다.

* 실행 시(런타임 시) 특정 기능을 실행하도록 정보를 제공한다.

<br/>

<br/>

## **어노테이션 타입 정의**

```java
public @interface AnnotationName { ... }
```

* 인터페이스를 정의하는 것과 유사하다.

* `@interface` 를 사용해서 어노테이션을 정의하며, 그 뒤에 사용할 어노테이션 이름이 온다.

<br/>

<br/>

  ```java
  @AnnotationName
  ```


* 정의한 어노테이션 코드는 위와 같이 사용한다.

<br/>

<br/>

## **어노테이션 타입 적용**

```java
public @interface AnnotationName {
  타입 elementName() { default 값};
}
```

* 어노테이션은 엘리먼트(element)를 멤버로 가질 수 있다. 각 엘리먼트는 타입과 이름으로 구성되며, 디폴트 값을 가질 수 있다.
* 엘리먼트 타입으로는 int, double과 같은 기본 데이터 타입이나 String, 열거 타입, Class 타입, 그리고 이들의 배열 타입을 사용할 수 있다.
* 엘리먼트의 이름 뒤에는 메소드를 작성하는 것처럼 ()를 붙여야 한다. 

<br/>

<br/>

```java
public @interface AnnotationName {
    String elementName1();
    int elementName2() default 5;
  }
```

* 이렇게 정의한 어노테이션은 코드에서 적용할 때에는 다음과 같이 기술한다.

```java
@Annotation(elementName1 = "값", elementName2=3)
```

또는

```java
@Annotation(elementName1 = "값")
```

<br/>

<br/>

## **어노테이션 타입 적용(value)**

```java
public @interface AnnotationName {
    String value(); //기본 엘리먼트 선언
    int elementName() default 5;
  }
```

* 어노테이션은 기본 엘리먼트인 value를 가질 수 있다.

* 기본 엘리먼트인 value가 있으면 다음과 같의 적용할 수 있다.

```java
@Annotation("값")
```
또는
```java
@Annotation(value = "값", elementName=3)
```

<br/>

<br/>

<br/>

# 💡 12-2 @Target, @Retention, @Documented

## **빌트인 어노테이션**

Java에 내장되어 있는 어노테이션으로 컴파일러를 위한 어노테이션을 말한다.

* ### **@Override**
  
  * 현재 메서드가 슈퍼 클래스의 메서드를 오버라이드한 것임을 컴파일러에게 명시해준다.
  * 메서드가 슈퍼클래스에 없다면 에러를 발생시기 때문에 오타와 같은 실수도 잡을 수 있다.

* ### **@Deprecated**
  * 마커 어노테이션으로 다음 버전에 지원되지 않을 수도 있기 때문에 앞으로 사용하지 말라고 경고를 알린다

* ### **@SuppressWarning**
  * 컴파일 경고를 무시하도록 할 때 사용한다.
  
* ### **@SafeVarargs**
  * Java 7이상에서 사용가능하고 제네릭같은 가변인자 매개변수 사용시 경고를 무시한다
* ### **@FunctionalInterface**
  * Java 8이상에서 사용가능하고 컴파일러에게 함수형 인터페이스라는 것을 알리는 어노테이션이다.

> ### **참고**
> **함수형 인터페이스란?**  
> 1개의 추상 메서드만을 갖고 있는 인터페이스로 10주차에 배운 Runnable이 그 예이다.

<br/>

<br/>

## **메타 어노테이션**

어노테이션에서 사용되는 어노테이션으로 어노테이션을 정의(설명)하기 위해 사용된다.

* ### **@Target**

  * 어노테이션이 적용될 대상을 지정할 때 사용된다.
  * 다음과 같이 사용할 수 있다.
  ```java
  @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
  public @interface AnnotationName {
  }
  ```
  ```java
  @AnnotationName
  public class AnnotationDemo {
      @AnnotationName
      private int field;

      //생성자에 어노테이션 사용시 컴파일 에러
      @AnnotationName
      public AnnotationDemo(){

      }
      @AnnotationName
      public void method() {}
  }
  ```
  * 기존
    * **TYPE** : Class, Interface(어노테이션 타입 포함), enum, jdk14에 생긴 record

    * **FIELD** : 필드 값(프로퍼티), enum 상수값

    * **METHOD** : 메서드

    * **PARAMETER** : 메서드 파라미터 (매개 변수)

    * **CONSTRUCTOR** : 생성자

    * **LOCAL_VARIABLE** : 지역 변수

    * **ANNOTATION_TYPE **: 어노테이션

    * **PACKAGE** : 자바 패키지 

  * JDK 1.8 이후 추가
    * **TYPE_PARAMETER** : 타입 매개 변수

    * **TYPE_USE** : 타입 사용 //jdk 9 이후

    * **MODULE** : 모듈
  * JDK 14 이후 추가
    
    * **RECORD_COMPONENT** : Record 컴포넌트

<br/>

* ### **@Retention**

  * 어노테이션이 유지되는 기간(Life Time)을 설정하는 어노테이션이다.

  * **SOURCE** : 소스상에서만 어노테이션 정보를 유지한다. 바이트 코드 파일에는 정보가 남지 않는다.
  * **CLASS** : 바이트 코드 파일까지 어노테이션 정보를 유지한다. 하지만 리플렉션을 이용해서 어노테이션 정보를 얻을 수는 없다.
  * **RUNTIME** : 바이트 코드 파일까지 어노테이션 정보를 유지하면서 리플렉션을 이용해서 런타임 시에 어노테이션 정보를 얻을 수 있다.
  * 다음과 같이 사용할 수 있다.
  ```java
  @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface AnnotationName {
  }
  ```
  * 우리가 작성하는 어노테이션은 대부분 런타임 시점에 사용하기 위한 용도로 만들어진다.

<br/>

> ### **참고**
> **리플렉션(Reflection)이란?**  
> 런타임 시에 클래스의 메타 정보를 얻는 기능을 말한다. 예를 들어 클래스가 가지고 있는 필드가 무엇인지, 어떤 생성자를 갖고 있는지, 어떤 메소드를 가지고 있는지, 적용된 어노테이션이 무엇인지 알아내는 것이 리플렉션이다.

<br/>

* ### **@Documented**
  * 어노테이션의 정보가 javadoc의 문서에 포함되도록 하는 어노테이션

* ### **@Inherited**
  * 자식 클래스에게도 어노테이션이 상속되도록 하는 어노테이션

* ### **@Repeatable**
  * 어노테이션을 반복적으로 선언할 수 있게 하는 어노테이션
<br/>

<br/>

<br/>

# 💡 12-3 런타임 시 어노테이션 정보 사용하기

어노테이션 자체는 아무런 동작을 가지지 않는 단지 표식일 뿐이지만, 리플렉션을 이용해서 어노테이션의 적용 여부와 엘리먼트 값을 읽고 적절히 처리할 수 있다.

클래스에 적용된 어노테이션 정보를 얻으려면 `java.lang.Class` 를 이용하면 되지만, 필드, 생성자, 메소드에 적용된 어노테이션 정보를 얻으려면 Class 의 다음 메소드를 통해서 java.lang.reflect 패키지의 Field, Constructor, Method 타입의 배열을 얻어야 한다.

* ### **getFields()**
* ### **getConstructors()**
* ### **getDeclaredMethods()**

그런 다음 위의 메소드가 가지고 있는 다음 메소드를 호출해서 적용된 어노테이션 정보를 얻을 수 있다.

* ### **isAnnotationPresent()**
  * 지정한 어노테이션이 적용되었는지 여부, Class에서 호출했을 때 상위 클래스에 적용된 경우도 true를 리턴한다.
  
* ### **getAnnotation()**
  * 지정한 어노테이션이 적용되어 있으면 어노테이션을 리턴하고 그렇지 않다면 null을 리턴한다.
  * Class에서 호출했을 때 상위 클래서에 적용된 경우도 포함한다.

* ### **getAnnotations()**
  * 적용된 모든 어노테이션을 리턴한다.
  * Class에서 호출했을 때 상위 클래스에 적용된 어노테이션도 모두 포함한다.

* ### **getDeclaredAnnotations()**
  * 직접 적용된 모든 어노테이션을 리턴한다.
  * Class에서 호출했을 때 상위 클래스에 적용된 어노테이션은 포함되지 않는다.

<br/>

<br/>

## **예제 코드**

### **어노테이션 정의**
```java
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationName {
    String value();
}
```

### **어노테이션 적용**
```java
public class AnnotationDemo {
    @AnnotationName("*")
    public static void method1() {
        System.out.println("method1");
    }

    @AnnotationName("**")
    public static void method2() {
        System.out.println("method2");
    }

    @AnnotationName("***")
    public static void method3() {
        System.out.println("method3");
    }
}
```

### **어노테이션이 적용된 메소드 정보 출력**
```java
public class Main {
    public static void main(String[] args) {

        Method[] methods = AnnotationDemo.class.getDeclaredMethods();

        for (Method method : methods) {
            if(method.isAnnotationPresent(AnnotationName.class)){
                System.out.println("[" + method.getName() + "]");

                AnnotationName annotationName = method.getAnnotation(AnnotationName.class);
                System.out.println("value : "  + annotationName.value());
            }

            System.out.println();
        }
    }
}
```
<br/>

<br/>

<br/>

# 💡 12-4 어노테이션 프로세서

## **어노테이션 프로세서란?**

* 어노테이션 프로세서란 컴파일 타임에 특정한 어노테이션이 붙어있는 코드를 참조해서 또다른 코드를 만들어내는 역할을 한다. 

* 특정 어노테이션을 프로세싱하기 위해 어노테이션 프로세서를 등록할 수 있다. 

* 어노테이션 프로세서의 대표적인 예로는 롬복(lombok) 라이브러리가 있다.

<br/>

<br/>

## **롬복(lombok)**

어노테이션 프로세서의 대표적인 예이다.

### **소스코드**
```java
@Getter
@Setter
public class Person {
    private int age;
    private String name;
    private String address;
    
}
```

### **클래스 파일**

```java
public class Person {

    private int age;
    private String name;
    private String address;

    public Person(){

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

* 클래스 파일을 열어보면 만들지 않은 메소드가 추가된 것을 확인할 수 있다.
* 이것은 컴파일 할때 생성된다.

<br/>

<br/>

<br/>

# 참고
* 이것이 자바다 - 신용권
* https://b-programmer.tistory.com/264
* https://www.notion.so/12-95595cad188b45058bfb1ddcf97869c5