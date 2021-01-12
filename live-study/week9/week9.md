# 9주차 - 예외 처리
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## ✔ 목표
자바의 예외 처리에 대해 학습하세요.

<br/>

## ✔ 목차
* Exception과 Error의 차이는?
* 자바가 제공하는 예외 계층 구조
* 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
* RuntimeException과 RE가 아닌 것의 차이는?
* 커스텀한 예외 만드는 방법

<br/>

## 💡 9-1 Exception과 Error의 차이는?

### 📌 **에러(Error)**

컴퓨터 하드웨어의 오동작 또는 고장으로 인해 응용프로그램 실행 오류가 발생하는 것을 자바에서는 에러(Error)라고 한다. 

에러는 JVM 실행에 문제가 생겼다는 것이므로 JVM 위에서 실행되는 프로그램을 아무리 견고하게 만들어도 결국 실행 불능이 된다.

개발자는 이런 에러에 대처할 방법이 전혀 없다.

에러(Error)의 대표적인 예는 메모리 부족(OutOfMemoryError)와 스택오버플로우(StackOverFlowError) 등이 있다.

<br/>

### 📌 **예외(Exception)**

예외란 사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인해 발생하는 프로그램 오류를 말한다.

예외는 두 가지 종류가 있다.
* **일반 예외(Exception)** : **컴파일러 체크 예외**라고도 하는데, 자바 소스를 컴파일하는 과정에서 예외 처리 코드가 필요한지 검사하기 때문이다. 만약 예외 처리 코드가 없다면 컴파일 오류가 발생한다.
* **실행 예외(RuntimeException)** : 컴파일하는 과정에서 예외 처리 코드를 검사하지 않는 예외를 말한다.

**예외**는 예외 처리(Exception Handling)을 통해 프로그램을 종료하지 않고 정상 실행 상태가 유지되도록 할 수 있다.

<br/>

## 💡 9-2 자바가 제공하는 예외 계층 구조

<br/>

