# 2주차 - 자바 데이터 타입, 변수 그리고 배열
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

## 목표
자바가 제공하는 다양한 연산자를 학습하세요.

## 목차
* 산술 연산자
* 비트 연산자
* 관계 연산자
* 논리 연산자
* instanceof
* assignment(=) operator
* 화살표(->) 연산자
* 3항 연산자
* 연산자 우선 순위
* (optional) Java 13. switch 연산자

## 3 연산자의 종류와 용어 정의
* 연산 : 프로그램에서 데이터를 처리하여 결과를 산출하는 것
* 연산자 : 연산에 사용되는 표시나 기호
* 피연산자 : 연산되는 데이터
* 연산식 : 연산자와 피연산자를 이용하여 연산의 과정을 기술한 것

| 연산자 종류 | 연산자 | 피연산자 수 | 산출값 | 기능 설명
| :---: | :---: | :---: | :---: | :---: |
산술 | +, -, *, /, % | 이항 | 숫자 | 사칙연산 및 나머지 계산
부호 | +, - | 단항 | 숫자 | 음수와 양수의 부호
문자열 | + | 이항 | 문자열 | 두 문자열을 연결
대입 | =, +=, -=, *=, /=, %=, &=, ^=, l=, <<=, >>=, >>>= | 이항 | 다양 | 우변의 값을 좌변의 변수에 대입
증감 | ++, == | 단항 | 숫자 | 1만큼 증가/감소
비교 | ==, !=, >, <, >=, <=, instanceof | 이항 | boolean | 값의 비교
논리 | !, &, l, &&, ll | 단항, 이항 | boolean | 논리적 NOT, AND, OR 연산
조건 | (조건식) ? A : B | 삼항 | 다양 |조건식에 따라 A 또는 B 중 하나를 선택
비트 | ~, &, l, ^ | 단항, 이항 | 숫자, boolean |  비트 NOT, AND, OR, XOR 연산
쉬프트 | >>, <<, >>> | 이항 | 숫자 | 비트를 좌측/우측으로 밀어서 이동

<br/>

## 3-1 산술 연산자
* `+`, `-`, `*`, `/`, `%`
* **피연산자들의 타입이 동일하지 않을 경우 다음과 같은 규칙을 사용해서 타입을 일치시킨다.**
    - 피연산자들의 모두 정수 타입이고, int 타입보다 크기가 작을 경우, 연산의 산출 타입은 int이다.
    - 피연산자들이 모두 정수 타입이고, long 타입이 있을 경우 모두 long 타입으로 변환 후, 연산을 수행. 따라서 연산의 산출 타입은 long이다.
    - 피연산자 중 실수 타입(float, double)이 있을 경우, 크기가 큰 실수 타입으로 변환 후, 연산을 수행. 따라서 연산의 산출 타입은 실수 타입이다.
    - **정수 타입 연산의 결과가 int 타입으로 나오는 이유는 자바 가상 기계(JVM)가 기본적으로 32비트 단위로 계산하기 때문이다.
    ```java
    byte a = 1;
    byte b = 2;
    byte c = a + b; //컴파일 에러
    int d = a + b; //정상
    ```
    ```java
    int a = 10;
    int b = 4;
    int result1 = a / b; //2
    double result2 = a / b; //2.0
    ```
    ```java
    int a = 10;
    double b = 4;
    double result = a / b; //2.5
    ```
* NaN 과 Infinity

    ```java
    5 / 0 //ArtimeticException 예외 발생
    5 % 0 //ArtimeticException 예외 발생
    ```
    ```java
    5 / 0.0 //Infinity
    5 % 0.0 //NaN
    ```

<br/>

## 3-2 비트 연산자
* `&` (AND)
* `l` (OR)
* `^` (XOR)
* `~` (NOT)
* `<<` (left SHIFT)
* `>>` (right SHIFT)
* `>>>` (unsigned right SHIFT): 비트값을 오른쪽으로 이동한
  
