# 8주차 - 인터페이스
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## ✔ 목표
자바의 인터페이스에 대해 학습하세요.

<br/>

## ✔ 목차
* 인터페이스 정의하는 방법
* 인터페이스 구현하는 방법
* 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
* 인터페이스 상속
* 인터페이스의 기본 메소드 (Default Method), 자바 8
* 인터페이스의 static 메소드, 자바 8
* 인터페이스의 private 메소드, 자바 9

<br/>

## 💡 8-1 인터페이스 정의하는 방법
    
### 📌 **인터페이스란?**

자바는 클래스를 하나만 상속이 가능한 특성이 있는데 이는 객체지향 프로그래밍에서 큰 제약이기 때문에 인터페이스(Interface)라는 개념을 도입하였다.

자바에서의 인터페이스(Interface)는 객체의 **사용 방법**을 정의한 타입이다.

인터페이스(Interface)는 객체의 교환성을 높여주기 때문에 다형성을 구현하는 매우 중요한 역활을 한다. 특히 자바 8에서 인터페이스의 중요성은 더욱 커졌다. 자바 8의 람다식은 함수적 인터페이스의 구현 객체를 생성하기 때문이다.

인터페이스(Interface)는 개발 코드와 객체가 서로 통신하는 접점 역활을 한다. 개발 코드가 인터페이스의 메소드를 호출하면 인터페이스는 객체의 메소드를 호출시킨다.

**그럼 개발 코드가 직접 객체의 메소드를 호출하면 간단한데, 왜 중간에 인터페이스를 두었을까?**

그 이유는 개발 코드를 수정하지 않고, 사용하는 객체를 변경할 수 있도록 하기 위해서이다. 인터페이스는 하나의 객체가 아니라 여러 객체들과 사용이 가능하므로 어떤 객체를 사용하느냐에 따라서 실행 내용과 리턴값이 다를 수 있다. 따라서 실행 내용과 리턴값을 **다양화**할 수 있다는 장점이 있다.

인터페이스는 `~.java` 형태의 소스 파일로 작성되고 컴파일러(`javac.exe`)를 통해 `~.class` 형태로 컴파일되기 때문에 물리적 형태는 클래스와 동일하다. 차이점은 소스를 작성할 때 선언하는 방법이 다르다.

<br/>

### 📌 **인터페이스의 정의**

```java
public interface 인터페이스명 {
    //상수
    타입 상수명 = 값;

    //추상 메소드
    타입 메소드명(매개변수, ...);

    //디폴트 메소드
    default 타입 메소드명(매개변수, ...) { ... }

    //정적 메소드
    static 타입 메소드명(매개변수) { ... } 
 }
```

* 상수 필드 : 인터페이스는 객체 사용 설명서이므로 런타입 시 데이터를 저장할 수 있는 필드를 선언할 수 없다. 그러나 상수 필드는 선언이 가능하다. 상수는 인터페이스에 고정된 값으로 런타임 시에 데이터를 바꿀 수 없고 선언할 때에는 반드시 초기값을 대입해야 한다.
    * 인터페이스에 선언된 필드는 모두 `public static final` 의 특성를 갖으며 생략할 수 있다.
    * 상수명은 대문자로 작성하되, 서로 다른 단어로 구성되어 있을 경우 언더바(_)로 연결한다.
    * MAX_VALUE, MIN_VALUE

* 추상 메소드 : 추상 메소드는 객체가 가지고 있는 메소드를 설명한 것으로 호출할 때 어떤 매개값이 필요하고, 리턴 타입이 무엇인지 알려준다. 실제 실행부는 객체(구현 객체)가 가지고 있다.
    * 인터페이스에 선언된 추상 메소드는 모두 `public abstract` 의 특성을 갖으며 생략할 수 있다.

**디폴트 메소드와 정적 메소드는 아래에서 좀 더 자세하게 다루겠다.**

<br/>

## 💡 8-2 인터페이스를 구현하는 방법

```java
public interface RemoteControl {
    void turnOn();
    void turnOff();
}
```

클래스 선언부에 `implements` 키워드를 추가하고 인터페이스명을 명시해야 한다.

```java
public class Television implements RemoteControl{
    @Override
    public void turnOn() {
        System.out.println("티비를 켭니다");
    }

    @Override
    public void turnOff() {
        System.out.println("티비를 끕니다");
    }
}
```

<br/>

## 💡 8-3 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법

위의 코드에서 `RemoteControl` 인터페이스를 구현한 `Audio` 클래스를 추가로 만든다.

```java
public class Audio implements RemoteControl{
    @Override
    public void turnOn() {
        System.out.println("오디오를 켭니다");
    }

    @Override
    public void turnOff() {
        System.out.println("오디오를 끕니다");
    }
}
```

`Audio`, `Television` 클래스를 인스턴스화 하여 메소드를 호출한다.

두 클래스는 `RemoteControl` 타입으로도 생성이 가능하다.

```java
public class Main {
    public static void main(String[] args){
        Television tv = new Television();
        //RemoteControl tv = new Television();

        Audio audio = new Audio();
        //RemoteControl audio = new Audio();

        tv.turnOn();
        tv.turnOff();
        audio.turnOn();
        audio.turnOff();
    }
}
```

결과는 다음과 같다.

```
티비를 켭니다
티비를 끕니다
오디오를 켭니다
오디오를 끕니다
```

<br/>

## 💡 [추가] 익명 구현 객체

