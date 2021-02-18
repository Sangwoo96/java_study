# 6주차 - 상속
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## ✔ 목표
자바의 상속에 대해 학습하세요.

<br/>

## ✔ 목차
* 자바 상속의 특징
* super 키워드
* 메소드 오버라이딩
* 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
* 추상 클래스
* final 키워드
* Object 클래스

<br/>

## 💡 6-1 자바 상속의 특징
    
### 📌 **상속이란?**
객체 지향 프로그램에서는 부모 클래스의 맴버를 자식 클래스에게 물려줄 수 있다. 부모 클래스를 상위(Super) 클래스라고 부르기도 하고, 자식 클래스를 하위(Sub) 클래스, 또는 파생 클래스라고 부른다.

* 상속은 이미 잘 개발된 클래스를 재사용해서 새로운 클래스를 만들기 때문에 **코드의 중복**을 줄여준다.  
* 상속을 이용하면 **클래스의 수정을 최소화**시킬 수 있다.
    * 예를 들면 어떤 상위 클래스를 상속받은 하위 클래스가 여러개 있을때 공통적인 부분을 수정하려면 상위 클래스만 수정하면 되지만, 상속을 받지 않았을 경우에는 모든 하위 클래스 코드를 수정해야한다. 

<br/>

### 📌 **상속 구현**

자바에서 상속의 구현은 다음과 같다. 새로 작성하고자 하는 클래스 이름 뒤에 상속받고자 하는 클래스의 이름을 키워드 `extends`와 함께 작성해주면 된다. 

**Car.java**
```java
public class Car {
    public int wheel;
    public String color;

    public Car(int wheel, String color) {
        this.wheel = wheel;
        this.color = color;
    }

    public void drive(){
        System.out.println("[Car] --- drive");
    }

    public void stop() {
        System.out.println("[Car] --- stop");
    }
}
```

<br/>

**CarA.java**
```java
public class CarA extends Car{

    public CarA(int wheel, String color, String model) {
        super(wheel, color);
        this.model = model;
    }
}
```

<br/>

**CarB.java**
```java
public class CarB extends Car{

    public CarB(int wheel, String color, String model) {
        super(wheel, color);
        this.model = model;
    }
}
```

CarA 와 CarB 의 공통적으로 포함하는 부분은 wheel(바퀴), color(색)이다.  
따라서 위의 공통적인 부분을 Car으로 묶고 CarA와 CarB는 Car을 상속하고, 나머지를 구현하는 것이 코드의 중복과 수정을 줄일 수 있는 방법이다.

<br/>

### 📌 **단일 상속**
다른 언어와는 달리 자바는 **다중 상속을 허용하지 않는다**. 그러므로 다음과 같이 `extends` 뒤에는 단 하나의 부모 클래스만 와야한다.
```java
//불가능
public class 자식클래스 extends 부모클래스1, 부모클래스2 { ... }
```
```java
//가능
public class 자식클래스 extends 부모클래스1 { ... }
```

<br/>

### 📌 **최상위 클래스 Object**
자바의 모든 클래스는 최상위 클래스 Object의 서브 클래스이다.

```java
public class Main {
    public static void main(String[] args){
        School school = new School("신사초등학교", "서울");
        Object teacher = new Teacher(school, "홍길동", 30);
        Object student = new Student(school, "김철수", 9);
    }
}
```

<br/>

## 💡 6-2 super 키워드

### 📌 **super**

super는 자식 클래스에서 부모 클래스로부터 상속받은 멤버를 참조하는데 사용되는 참조 변수이다. 생성자에서 멤버변수와 매개 변수의 이름이 같을 때 `this` 를 붙여서 구별했듯이 상속받은 멤버와 자신의 클래스에 정의된 멤버의 이름이 같을 때는 `super` 를 붙여서 구별할 수 있다.  

`static` 메서드(클래스 메서드)는 인스턴스와 관련이 없기 때문에 `this` 와 마찬가지로 `super` 역시 `static` 메서드에서는 사용할 수 없고 인스턴스 메서드에서만 사용할 수 있다.

```java
class Parent {
    int x = 10;
}

class Child extends Parent {
    int x = 20;

    void method() {
        System.out.println("x = " + x);
        System.out.println("this.x = " + this.x);
        System.out.println("super.x = " + super.x);
    }
}
```
```
결과화면
x = 20
this.x = 20
super.x = 10
```

<br/>

### 📌 **super()**

자바에서 자식 객체를 생성하면, 부모 객체가 먼저 생성되고 자식 객체가 그 다음에 생성된다. 

`super()` 는 부모 클래스의 생성자를 호출하는데 사용된다.

```java
public class CarA extends Car{

    public CarB(int wheel, String color) {
        super(wheel, color);
    }
}
```

`CarA` 은 `super(wheel, color)` 을 사용하여 부모 클래스의 생성자를 호출하였다.

위와 같이 부모 클래스가 매개 변수가 있는 생성자만 있다면 반드시 자식 클래스 생성자에서는 `super(매개값, ...)` 을 호출해야한다.

