# 2주차 - 자바 데이터 타입, 변수 그리고 배열
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다.  

## 목표
자바의 프리미티브 타입, 변수 그리고 배열을 사용하는 방법을 익히기

## 목차
* 프리미티브 타입 종류와 값의 범위 그리고 기본 값
* 프리미티브 타입과 레퍼런스 타입
* 리터럴
* 변수 선언 및 초기화하는 방법
* 변수의 스코프와 라이프타임
* 타입 변환, 캐스팅 그리고 타입 프로모션
* 1차 및 2차 배열 선언하기
* 타입 추론, var

## 2-1 프리미티브 타입 종류와 값의 범위 그리고 기본 값

### **Primitive Type** ###

| 값의 종류 | 기본 타입 | 메모리 사용 크기 | 저장되는 값의 범위 | 기본값
| :---: | :---: | :---: | :---: | :---: |
| 정수 | byte | 1 byte (8 bit) | -2<sup>7</sup> ~ (2<sup>7</sup>-1) (-128 ~ 127)| 0 |
| | char | 2 byte (16 bit) | 0 ~ 2<sup>16</sup>-1 (유니코드 : \u0000~\uFFFF,0~65535)| \u0000 |
| | short | 2 byte (16 bit) | -2<sup>15</sup> ~ (2<sup>15</sup>-1) (-32,768 ~ 32,767)| 0 |
| | int | 4 byte (32 bit) | -2<sup>31</sup> ~ (2<sup>31</sup>-1) (-2,147,483,648 ~ 2,147,483,647)| 0 |
| | long | 8 byte (64 bit) | -2<sup>63</sup> ~ (2<sup>63</sup>-1)| 0L |
| 실수 | float | 4 byte (32 bit) | (+/-)1.4E-45 ~ (+/-)3.4028235E38| 0.0f ||
| | double | 8 byte (64 bit) | (+/-)4.9E-324 ~ (+/-)1.7976931348623157E308| 0.0d |
| 논리 | boolean | 1 byte (8 bit) | true, false | false |

<br/>

* **byte 타입** 
    - byte 타입은 색상 정보 및 파일 또는 이미지 등의 이진(바이너리) 데이터를 처리할 떄 주로 사용된다.
    - 양수가 2<sup>7</sup>-1 인 이유는 0이 포함되기 때문이다.
    - 범위를 넘어서면 컴파일 에러("Type mismatch: cannot convert from int to byte")가 발생한다.
    - byte 타입은 1byte, 즉 8bit 크기를 가지므로 0과 1이 8개로 구성된 이진수로 표현이 가능하다. 
    - 최상위 비트는 정수값의 부호를 결정하므로 범위는 -128(이진수 : 10000000) ~ 127(이진수 : 01111111)이다.
    - byte 타입보다 크기가 큰 **short, int, long 타입**도 전체 바이트 수만 다를 뿐 동일한 원리로 정수를 표현한다.
    - 저장할 수 있는 값의 범위를 초과하면 **최소값부터 다시 반복 저장**되는데, 이를 쓰레기값이라고 한다.

*  **char 타입**
    - 자바는 모든 문자를 **유니코드**로 처리한다. 유니코드는 세계 각국의 문자들을 코드값으로 매핑한 국제 표준 규약이다.
    - **유니코드**는 0~65535 범위의 2byte 크기를 가진 정수값이다.
    - 0~127까지는 아스키 문자(특수기호 및 영어 알파벳)가 할당되어 있다.
    - 44032~55203까지는 한글 11172자가 할당되어 있다.
    - 유니코드에는 음수가 없기 때문에 char 타입의 변수에는 음수 값을 저장할 수 없다. 따라서 저장할 수 있는 값의 범위는 0~65535이다.
    - 문자를 char 변수에 저장할 경우 변수에 저장되는 유니코드 값은 다음과 같다.
    ```java
    char var1 = 'A'; //유니코드: 0x0041 => 2진수: 00000000 01000001
    char var2 = '가'; //유니코드: 0xAC00 => 2진수: 10101100 00000000 
    ```

* **short 타입**
    - C언어와의 호환을 위해 사용되며 비교적 자바에서는 잘 사용되지 않는 타입이다.

* **int 타입**
    - 자바에서 정수 연산을 하기 위한 기본 타입이다. byte 타입 또는 short 타입의 변수를 + 연산을 하면 int 타입으로 변환된 후 연산되고 연산의 결과 역시 int 타입이 된다.
    - 메모리가 ㅋ트게 부족하지 않다면 정수를 저장할 때에는 일반적으로 int 타입을 사용한다.

