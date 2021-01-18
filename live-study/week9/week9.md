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

## **에러(Error)**

컴퓨터 하드웨어의 오동작 또는 고장으로 인해 응용프로그램 실행 오류가 발생하는 것을 자바에서는 에러(Error)라고 한다.   
에러는 JVM 실행에 문제가 생겼다는 것이므로 JVM 위에서 실행되는 프로그램을 아무리 견고하게 만들어도 결국 실행 불능이 된다.  
에러(Error)의 대표적인 예는 메모리 부족(OutOfMemoryError)와 스택오버플로우(StackOverFlowError) 등이 있으며 개발자는 이런 에러에 대처할 방법이 전혀 없다. 따라서 애초에 발생하지 않도록 주의해야 한다.

<br/>

## **예외(Exception)**

예외란 사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인해 발생하는 프로그램 오류를 말한다
**예외**는 예외 처리(Exception Handling)을 통해 프로그램을 종료하지 않고 정상 실행 상태가 유지되도록 할 수 있다.

<br/>

## 💡 9-2 자바가 제공하는 예외 계층 구조

<br/>

![1](https://user-images.githubusercontent.com/55661631/104916033-d7990280-59d4-11eb-88fc-3fa32bfa7887.PNG)


## **Throwable**

모든 예외의 조상이 되는 Exception 클래스와 모든 오류의 조상이 되는 Error 클래스의 부모 클래스이다.

`String getMesage()`, `void printStackTrace()`, `String toString()` 메소드가 포함된다.

<br/>

## **Checked Exception**

위 그림을 살펴보면 Exception를 상속받는 클래스들 중에서 RuntimeException을 제외하고 모두를 **Unchecked Exception**이라고 한다. 또는 **일반 예외**라고도 한다.

**Checked Exception**은 컴파일 시점에서 확인될 수 있는 예외이다. 만약 코드 내에서 Checked Exception을 발생시킨다면, 해당 예외는 반드시 try-catch로 처리하거나 throws를 사용하여 호출한 곳으로 처리는 떠넘겨야한다.

![2](https://user-images.githubusercontent.com/55661631/104917711-1e87f780-59d7-11eb-898e-c5a77b62ead9.PNG)

위 코드처럼 Unchecked Exception인 IOException을 발생시키고 아무런 처리를 하지 않으면 컴파일 단계에서 예외가 확인된다.

![4](https://user-images.githubusercontent.com/55661631/104918217-c9001a80-59d7-11eb-961d-cc82abf2b20b.PNG)

예외 처리를 해주면 컴파일이 가능하다.

<br/>

## **Unchecked Exception**

Unchecked Exception은 컴파일 단계에서 확인할 수 없는 예외이다.

![5](https://user-images.githubusercontent.com/55661631/104918631-61969a80-59d8-11eb-8d80-df468cc22379.PNG)

예외 처리를 안해도 컴파일이 가능하다.

자바 라이브러리에서 제공하는 기본 RuntimeException들은 알아두는 것이 좋다.

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

### 4. **ArithmeticException**

산술 연산에서 예외 조건이 발생했을 때 발생한다.

```java
try{
        int a = 100/0;
}catch (ArithmeticException e){
        System.out.println("예외 발생");
    }
```

<br/>

## **왜 예외를 두 타입으로 나눠놨을까?**

>예외는 메서드의 파라미터나 반환 값만큼이나 중요한 공용 인터페이스 중 하나이다.  
메서드를 호출하는 쪽은 그 메서드가 어떤 예외를 발생시킬 수 있는가에 대해 반드시 알아야 한다. 따라서 Java는 checked exception을 통해 해당 메서드가 발생시킬 수 있는 예외를  명세하도록 강제하고 있다.  
그럼 Runtime Exception은 왜 예외를 명세하지 않아도 되도록 했을까? Runtime Exception은 프로그램 코드의 문제로 발생하는 예외이다. 따라서 클라이언트 쪽(메서드를 호출하는 쪽)에서 이를 복구(or 회복)하거나 대처할 수 있을 거라고 예상하긴 어렵다. 또 Runtime Exception은 프로그램 어디서나 매우 빈번하게 발생할 수 있기 때문에 모든 Runtime Exception을 메서드에 명시하도록 강제하는 것은 프로그램의 명확성을 떨어뜨릴 수 있다.  
따라서 클라이언트가 exception을 적절히 회복할 수 있을 것이라고 예상되는 경우 checked exception으로 만들고, 그렇지 않은 경우 unchecked exception으로 만드는 것이 좋다.  
출처 : https://wisdom-and-record.tistory.com/46

<br/>

## 💡 9-3 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

## **try-catch-finally**

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

try 블록에는 예외 발생 가능 코드가 위치한다. 만약 try 블록의 코드에서 예외가 발생하면 해당 예외 타입의 catch 블록을 실행한다. try 블록의 코드가 예외 발생 없이 정상 실행되면 catch 블록의 코드는 실행되지 않는다.  

마지막으로 finally 블록의 코드를 실행한다. finally 블록은 옵션을 생략이 가능하다. 예외 발생 여부와 상관없이 항상 실행할 내용이 있을 경우에만 finally 블록을 작성해주면 된다. 

try 블록과 catch 블록에서 return문을 사용하더라도 finally 블록은 항상 실행된다.

<br/>

## **다중 catch**

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

## **멀티 catch**

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

**멀티 catch 블록을 작성할 때 주의해야할 점이 있다.** 

catch 문에 나열된 예외 클래스들이 상속(부모 자식) 관계에 있다면 아래와 같이 컴파일이 불가능하다.

![7](https://user-images.githubusercontent.com/55661631/104920377-0619dc00-59db-11eb-9369-c9d8c61f0aa0.PNG)

<br/>

## **throw**

예외를 직접 발생시켜야 할 때가 있다. 그 때는 throw 키워드를 사용하면 된다. 

발생시킬 수 있는 예외는 Exception의 최상위 클래스인 Throwable class의 하위 클래스라면 모두 가능하다.

```java
public Object pop() {
    Object obj;

    if (size == 0) {
        throw new EmptyStackException();
    }

    obj = objectAt(size - 1);
    setObjectAt(size - 1, null);
    size--;
    return obj;
}
```


<br/>

## **throws(예외 떠넘기기)**

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

## **try-with-resources**

* 자바 7에서 새로 추가되었다.
* 리소스 객체(각종 입출력 스트림, 서버 소켓, 소켓, 각종 채널)들을 자동으로 close() 해준다.
* 사용 로직을 작성할 때 객체는 **AutoCloseable 인터페이스를 구현한 객체**여야 한다.

**AutoCloseable 인터페이스**
```java
public interface AutoCloseable {
    void close() throws Exception;
}
```

**try-catch-finally 예제**
```java
FileInputStream fis = null;
try{
	fis = new FileInputStream("file.txt");\
    ...
}catch(FileNotFoundException e){
    ...
}finally{
	if(fis != null){
		try{
			fis.close();
		}catch(IOException e){
            ...
		}
	}
}
```

<br/>

**try-catch-resources 예제**
```java
//FileInputStream, FileOutputSTream은 AutoCloseable 인터페이스를 구현한 객체이다.
try(FileInputStream fis = new FileInputStream("file1.txt");
    FileOutputSTream fos = new FileOutpuStream("file2.txt")
){
	...
}catch(IOException e){
    ...
}
```

<br/>

## 💡 try-catch-resources 관련 퀴즈

**아래의 코드는 어떤 문제가 있을까?**

```java
static void copy(String src, String dest) throws IOException{
	InputStream in = null;
	OutputStream out = null;
	try{
		in = new FileInputStream(src);
		out = new FileOutputStream(dest);
		byte[] buf = new byte[1024];
		int n;
		while((n = in.read(buf)) >= 0)
			out.write(buf, 0 ,n);

	}finally{
		if (in != null) in.close();
		if (out != null) out.close();
	}
}
```

**정답**   
finally 블럭의 in.close()에서 IOException이 발생하면 out.close()는 실행이 안된다. 사용한 리소스를 닫지 못하기 때문에 문제가 발생할 수도 있다.

**그러면 어떻게 코드를 수정해야할까?**

in.close()와 out.close()를 try-catch문으로 감싸줘 예외가 발생해도 멈추지않게한다.

```java
static void copy(String src, String dest) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try{
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int n;
            while((n = in.read(buf)) >= 0)
                out.write(buf, 0 ,n);

        }finally{
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
```

하지만 이 코드도 문제가 발생할 수 있다. 

만약 in.close()에서 IOException이 아닌 RuntimeException이 발생하면 out.close()가 실행되지 않기 때문이다.

```java
static void copy(String src, String dest) throws IOException{
	InputStream in = null;
	OutputStream out = null;
	try{
		out = new FileOutputStream(dest);
		try{
			in = new FileInputStream(src);
			byte[] buf = new byte[1024];
			int n;
			while((n = in.read(buf)) >= 0)
				out.write(buf, 0 ,n);
	
		}finally{
			if(in != null){
				try{
					in.close();
				}catch(IOException e){
				}
			}
		}
	}finally{
		if(out != null){
			try{
				out.close();
			}catch(IOException e){
			}
		}
	}
}
```

in.close()와 out.close() 모두 try-catch문을 사용하여 해결할 수 있다. 하지만 가독성이 매우 떨이지는 것을 볼 수 있다.

**이를 개선할 수 있는 방법은 뭘까?**

```java
static void copy(String src, String dest) throws IOException{
	try(
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(Dest)
	){
		byte[] buf = new byte[1024];
		int n;
		while((n = in.read(buf)) >= 0)
			out.write(buf, 0 ,n);
	}
}
```

try-resources를 사용하여 in과 out 리소스가 자동으로 닫히게 할 수 있다.





<br/>

## 💡 9-4 커스텀한 예외 만드는 방법

>좋은 커스텀 예외 만드는 법을 잘 정리한 블로그가 있다. 참고하자.  
https://www.notion.so/3565a9689f714638af34125cbb8abbe8


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
* https://www.notion.so/3565a9689f714638af34125cbb8abbe8
* https://wisdom-and-record.tistory.com/46