# 7주차 - 패키지
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## ✔ 목표
자바의 패키지에 대해 학습하세요.

<br/>

## ✔ 목차
* package 키워드
* import 키워드
* 클래스패스
* CLASSPATH 환경변수
* -classpath 옵션
* 접근지시자

<br/>

## 💡 7-1 package 키워드
    
### 📌 **패키지란?**
프로젝트를 개발하다 보면 적게는 수십 개, 많게는 수백 개의 클래스를 작성해야한다. 이때 클래스를 체계적으로 관리하지 않으면 클래스 간의 관계가 복잡해져 유지 보수가 어렵게 된다.

* 자바에서는 클래스를 체계적으로 관리하기 위해 패키지(package)를 사용한다. 이는 **파일 시스템의 폴더**라고 생각하면 된다.

* 패키지는 단순히 폴더 기능만 하는 것이 아니라 클래스의 일부분이다. 클래스를 유일하게 만들어주는 색별자 역활을 한다. 즉 **클래스 이름이  동일해도 패키지가 다르면 다른 클래스로 인식한다.**

* 클래스의 전체 이름은 "패키지명 + 클래스명" 이다. 패키지가 상 하위로 구분되어 있다면 도트(.)을 사용해서 다음과 같이 표현한다
    ```
    상위패키지.하위패키지.클래스
    ```

* 패키지는 소스의 가장 첫 줄에 있어야하고, 패키지 선언은 소스 하나에 하나만 있어야한다.

* 패키지 이름과 위치한 폴더의 이름이 같아야한다.

* 패키지 이름을 `java` 로 시작하면 안된다.

* 동일 패키지에 있는 클래스를 사용한다면 패키지명은 명시하지 않아도 된다. 

* 모든 클래스에는 정의된 클래스 이름과 패키지 이름이 있다.  이 둘을 합쳐야 완전하게 한 클래스를 표현한다고 할 수 있으며 **FQCN(Fully Qualified Class Name)** 이라고 한다. 

* 아래의 예시를 보면 `dev.highright96.package1` 은 **패키지 이름**이며 `Class1` 은 **클래스 이름**이다. **FQCN**는 `dev.highright96.package1.Class1` 이 된다.

    ![5](https://user-images.githubusercontent.com/55661631/103995671-262bed00-51dc-11eb-8e06-b47bd027c07b.PNG)

<br/>

## 💡 7-2 import 키워드
    
```java
import 패키지명.클래스명 //특정 클래스
import 패키지명.* //패키지 내의 모든 클래스
```

* 다른 패키지에 속하는 클래스를 사용하려면  사용하고자 하는 패키지를 `import` 문을 선언해야한다.

* 패키지에 다수의 클래스를 `import` 하는 경우 `import 패키지명.*` 처럼 `import` 할 수 있다.

* 인텔리 제이는 Alt +Enter 을 이용해 바로 import 가능하다.

    ![5](https://user-images.githubusercontent.com/55661631/103995671-262bed00-51dc-11eb-8e06-b47bd027c07b.PNG)
    ![6](https://user-images.githubusercontent.com/55661631/103995673-275d1a00-51dc-11eb-87c9-285d9500c304.PNG)

<br/>

## 💡 7-3 클래스패스

### 📌 **클래스패스란?**
클래스패스는 JVM 혹은 Java 컴파일러가 사용하는 파라미터인데 클래스나 패키지를 찾을 때 기준이 되는 경로를 말한다.

**클래스패스를 설정하지 않으면 자바는 기본적으로 현재 디렉토리 안에서 클래스를 찾게된다.**

잠깐 자바프로그램 실행과정을 돌이켜 보면 다음과 같다.

* 프로그램이 실행되면 JVM은 OS로부터 필요한 메모리를 할당받는다.
* 자바 컴파일러(`javac`)가 자바 소스코드(`.java`)를 읽어들여 자바 바이트코드(`.class`)로 변환시킨다.
* 클래스 로더를 통해 클래스 파일들을 JVM으로 로딩한다.
* 로딩된 클래스 파일들은 Execution engine을 통해 해석된다.
* 해석된 바이트코드는 Runtime Data Areas에 배치되어 실질적인 수행이 이루어진다.
    - 이러한 실행과정 속에서 JVM은 필요에 따라 Thread Synchronization과 GC같은 관리 작업을 수행한다.

    ![7](https://user-images.githubusercontent.com/55661631/104001006-642c0f80-51e2-11eb-96b5-4076018b77cc.PNG)  
    출처 : https://asfirstalways.tistory.com/158

자바 컴파일러(`javac`)가 자바 소스코드(`.java`)를 읽어들여 자바 바이트코드(.class)로 변환시킨 후 JVM이 `.class` 에 들어있는 명령을 실행시키려면  먼저 찾아야한다. 이때 클래스패스에 지정된 경로를 사용하여 `.class` 파일을 찾게된다.

**클래스패스를 지정하기 위한 두 가지 방법이 있다.**
* CLASSPATH 환경변수 사용
* java runtime 에 -classpath 옵션 사용

<br/>

### 📌 **CLASSPATH 환경변수 사용**

컴퓨터 시스템 변수 설정을 통해 지정할 수 있다.  
JVM이 시작될 때 JVM의 클래스 로더는 이 환경 변수를 호출한다. 그래서 환경 변수에 설정되어 있는 디렉토리가 호출되면 그 디렉토리에 있는 클래스들을 먼저 JVM에 로드한다. 그러므로 CLASSPATH 환경 변수에는 필스 클래스들이 위치한 디렉토리를 등록하도록 한다.

<br/>

### 📌 **java runtime 에 -classpath 옵션 사용**

`javac <options> <souce files>`

* 컴파일러가 컴파일 하기 위해서 필요로 하는 참조할 클래스 파일들을 찾기 위해서 컴파일시 파일 경로를 지정해주는 옵션

* Hello.java파일이 `C:\Java` 디렉터리에 존재하고, 필요한 클래스 파일들이 `C:\Java\Engclasses` 에 위치한다면, `javac -classpath C:\Java\Engclasses C:\Java\Hello.java` 로 해주면 된다.

* 만약 참조할 클래스 파일들이 그 외의 다른 디렉터리, 그리고 현 디렉토리에도 존재한다면, `javac -classpath .;C:\Java\Engclasses;C;\Java\Korclasses C:\Java\Hello.java` 과 같이`;` 으로 구분해줄 수 있다. ( . 은 현 디렉토리, .. 은 현 디렉토리에서 상위 디렉토리를 의미한다.)

* 또한 classpath 대신 단축어인 cp를 사용해도 된다. `javac -cp .;C:\Java\Engclasses;C;\Java\Korclasses C:\Java\Hello.java`

<br/>

## 💡 7-4 접근지시자
접근제어자는 클래스, 메소드, 인스턴스 및 클래스 변수를 선언할 때, 사용된다. 자바에서 사용하는 접근지시자는 `public`, `protected`, `defalut`, `private`로 총 네가지 이다.

* `public` : 누구나 접근 가능하다.
* `protected` : 같은 패키지에 있거나, 상속 받는 경우 사용할 수 있다.
* `defalut` : 아무 접근제어자를 적어주지 않은 경우이며, 같은 패키지 내에서 접근 가능하다.
* `private` : 해당 클래스 내에서만 접근 가능하다.

<br/>

# 참고
* 나는 자바다 - 신용권
* https://itcoin.tistory.com/483
* https://kils-log-of-develop.tistory.com/430
* https://hyoje420.tistory.com/7