* **float, double(실수) 타입**
    - float과 double의 메모리 사용 크기는 각각 int와 long의 크기와 같지만, 저장 방식의 차이로 훨씬 더 큰 범위의 값을 저장할 수 있다.
    - float 타입 : 부호(1bit) + 지수(8bit) + 가수(23bit) = 32bit = 4byte
    - double 타입 : 부호(1bit) + 지수(11bit) + 가수(52bit) = 64bit = 8byte

<br/>

## 2-2 프리미티브 타입과 레퍼런스 타입

```java
public class Example1{
    public static void main(String[] args){
        String string = "hello";
        int number = 1;
    }
}
```

### **Primitive Type(프리미티브 타입)** ###
* **byte, short, signed/unsigned int, signed/unsigned long, float, double, boolean, char**
* not object
* 프리미티브 타입 **변수 number**과 값인 **1**은 **런타임 스택 영역**에 생성, 저장된다. 

<br/>

### **Reference Type(레퍼런스 타입)** ###
* **class, interface, enum, array, String type**
* 레퍼런스 타입의 **변수 string**과 값인 **주소값**은 **런타임 스택 영역**에 생성, 저장된다.
* 레퍼런스 타입의 값인 **주소값**이 가르키는 **실제 값**은 **가비지 컬렉션 힙 영역**에 객체가 생성된다.
* **값을 복사할 떄 주소값이 복사되므로, 조심해서 사용해야한다.**

<br/>

## 2-3 리터럴

* 소스 코드 내에서 직접 입력된 값을 **리터럴**이라고 부른다.
* 정수 리터럴
    - 10진수 : 소수점이 없는 정수 리터럴
    - 8진수 : 0으로 시작되는 리터럴
    - 16진수 : 0x 또는 0X로 시작되는 리터럴
* 실수 리터럴
    - 10진수 실수 : 소수점이 있는 리터럴
    - 10진수 지수와 가수 : 대문자 E 또는 소문자 e가 있는 리터럴