부모 클래스에 기본 생성자가 존재하면 `super()` 을 작성하지 않아도 된다. 컴파일러가 자동으로 추가하기 때문이다.

만약 자식 클래스 생성자에서 매개 변수가 있는 생성자를 호출하려면 `super(매개값, ...)` 을 작성해야한다.

<br/>

## 💡 6-3 메소드 오버라이딩

### 📌 **메소드 오버라이딩이란?**

매소드 오버라이딩(@Override)은 상속된 메소드의 내용이 자식 클래스에 맞지 않을 경우, 자식 클래스에서 동일한 메소드를 재정의하는 것을 말한다.

<br/>

### 📌 **메소드 오버라이딩 조건**

오버라이딩은 메서드의 내용만을 새로 작성하는 것이므로 메서드의 선언부는 부모의 것과 완전히 일치해야 한다. 따라서 다음 조건을 만족해야한다.
  * 부모의 메소드와 동일한 시그너처(리턴 타입, 메소드 이름, 매개 변수 리스트)를 가져야 한다.
  * 접근 제한을 더 강하게 오버라이딩할 수 없다.
  * 새로운 예외를 throw할 수 없다.

여기서 반환타입의 경우 JDK1.5부터 공변 반환타입(covariant return type)이 추가되어, 반환타입을 자식 클래스의 타입으로 변경하는 것이 가능하도록 되었다.

위의 조건들을 간단히 요약하면 선언부가 서로 일치해야 한다는 것이다. 단 접근 제어자(access modifier)와 예외(exception)는 제한된 조건 하에서만 다르게 변경할 수 있다.

* **접근 제어자는 부모 클래스의 메서드보다 좁은 범위로 변경할 수 없다.**
    * 만일 부모 클래스에 정의된 메서드의 접근 제어자가 protected라면, 이를 오버라이딩하는 자식 클래스의 메서드는 접근 제어자가 protected나 public이어야 한다. 대부분의 경우 같은 범위의 접근 제어자를 사용한다.

* **부모 클래스의 메서드보다 많은 수의 예외를 선언할 수 없다.**

```java
public class CarB extends Car{

    public CarB(int wheel, String color) {
        super(wheel, color);
    }

    @Override
    public void drive() {
        System.out.println("[CarB] --- drive");
    }

    @Override
    public void stop() {
        System.out.println("[CarB] --- stop");
    }
}

```

<br/>

## 💡 6-4 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)

자바는 객체지향 프로그래밍언어로서 객체들간의 메시지 전송을 기반으로 문제를 해결해나간다.

메세지 전송이라는 표현은 결국 메서드를 호출하는 것인데, 이것을 디스패치(dispatch)라고 부른다.

 디스패치(dispatch)는 정적 디스패치(static dispatch)와 동적 디스패치(dynamic dispatch)가 있는데 

정적(static)은 구현 클래스를 이용해 컴파일 시점에서부터 어떤 메서드가 호출될 지 정해져 있는 것이고,