```java
int a = 3 & 1; // 0011 & 0001 = 1
int b = 2 | 1; // 0010 | 0001 = 3
int c = 3 ^ 1; // 0011 ^ 0001 = 2
int d = ~a; // 0001 -> 1111 1111 1111 1111 1111 1111 1111 1110
int e = b >> 1; // 0011 에서 왼쪽으로 1칸 이동, 1(0001)
int f = b << 1; // 0011 에서 오른쪽으로 1칸 이동, 6(0110)
int g = -8 >>> 3 //-8 -> 536870911
```

<br/>

## 3-3 관계 연산자
- `==` : Equal to
- `!=` : Not equal to
- `>` : greater than
- `<` : less than
- `>=` : greater than or equal to
- `<=` : less than or equal to
- 연산 결과 타입은 `boolean`


<br/>

## 3-4 논리 연산자
* `&&`, `||`, `&`, `|`, `^`, `!`
* &&와 &&는 산출 결과는 같지만 연산 과정이 조금 다르다.
* &&는 앞의 피연산자가 false라면 뒤의 피연산자를 평가하지 않고 &는 두 피연산자 모두를 평가해서 산출 결과를 낸다.
* **따라서 &보다 &&가 더 효율적으로 동작한다.**
* 피연산자의 타입은 `boolean`
* 연산 결과 타입은 `boolean`

<br/>

## 3-5 instanceOf
* `instanceOf` 연산자는 객체가 어떤 클래스인지, 어떤 클래스를 상속받았는지 확인하는데 사용된다.
* Syntax
    - `object instanceOf type`
* null 객체에 대한 `instanceOf` 는 항상 false 반환

```java
public class Main {
    public static void main(String[] args) {
        School school = new School();
        Student student = new Student();
        Child child = new Child();

        System.out.println(school instanceof School); //true
        System.out.println(student instanceof School); //true
        System.out.println(child instanceof Parent); //true
        System.out.println(student instanceof Parent); //false
    }
}

class School {}
interface Parent {}
class Student extends School {}
class Child implements Parent {}
```

<br/>

## 3-6 Assignment(=) operator
* Assignment operator(대입 연산자)
  - `=` (ASSIGN)
  - `+=` (ADD and ASSIGN)
  - `-=` (SUBTRACT and ASSIGN)
  - `*=` (MULTIPLY and ASSIGN)
  - `/=` (DIVIDE and ASSIGN)
  - `%=` (MODULO and ASSIGN)
  - `&=` (AND and ASSIGN)
  - `^=` (XOR and ASSIGN)
  - `|=` (OR and ASSIGN)
  - `<<=` (LEFT SHIFT and ASSIGN)
  - `>>=` (RIGHT SHIFT and ASSIGN)
  - `>>>=` (UNSIGNED RIGHT SHIFT and ASSIGN)
* 오른쪽의 값을 왼쪽 피연산자에 할당한다.
    
    ```java
    int a = 0;
    int b = 1;
    int c = 2;
    ```
* 대입 연산자는 모든 연산자들 중에서 **가장 낮은 연산 순위**를 가지고 있다.
* 연산의 진행 방향이 오른쪽에서 왼쪽이다.
    - `a = b = c = 5`
        - `c = 5`
        - `b = c = 5`
        - `a = b = c = 5`
        - 순서로 진행된다.
* **객체 참조**를 할당하기 위해 사용된다. 주소값을 할당하는 것을 주의해야한다.
    - 런타임 스택에 `school` 과 `student` 변수 이름 **주소값**이 저장된다. 이 주소값은 **힙 영역**의 주소를 가르킨다.
    ```java
    public class Main {
    public static void main(String[] args) {
        School school = new School(); //주소값 할당
        Student student = new Student(); //주소값 할당

        System.out.println(school); //com.company.Student@3ac3fd8b -> 주소값
        System.out.println(student); //com.company.School@5594a1b5 -> 주소값
        }
    }
    class School {}
    class Student {};
    ```

    <br/>

## 3-7 화살표(->) 연산자
* 자바 8 이상의 **람다식**에서 익명함수를 만들 때 사용한다.
* 코드가 간결해 진다는 장점이 있다.
* 화살표 왼쪽에 인자, 오른쪽에 실제 표현식을 쓴다.

    ```
    (타입 매개변수, ...) -> { 실행문; ... }
    ```