* 문자 리터럴
    - 작은 따옴표(')로 묶은 텍스트는 하나의 문자 리터럴로 간주한다.
    - 역슬래쉬(\)가 붙은 문자 리터럴은 이스케이프 문자라고한다. 특수한 용도로 사용된다.
* 논리 리터럴
    - true, false

<br/>

## 2-4 변수 선언 및 초기화하는 방법

```java
public class Example2{
    public static void main(String[] args){
        int value1; //변수 선언
        value1 = 10; //변수를 10으로 초기화

        int value2 = 20; //변수 선언 후 초기화
    }
}
```

<br/>

## 2-5 변수의 스코프와 라이프타임
### **Primitive type variable(프리미티브 타입 변수)**
* 선언된 블록 내에서만 사용이 가능하다.
* **Member variable scope(맴버 변수 범위)**
    - `public` modifier: 상속받은 모든 클래스에서 사용 가능
    - `private` modifier: 자기 클래스에서만 사용 가능
* **블록 내에서 선언된 변수는 블록이 종료될 때 런타임 스택 영역에서 소멸된다.**
<br/>

### **Reference type variable(레퍼런스 타입 변수)**
```java
public class Example3{
    public static void main(String[] args){
        Test test = new Test();
        test = null; //참조 제거
    }
}

class Test {}
```
* 참조되지 않은 객체인 test는 **Garbage Collector**에 의해 제거 대상이 된다.

<br/>

## 2-6 타입 변환, 캐스팅 그리고 타입 프로모션
**타입 변환이란 데이터 타입을 다른 데이터 타입으로 변환하는 것을 말한다.**
* 자동 타입 변환
    - 프로그램 실행 도정 자동적으로 타입 변환이 일어나는 것을 말한다.
    - **작은 크기 타입이 큰 크기 타입**에 저장될 때 발생한다.
    - **char의 범위는 0~65535이므로 음수가 저장될 수 있는 byte 타입을 char 타입으로 자동 변환시킬 수 없다. 단 강제 타입 변환은 가능하다.**
    ```java
    public class Example4{
        public static void main(String[] args){
            byte byteValue = 10;
            int intValue = byteValue; //int <-- byte
            System.out.println(intValue);

            char charValue = '가';
            intValue = charValue; //int <-- char
            System.out.println("가의 유니코드=" + intValue);

            intValue = 200;
            double doubleValue = intValue; //double <-- int
            System.out.println(doubleValue);

            /*
            byte byteValue =65;
            char charValue = byteValue; //컴파일 에러
            */
        }
    }
    ```
    ```
    결과화면
    10
    가의 유니코드=44032
    200.0
    ```

* 강제 타입 변환
    - 강제적으로 큰 데이터 타입을 작은 데이터 타입으로 쪼개어서 저장하는 것을 강제 타입 변환이라고 한다.
    - 값이 손실될 수 있다.
    ```java
    public class Example5{
        public static void main(String[] args){
            int num1 = 1234567890;
            int num2 = 1234567890;

            float num3 = num2;
            num2 = (int) num3;

            int result = num1 - num2;
            System.out.println(result);
        }
    }
    ```
    ```
    결과화면
    -4
    ```
    - 위의 실행 결과를 보면 엉뚱하게도 0이 나오지 않는다. 이유는 int 값을 손실 없이 float 타입 `부호(1비트) + 지수(8비트) + 가수(23비트)`의 값으로 변환할 수 있으려면 가수 23비트로 표현 가능한 값이어야 한다. 1234567890은 23비트로 표현할 수 없기 때문에 근사치로 변환된다. 따라서 아래와 같이 double 타입 `부호(1비트) + 지수(11비트) + 가수(52비트)`을 사용하여 변환하여야 손실 없이 복원된다.
    ```java
    public class Example5{
        public static void main(String[] args){
            int num1 = 1234567890;
            int num2 = 1234567890;

            double num3 = num2;
            num2 = (int) num3;

            int result = num1 - num2;
            System.out.println(result);
        }
    }
    ```
    ```
    결과화면
    0
    ```

<br/>

* 연산식에서의 자동 타입 변환
    - 서로 다른 타입의 피연산자가 있을 경우 투 피연산자 중 크기가 **큰 타입**으로 자동 변환된 후 연산을 수행한다.
    - 작은 타입으로 변환을 원할 경우 강제 변환을 해야한다.

<br/>

## 2-7 1차 및 2차 배열 선언하기
```java
public class Example6{
        public static void main(String[] args){
            //1차원 배열
            int[] oneDimensionArrayEx1 = {1, 2, 3, 4, 5};
            int[] oneDimensionArrayEx2;
            oneDimensionArrayEx2 = new int[10];

            //2차원 배열
            int[][] twoDimensionArrayEx1 = {{1, 2}, {3, 4}};
            int[][] twoDimensionArrayEx2;
            twoDimensionArrayEx2 = new int[10][10];
        }
    }
```
* 1차원 배열
    - oneDimensionArrayEx1 은 Runtime Stack 영역의 힙 영역 주소값을 가진다.
    - Heap 영역에 int 타입 크기의 요소 5개를 할당하여 사용된다.  
    ![2-1](https://user-images.githubusercontent.com/55661631/103211333-3aa1f700-494b-11eb-9c76-06c46277598e.PNG)  
    출처 : https://www.notion.so/2-38b5d67c7f5a48238529bb8f1617ea0d

* 2차원 배열
    - Runtime Stack 영역의 twoDimensionArrayEx1 은 2개의 요소 크기를 가진 힙 영역 주소값을 가진다.
    - 힙 영역에는 실제 값이 들어있는 요소들과 주소값이 들어있는 요소들로 존재하게된다.
    ![2-2](https://user-images.githubusercontent.com/55661631/103211690-3b875880-494c-11eb-8d60-50ac718dc5db.PNG)  
    출처 : https://www.notion.so/2-38b5d67c7f5a48238529bb8f1617ea0d


## 2-8 타입 추론, var
### **타입 추론**
* **타입 추론은 개발자가 변수의 타입을 명시적으로 적어주지 않고도, 컴파일러가 알아서 변수의 타입을 추론하는 것이다.**
* 대표적인 타입 추론 언어는 **자바스크립트, 코틀린, 스위프트** 등이 있다.
* 타입 추론에 대해서는 아래와 같이 대표적으로 **제네릭**에서 볼 수 있다.

    ```java
    public class Example7 {
        public static void main(String[] args) {
            HashMap<String, Integer> hashMap = new HashMap<>();
        }   
    }
    ```
    - 위에서 `hashMap` 객체를 할당할 때 `new HashMap<>()`를 사용한 이유는 데이터 타입`HashMap<String, Integer>`을 보고 추론 했기 때문이다.

### **Var**
* Var이란?

    ```java
    public class Example8 {
            public static void main(String[] args) {
                var str = "Hello";
                var num = 1;
            }   
        }
    ```
    * 변수 `str` 과 `num`의 타입을 명시하지 않았지만, 컴파일로가 오른쪽에 있는 초기화값 리터럴로 타입을 추론한다.
    * `str` 의 타입 = `String`
    * `num` 의 타입 = `int`
* 주의할 점
    - Var는 초기화없이 사용할 수 없다.
    - Var타입 변수에는 null 값이 들어갈 수 없다.
    - Var 타입은 로컬 변수에만 선언이 가능하다.
    - Lambda Expression에는 명시적인 타입을 지정해줘야 한다.
    - 배열을 선언할 때, var 대신 타입을 명시해줘야 한다.

<br/>

# 참고
* 이것이 자바다 - 신용권
* https://catch-me-java.tistory.com/19
* https://www.notion.so/2-38b5d67c7f5a48238529bb8f1617ea0d
* https://blog.naver.com/hsm622/222144931396

 