자바는 UI 프로그래밍에서 이벤트를 처리하기 위해, 그리고 임시 작업 스레드를 만들기 위해 익명 구현 객체를 많이 활용한다.

자바 8에서 지원하는 람다식은 인터페이스의 익명 구현 객체를 만들기 때문에 코드 패턴을 잘 익혀야한다.

```java
RemoteControl rc = new RemoteControl(){
    @Override
    public void turnOn() {
        //실행문
    }

    @Override
    public void turnOff() {
        //실행문
    }
};
```

<br/>

## 💡 8-4 인터페이스 상속

인터페이스도 다른 인터페이스를 상속할 수 있다. 인터페이스는 클래스와 달리 **다중 상속**을 허용한다.

**하위 인터페이스를 구현하는 클래스는 하위 인터페이스의 메소드 뿐만아니라 상위 인터페이스의 모든 추상 메소드에 대한 실제 메소드를 가지고 있어야 한다.**

그렇기 때문에 구현 클래스로부터 객체를 생성하고 하위 및 상위 인터페이스 타입으로 변환이 가능하다.

**하위 인터페이스로 타입이 변환되면 상 하위 인터페이스에 선언된 모든 메소드를 사용할 수 있으나, 상위 인터페이스로 타입 변환되면 하위 인터페이스에 선언된 메소드는 사용할 수 없다.**

아래의 코드는 위에서 말한 인터페이스 상속의 특징들을 보여준다.

```java
public interface InterfaceA {
    void methodA();
}
```

```java
public interface InterfaceB extends InterfaceA{
    void methodB();
}
```

```java
public class Class implements InterfaceB{

    //상 하위 인터페이스의 추상 메소드들을 모두 구현
    @Override
    public void methodB() {

    }

    @Override
    public void methodA() {

    }
}
```

```java
//상위 인터페이스 -> methodA() 만 사용 가능
InterfaceA a = new Class();
//하위 인터페이스 -> methodA(), methodB() 사용 가능
InterfaceB b = new Class();
```

<br/>

## 💡 8-5 인터페이스의 기본 메소드(Default Method), 자바 8

### 📌 **기본 메소드(디폴트 메소드)란?**

**디폴트 메소드**는 자바 8에서 추가된 인터페이스의 새로운 멤버이다. 형태는 클래스의 인스턴스 메소드와 동일한데, `default` 키워드가 리턴 타입 앞에 붙는다. 디폴트 메소드는 `public` 특성을 갖으며 생략이 가능하다.

기존 `RemoteControl` 인터페이스에 디폴트 메소드를 추가하였다.

```java
public interface RemoteControl {
    void turnOn();
    void turnOff();
    default void defaultMethod(){
        System.out.println("디폴트 메소드입니다");
    }
}
```

<br/>

### 📌 **기본 메소드(디폴트 메소드)는 왜 사용할까?**

>...(중략)... 바로 "하위 호환성"때문이다. 예를 들어 설명하자면, 여러분들이 만약 오픈 소스코드를 만들었다고 가정하자. 그 오픈소스가 엄청 유명해져서 전 세계 사람들이 다 사용하고 있는데, 인터페이스에 새로운 메소드를 만들어야 하는 상황이 발생했다. 자칫 잘못하면 내가 만든 오픈소스를 사용한 사람들은 전부 오류가 발생하고 수정을 해야 하는 일이 발생할 수도 있다. 이럴 때 사용하는 것이 바로 default 메소드다. (자바의 신 2권)

인터페이스를 보완하는 과정에서 추가적으로 구현해야 할, 혹은 필수적으로 존재해야 할 메소드가 있다면, 이미 이 인터페이스를 구현한 클래스와의 호환성이 떨어지게 된다. 

이러한 경우 default 메소드를 추가하여 하위 호환성은 유지되고 인터페이스의 보완을 지킬 수 있다.

새로 작성된 디폴트 메소드는 implements 한 클래스에서 재정의가 가능하다.

<br/>

## 💡 8-5 인터페이스의 static 메소드, 자바 8

자바 8에서 추가되었다.

디폴트 메소드와는 달리 객체가 없어도 인터페이스만으로 호출이 가능하다.

상속이 불가능하다.

```java
static 타입 메소드명(매개변수) { ... }
```

<br/>

## 💡 8-6 인터페이스의 priavte 메소드, 자바 8

자바 9에서 추가되었다.

>자바 8의 `default method` 와 `static method` 는 여전히 불편하게 만든다.  
>단지 특정 기능을 처리하는 내부 method일 뿐인데도, 외부에 공개되는 `public method` 로 만들어야하기 때문이다.  
>interface를 구현하는 다른 interface 혹은 class가 해당 method에 엑세스 하거나 상속할 수 있는 것을 원하지 않아도, 그렇게 될 수 있는 것이다.  
>자바 9에서는 위와 같은 사항으로 인해 `private method` 와 `private static method` 라는 새로운 기능을 제공해준다.  
>→코드의 중복을 피하고 interface에 대한 캡슐화를 유지 할 수 있게 되었다.

```java
public interface RemoteControl {
    void turnOn();
    void turnOff();
    default void defaultMethod(){
        System.out.println("defaultMethod");
    }
    private void privateMethod() {
        System.out.println("privateMethod");
    }

    private static void privateStaticMethod() {
        System.out.println("privateStaticMethod");
    }
}
```

<br/>

# 참고

* 이것이 자바다 - 신용권
* https://dev-coco.tistory.com/13
* https://blog.baesangwoo.dev/posts/java-livestudy-8week/