![8](https://user-images.githubusercontent.com/55661631/104283814-75339400-54f4-11eb-94e1-9e561d72325a.PNG)  
출처 : https://itmining.tistory.com/9

### 📌 **Throwable**

모든 예외의 조상이 되는 Exception 클래스와 모든 오류의 조상이 되는 Error 클래스의 부모 클래스이다.

`String getMesage()`, `void printStackTrace()`, `String toString()` 메소드가 포함된다.

<br/>

### 📌 **일반 예외(Exception)**

일반 예외(Exception)와 실행 예외(RuntimeException) 클래스를 구분하는 방법은 일반 예외는 Exception을 상속받지만 RuntimeException을 상속받지 않은 클래스이고, 실행 예외는 위와 같이 RuntimeException을 상속받는 클래스이다.

RuntimeException 역시 Exception을 상속받지만, JVM은 RuntimeException을 상속했는지 여부를 보고 실행 예외를 판단한다.

<br/>

### 📌 **실행 예외(RuntimeException)**

### 1. **NullPointerException**

자바 프로그램에서 가장 빈번하게 발생하는 실행 예외는 NullPointerException일 것이다. 이것은 객체 참조가 없는 상태, 즉 null 값을 갖는 참조 변수에 접근을 할 때 발생한다.

```java
public class NullPointerExceptionTest {
     public static void main(String[] args){
         String data = null;
         System.out.println(data.toString());
     }
}
```
위 프로그램의 data 변수는 null 값을 가지고 있기 때문에 String 객체를 참조하고 있지 않다. 하지만 String 객체의 toString() 메소드를 호출하여 
NullPointerException이 발생한다.

<br/>

### 2. **ArrayIndexOutOfBoundsException**

배열에서 인덱스 범위를 초과하면 발생하는 에러이다.

```java
public class ArrayIndexOutOfBoundsExceptionTest {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        System.out.println(list.get(0));
    }
}
```

<br/>

### 3. **NumberFormatException**

`Interger`와 `Double` 클래스의 정적 메소드인 parseXXX() 메소드를 이용하면 문자열을 숫자로 변환할 수 있다. 이때 숫자로 변환될 수 없는 문자가 포함될 때 발생하는 에러이다.

```java
public class NumberFormatExceptionTest {
    public static void main(String[] args){
        String data = "a100";
        System.out.println(Integer.parseInt(data));
    }
}
```
data 변수의 a100은 숫자로 변환될 수 없기 때문에 NumberFormatException이 발생한다.

<br/>

## 💡 9-3 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

### 📌 **try-catch-finally**

try-catch문의 구조는 다음과 같다.

```java
try{
    //예외 발생 가능 코드
}catch (예외클래스 e1){
    //예외 처리
}finally{
    //항상 실행
}
```

try 블록에는 예외 발생 가능 코드가 위치한다.   
try 블록의 코드가 예외 발생 없이 정상 실행되면 catch 블록의 코드는 실행되지 않는다.  
만약 try 블록의 코드에서 예외가 발생하면 해당 예외 타입의 catch 블록을 실행한다.   
그리고 finally 블록의 코드를 실행한다. 

finally 블록은 옵션을 생략이 가능하다. 예외 발생 여부와 상관없이 항상 실행할 내용이 있을 경우에만 finally 블록을 작성해주면 된다. 

try 블록과 catch 블록에서 return문을 사용하더라도 finally 블록은 항상 실행된다.

<br/>

### 📌 **다중 catch**

```java
try{
    //예외 발생 가능 코드
}catch (예외클래스 e1){
    //예외 처리
}catch (예외클래스 e2){
    //예외 처리
}catch (예외클래스 e3){
    //예외 처리
}
```

try 블록 내부는 다양한 종류의 에러가 일어날 수 있기 때문에 위와 같이 여러 개의 catch 블록이 작성할 수 있다. 이를 **다중 catch**라고 부른다.


```java
try{
    String data = null;
    System.out.println(data.toString());
}catch (ArrayIndexOutOfBoundsException e){
    System.out.println(e.getClass().getName());
}catch (NumberFormatException e){
    System.out.println(e.getClass().getName());
}catch (NullPointerException e){
    System.out.println(e.getClass().getName());
}
```
위 프로그램은 `NullPointerException`을 발생하기 때문에 3번째 catch 블록이 실행된다.

만약 위와 다르게 try 블록안에 `NullPointerException`뿐만 아니라 다른 예외가 발생할 수 있다면 가장 처음 발생한 예외 타입의 catch 블록만 실행된다. 그 이유는 하나의 예외가 발생하면 즉시 실행을 멈추고 catch 블록으로 이동하기 때문이다.

<br/>

### 📌 **다중 catch 순서**

**다중 catch 블록을 작성할 때 주의해야할 점이 있다.** 

상위 예외 클래스가 하위 예외 클래스보다 아래쪽에 위치해야 한다는 점이다.

```java
try{
    
}catch(Exception e){
    //예외 발생시 항상 실행
}catch(NullPointerException e){
    //실행 x
}
```
상위 예외 클래스가 위에 위치해 있는 예제 코드이다.

`NullPointerException` (하위 예외 클래스)은 `Exception` (상위 예외 클래스)을 상속받기 때문에 첫 번째 catch 블록만 실행된다. 두 번째 catch 블록은 어떤 경우에라도 실행되지 않는다.

위 코드는 잘못된 코드이기 때문에 다음과 같이 변경해야한다.

```java
try{
    
}catch(NullPointerException e){
    //실행 o
}catch(Exception e){
    //NullPointerException 이외의 예외 발생시 실행
}
```

<br/>

### 📌 **멀티 catch**

자바 7부터 하나의 catch 블록에서 여러 개의 예외를 처리할 수 있도록 멀티 catch 기능을 추가했다.

다음은 멀티 catch 블록을 작성하는 방법이다. catch 괄호 () 안에 동일하게 처리하고 싶은 예외를 `|` 로 연결하면 된다.

```java
try{
    String data = null;
    System.out.println(data.toString());
}catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e){
    System.out.println(e.getClass().getName());
}
```

<br/>

### 📌 **예외 떠넘기기(throws)**

메소드 내부에서 예외가 발생할 수 있는 코드를 작성할 때 try-catch 블록으로 예외를 처리하는 것이 기본이지만, 경우에 따라서는 메소드를 호출한 곳으로 예외를 떠넘길 수도 있다.

이때 사용하는 키워드가 `throws` 이다.

```java
public class Throws {
    static void method() throws ClassNotFoundException{
        Class clazz = Class.forName("java.lang.없는클래스");
    }
}
```

```java
public class ExceptionTest {
    public static void main(String[] args){
        try{
            // Throws 클래스의 method 메소드를 호출한 위치
            Throws.method();
        }catch (ClassNotFoundException e){
            System.out.println(e.getClass().getName());
        }
    }
}
```

위 프로그램을 보면 Throws 클래스의 method 메소드는 발생할 수 있는 ClassNotFoundException을 처리하지 않고 throws 키워드를 사용해 이를 호출한 곳으로 떠넘겼다.

따라서 발생한 ClassNotFoundException는 main의 catch 블록을 실행시킨다.

**throws 키워드가 붙어있는 메소드는 반드시 try 블록 내에서 호출되어야 한다.**

<br/>

## 💡 9-4 커스텀한 예외 만드는 방법

프로그램을 개발하다 보면 자바 API에서 제공하는 예외 클래스만으로는 다양한 종류의 예외를 표현할 수 없다.

예를 들면 은행 업무를 처리하는 프로그램에서 잔고보다 더 많은 출금 요청이 들어왔을 경우 오류가 되며, 프로그램은 잔고 부족 예외를 발생시켜야한다.  
자바 API에는 이와 같은 예외가 없기 때문에 개발자가 직접 만들어야 하는데 이를 **애플리케이션 예외(Application Exception)** 또는 **사용자 정의 예외**라고 한다.

위에서 말한 예를 코드로 작성했다.

```java
public class Account {
    int balance;

    public void withdraw(int money) throws BalanceInsufficientException {
        if(money > balance){
            throw new BalanceInsufficientException("잔액이 부족합니다.");
        }
    }
}
```

Account 클래스의 withdraw(출금) 메소드 호출시 매개변수(money)가 balance(계좌 잔액)보다 클 때 BalanceInsufficientException을 발생하게 작성했다.

throws 키워드를 사용하여 예외 처리를 호출한 곳으로 떠넘겼다.


```java
public class BalanceInsufficientException extends Exception{
    BalanceInsufficientException(){};
    BalanceInsufficientException(String message){
        super(message);
    }
}
```

Exception을 상속받은 BalanceInsufficientException이라는 커스텀 예외 클래스를 작성했다.

커스텀 예외 클래스를 선언할 때에는 클래스 이름 끝에 -Exception을 붙혀주는 것이 좋다.  
2가지 생성자를 선언하여, 1개의 생성자는 기본생성자로 작성하고, 또다른 한개는 예외 오류문을 작성할 message를 작성한다.

```java
public class ExceptionTest {
    public static void main(String[] args){
        try{
            Account account = new Account();
            account.balance = 100;
            account.withdraw(1000);
        }catch (BalanceInsufficientException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
```
BalanceInsufficientException를 처리할 catch 블록을 작성했다.

위 프로그램의 결과는 다음과 같다.

```
결과화면
잔액이 부족합니다.
BalanceInsufficientException: 잔액이 부족합니다.
	at Account.withdraw(Account.java:6)
	at ExceptionTest.main(ExceptionTest.java:19)
```

<br/>

# 참고
* 이것이 자바다 - 신용권
* https://itmining.tistory.com/9