동적(dynamic(은 인터페이스를 이용해 참조함으로서 호출되는 메서드가 동적으로 정해지는 것을 의미한다.

<br/>

### 📌 **정적 메소드 디스패치(Static Method Dispatch)**



```java
public class Parent {
    public void method1(){
        System.out.println("Parent method1입니다");
    }
}
```

```java
public class Child extends Parent{
    @Override
    public void method1() {
        System.out.println("Child method1입니다");
    }
}
```

```java
public class Main {
    public static void main(String[] args){
        Child child = new Child();
        child.method1(); //동적 메소드 디스패치
    }
}
```

위의 코드는 `Student` 클래스의 `method1` 메소드는 부모 클래스 `School` 의 `method1`을 오버라이딩을 하였다.  

`Main` 클래스에서 `student.method1()`을 호출했을 때 `Student` 타입의 객체를 생성했기 때문에 우리는 `Student` 클래스의 오버라이딩 된 함수가 불릴 것을 알고 있다. 

**자바에서 객체 생성은 Runtime 시에 호출된다. 즉, 컴파일 시점에 알 수 있는 것은 타입에 대한 정보이다.**

따라서 컴파일러 역시 이 메소드를 호출하고 실행시켜야되는 것을 명확하게 알고 있다.

우리는 이를 정적 메소드 디스패치라 부른다.

</br>

### 📌 **다이나믹 메소드 디스패치 (Dynamic Method Dispatch)**

```java
public class Parent {
    public void method1(){
        System.out.println("Parent method1입니다");
    }
}
```

```java
public class Child extends Parent{
    @Override
    public void method1() {
        System.out.println("Child method1입니다");
    }
}
```

```java
public class Main {
    public static void main(String[] args){
        Parent parent = new Child();
        parent.method1(); //동적 메소드 디스패치
    }
}
```

자바에서는 위와 같은 `Parent parent = new Child()` 과 같은 객체의 생성과 바인딩을 허락한다.  

이 코드에서 `parent.method1()` 을 사용하면 어떤 메소드가 호출될까?  

위에서 말했던 것처럼 컴파일러는 타입만 체크한다.

따라서 `parent` 객체는 `Parent` 이라는 클래스 타입이기 때문에 `Child` 클래스를 할당할지라도 `Child` 클래스의 `method1`에 접근할 수가 없다. `Parent` 객체이기 때문이다.

하지만 결과는 `Child` 클래스의 `method1()` 이 호출된다.

그 이유는 컴파일러가 어떤 메소드를 호출해야되는지 모르지만 런타임에 정해져서 메서드를 호출하기 때문이다. 

이를 동적 메소드 디스패치라고 부른다.

<br/>

## 💡 6-5 추상 클래스

### 📌 **추상 클래스란?**

```java
abstract class 클래스이름{
    ...
}
```
추상 클래스는 클래스를 만들기 위한 일종의 설계도로 인스턴스를 생성할 수 없는 클래스이다. 이를 사용하기 위해서는 반드시 자식 클래스에서 상속을 받아 클래스를 모두 구현해야만 한다.

추상 클래스는 다음과 같은 특징을 갖고 있다.

* 자체 인스턴스 생성 불가능
* **생성자와 멤버변수, 일반 메서드** 모두를 가질 수 있다.
* 하나 이상의 **추상 메서드**를 포함한다.

<br/>

### 📌 **추상 메서드란?**

```java
abstract 리턴타입 메서드이름();
```

추상클래스는 메서드의 선언부만 작성하고 구현부는 미완성인 채로 남겨두는 메소드를 말한다. 추상클래스는 보통 주석을 통해 어떤 기능을 수행하는 지 알려주고, 구현부는 각각 상속받는 자식클래스마다 다르게 구현된다.

* 메서드의 선언부만 작성하고, 구현부는 미완성이다.
* 자식클래스는 반드시 추상메서드를 구현해야하며, 만약 구현하지 않을 경우 자식클래스도 추상클래스가 되어야 한다.
* 추상 메서드의 접근 지정자에는 private를 사용할 수 없다.

<br/>

## 💡 6-6 final 키워드

### 📌 **final 필드**
변수 앞에 `final` 이 붙으면, 값을 변경할 수 없는 상수가 된다.

`final` 이 붙은 변수는 상수이므로 일반적으로 선언과 동시에 초기화를 동시에 하지만, 인스턴스 변수의 경우 생성자에서 초기화 되도록 할 수 있다.

클래스 내에 매개변수를 갖는 생성자를 선언하여, 인스턴스를 생성할 때 `final` 이 붙은 멤버변수를 초기화하는데 필요한 값을 생성자의 매개변수로부터 제공받는 것이다. 이 기능을 활용하면 각 인스턴스마다 `final` 이 붙은 멤버변수가 다른 값을 갖도록 하는 것이 가능하다.

<br/>

### 📌 **final 클래스**
변경될 수 없는 클래스, 확장될 수 없는 클래스가 된다. 따라서 final로 지정된 클래스는 다른 클래스의 부모가 될 수 없다.

<br/>

### 📌 **final 메소드**
변경될 수 없는 메서드, final로 지정된 메서드는 오버라이딩을 통해 재정의 될 수 없다.

<br/>

### 📌 **final을 언제 사용해야힐까?**
final을 쓰던, 안쓰던 코드를 이해하고 작성하면 문제없이 코딩이 가능하다.

그러나 다른 사람들과 오해를 최소화하고 도움을 줄 수 있는지 고민하면 좋을 것 같다.

* 개발의 의도를 나타내기 위함
    * 코드 리뷰 등을 통해 명시적으로 변경, 상속, 확장을 막음으로서 실수를 최소화하고 버그를 줄일 수 있다.
* 코드의 가독성을 위함.



<br/>

## 💡 6-7 Object 클래스

`java.lang.Object` 클래스는 모든 클래스의 최상위 클래스이다.

| 메소드 | 설명 |
| --- | --- |
| boolean equals(Object obj) | 두 객체가 같은지 비교한다.
String toString() | 객체의 문자열을 반환한다.
protected Object clone() | 객체를 복사한다.
protected void finalize() | 가비지 컬렉션 직전에 객체의 리소스를 정리할때 호출한다.
Class getClass() | 객체의 클레스형을 반환한다.
int hashCode() | 객체의 코드값을 반환한다.
void notify() | wait된 스레드 실행을 재개할 때 호출한다.
void notifyAll() | wait된 모든 스레드 실행을 재개할 때 호출한다.
void wait() | 스레드를 일시적으로 중지할 때 호출한다.
void wait(long timeout) | 주어진 시간만큼 스레드를 일시적으로 중지할 때 호출한다.

<br/>

# 참고
* 이것이 자바다 - 신용권
* https://blog.naver.com/swoh1227/222181505425
* https://yadon079.github.io/2020/java%20study%20halle/week-06
* https://defacto-standard.tistory.com/413