```java
public class Main {
    public static void main(String[] args) {
        // 람다 표현식 사용 X
        Compare compare = new Compare(){
            @Override
            public int compareTo(int a, int b) {
                return a>=b?a:b;
            }
        };

        // 람다 표현식 사용 O
        Compare compare = (a, b) -> { return a>=b?a:b; };

        System.out.println(compare.compareTo(2,1));
    }
}

interface Compare {
    int compareTo(int a, int b);
}
```

<br/>

## 3-8 3항 연산자
- `?:`
- 구조

    ```
    조건식 ? 값 또는 연산식(true인 경우) : 값 또는 연산식(false 인 경우);
    ```
```java
    int a = 5;
    int b = 7;

    int c = a>=b?a:b; //3항 연산자 사용
    System.out.println(c); // 7
```

<br/>

## 3-9 연산자 우선 순위
우선 순위 | 연산자 | 설명
| :---: | :---: | :---: |
1 | expr++ expr-- | 후위 증가/감소 연산자
2 | ++expr --expr +expr -expr ~ ! | 전위 증가/감소 연산자, 양/음 부호, 논리/비트 NOT 연산자
3 |	* ㅤ / ㅤ %	 | 곱셈/나눗셈/나머지 연산자
4 |	+ ㅤ -	 | 덧셈/뺄셈 연산자
5 |	<< ㅤ >> ㅤ >>>	 | 비트 왼쪽/오른쪽 Shift 연산자, Unsigned Shift 연산자
6 |	< ㅤ > ㅤ <= ㅤ >= ㅤ instanceof	 | 보다 작은/보다 큰/보다 작거나 같은/보다 크거나 같은 관계 연산자, instanceof
7 |	== ㅤ !=	| 와 같은/와 다른 관계 연산자
8 |	&	| 비트 AND 연산자
9 |	^	|비트 XOR 연산자
10 |	`|`	| 비트 OR 연산자
11 |	&&	| 논리 AND 연산자
12 |	`||`	| 논리 OR 연산자
13 |	? :	| 삼항 조건 연산자
14 |	= ㅤ += ㅤ -= ㅤ *= ㅤ /= ㅤ %= ㅤ &= ㅤ ^= ㅤ `|=` ㅤ <<= ㅤ >>= ㅤ >>>=	| 대입 연산자 및 복합 대입 연산자

<br/>

## 3-10 (optional) Java 13. switch 연산자

* **아래와 같은 `switch` 문이 있을 때.**

```java
public enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY; }
// ...
    int numLetters = 0;
    Day day = Day.WEDNESDAY;
    switch (day) {
        case MONDAY:
        case FRIDAY:
        case SUNDAY:
            numLetters = 6;
            break;
        case TUESDAY:
            numLetters = 7;
            break;
        case THURSDAY:
        case SATURDAY:
            numLetters = 8;
            break;
        case WEDNESDAY:
            numLetters = 9;
            break;
        default:
            throw new IllegalStateException("Invalid day: " + day);
    }
    System.out.println(numLetters);
```

* **아래와 같이 변경이 가능하다.**

```java
int numLetters = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> {
        System.out.println(6);
        yield 6;
    }
    case TUESDAY -> {
        System.out.println(7);
        yield 7;
    }
    case THURSDAY, SATURDAY -> {
        System.out.println(8);
        yield 8;
    }
    case WEDNESDAY -> {
        System.out.println(9);
        yield 9;
    }
    default -> {
        throw new IllegalStateException("Invalid day: " + day);
    }
};  
```
* `자바 12` 부터 콜론(:) 대신 화살표(->) 연산자를 사용할 수 있다.
* `자바 13` 부터 `break` 대신 `yield` 를 사용한다.

<br/>

# 참고
* 이것이 자바다 - 신용권
* https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html
* https://github.com/gtpe/java-study/blob/master/w3.md#optional-java-13-switch-operator