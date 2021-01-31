# 11주차 - Enum
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

# ✔ 목표
자바의 열거형에 대해 학습하세요.

<br/>

# ✔ 목차
* enum 정의하는 방법
* enum의 특징
* enum이 제공하는 메소드 (values()와 valueOf())
* java.lang.Enum
* EnumSet

<br/>

# 💡 11-1 Enum 정의하는 방법

## **Enum(열거형)이란?**

enum(열거형)은 열거형 클래스이며, 자바 1.5부터 사용이 가능하다.

서로 관련된 상수를 편리하게 선언하기 위한 것으로, 여러 상수를 정의할 때 사용하면 유용하다.   
자바의 열거형은 C언어의 열거형보다 더 향상된 것으로 **열거형이 갖는 값뿐만 아니라 타입도 관리하기 때문에 보다 논리적인 오류를 줄일 수 있다.**

자바 외 언어들에서는 타입이 달라도 값이 같으면 조건식 결과가 참(true)인 경우가 있으나, 자바의 열거형은 **타입에 안전한 열거형(typesafe enum)** 이라서 실제 값이 같아도, 타입이 다르면 **컴파일** 에러가 발생한다.

<br/>

## **Enum은 왜 사용하는가?**

Enum을 잘 사용하면 코드의 가독성을 높이고 논리적인 오류를 줄일 수 있다.

학생 이름을 입력받으면 시험 점수를 알려주는 프로그램의 학생 이름을 상수로 관리한다고 했을 때의 문제점을 알아보자.

```java
public class EnumDemo {
    //A학교
    private static final int KIM = 1;
    private static final int NAM = 2;
    private static final int PARK = 3;
    
    //B학교
    private static final int KIM = 4; //컴파일 에러
    private static final int NAM = 2; //A학교 NAM과 중복

    public static void main(String[] args) {
        int name = KIM;
        switch(name){
            case KIM:
                System.out.println("학생 KIM는 100점입니다.");
                break;
            case NAM:
                System.out.println("학생 NAM는 70점입니다.");
                break;
            case PARK:
                System.out.println("학생 PARK는 50점입니다.");
                break;
        }
    }
}
```

위 코드의 문제점은 다음과 같다.

1. 각 상수에 부여된 1,2,3 이라는 값은 논리적으로 아무런 의미가 없다. 즉 학생 KIM와 1은 아무런 관련이 없다.

2. 이름의 충돌이 생길 수 있다. 만약 프로그램의 크기가 커저서 다른 학교의 학생들도 관리한다고 가정해보자. A라는 학교에 KIM이라느 학생이 있고 B라는 학교에도 KIM이라는 학생이 있으면 이름이 중복되기 때문에 에러가 발생한다. 

3. 학교가 서로 다른 학생들끼리는 시험 점수가 비교되면 안되는데 학교 A의 학생 NAM과 학교 B의 학생 NAM 모두 int형 자료형이고 상수값이 동일하기 때문에 비교가 가능하다. 애초에 비교하는 코드를 작성할 수 없게 컴파일 단계에서 막아줘야 한다.


**한정된 값(학생 이름)을 Enum(열거형)으로 관리한다면 위와 같은 문제점들을 해결해주고, 더욱 간단히 선언할 수 있도록 만든다.**

<br/>

## **정의하는 방법**

### **문법**

```
enum 열거형이름 { 상수명1, 상수명2, ... 상수명n; }
```

### **예제**

```java
enum Job { TEACHER, STUDENT; }
```

<br/>

## **사용하는 방법**

### **문법**

```
열거형이름.상수이름
```

### **예제**

```java
Job.TEACHER
```

<br/>

# 💡 11-2 Enum의 특징

## **1. Enum에 정의된 상수들은 해당 Enum 타입의 객체이다.**

C 등의 다른 언어에도 열거형이 존재한다. 하지만 다른 언어들과 달리 Java의 enum은 단순한 정수 값이 아닌 해당 enum 타입의 객체이다.

```java
enum Job { TEACHER, STUDENT; }
```

위와 같이 정의된 열거형은 아래와 같이 표현할 수 있다.

```java
class Job {
    public static final Job TEACHER = new Job("TEACHER");
    public static final Job STUDENT = new Job("STUDENT");
    
    private String name;
    
    private Job(String name) {
        this.name = name;
    }
}
```

**물론 실제 Enum의 구현체와 다르지만, 이런 형태로 생각하면 이해하기 더욱 쉽다.**

<br/>

## **2. 생성자와 메소드를 추가할 수 있다**

```java
enum Job {
    TEACHER,
    STUDENT;

    Job(){
        System.out.println(this.name() + " 생성자 호출");
    }
}

public class EnumDemo {
    public static void main(String[] args) {
        Job student = Job.STUDENT;
    }
}
```

<br/>

```
TEACHER 생성자 호출
STUDENT 생성자 호출
```

생성자를 정의할 수 있는데, enum의 생성자의 접근제어자는 private이기 때문에 외부에서 상수를 추가할 수 없다. 

열거형의 멤버 중 하나를 호출하면, 열거된 모든 상수의 객체가 생성된다. 위 예시를 보면 STUDENT 하나를 호출했는데 열거된 모든 상수의 생성자가 호출되었음을 확인할 수 있다. 상수 하나당 각각의 인스턴스가 만들어지며 모두 `public static final` 이다.

<br/>

<br/>

### **생성자를 이용해서 상수에 데이터를 추가할 수 있다.**

<br/>

```java
enum Job {
    DOCTOR(5000),
    TEACHER(1500),
    NURSE(1000);

    private final int dailyWages; //일급

    Job(int dailyWages){
        this.dailyWages = dailyWages;
    }

    public int getDailyWages() {
        return dailyWages;
    }
}

public class EnumDemo {
    public static void main(String[] args) {
        System.out.println("----일급----");
        System.out.println("DOCTOR : " + Job.DOCTOR.getDailyWages());
        System.out.println("TEACHER : " + Job.TEACHER.getDailyWages());
        System.out.println("NURSE : " + Job.NURSE.getDailyWages());
    }
}
```

<br/>

```
----일급----
DOCTOR : 5000
TEACHER : 1500
NURSE : 1000
```

<br/>

<br/>

### **메소드에 switch문을 사용해 상수에 따라 다른 로직을 실행시킬수 있다.**

<br/>

```java
public double getMonthlyWages(int day) //상수에 따라 다르게 측정되는 월급
{
    switch (this) {
        case DOCTOR:
            return dailyWages * day * 2.5;
        case TEACHER:
            return dailyWages * day * 2;
        case NURSE:
            return dailyWages * day * 1.5;
        default:
            return 0;
    }
}
```

Enum Job 에 위의 메소드를 추가한 뒤 아래와 같이 출력하면

```java
System.out.println("----월급----");
System.out.println("DOCTOR : " + Job.DOCTOR.getMonthlyWages(30));
System.out.println("TEACHER : " + Job.TEACHER.getMonthlyWages(30));
System.out.println("NURSE : " + Job.NURSE.getMonthlyWages(30));
```

다음과 같은 결과가 나오는 것을 확인할 수 있다.

```
----월급----
DOCTOR : 375000.0
TEACHER : 90000.0
NURSE : 45000.0
```

<br/>

<br/>

### **추상 메서드를 선언해서 위와 동일하게 구현할 수 있다.**

<br/>

```java
enum Job {
    DOCTOR(5000){
        @Override
        double getMonthlyWages(int day) {
            return getDailyWages() * day * 2.5;
        }
    },
    TEACHER(1500){
        @Override
        double getMonthlyWages(int day) {
            return getDailyWages() * day * 2;
        }
    },
    NURSE(1000){
        @Override
        double getMonthlyWages(int day) {
            return getDailyWages() * day * 1.5;
        }
    };

    private final int dailyWages; //일급

    Job(int dailyWages){
        this.dailyWages = dailyWages;
    }

    public int getDailyWages() {
        return dailyWages;
    }

    abstract double getMonthlyWages(int day); //상수에 따라 다르게 측정되는 월급
}
```

<br/>

<br/>

### **Enum 상수간의 비교가 가능하다.**

<br/>

열거형 상수간의 비교에는 `==` 을 사용할 수 있다. equals()가 아닌 `==` 로 비교가 가능하다는 것은 그만큼 빠른 성능을 제공한다는 뜻이다.

```java
Job doctor = Job.DOCTOR;
if(doctor == Job.DOCTOR){
    ...
} else if(doctor.compareTo(Job.NURSE) > 0){
    ...
}
```

<br/>

# 💡 11-3 Enum이 제공하는 메소드 (values()와 valueOf())

## **values()**

열거형의 모든 상수를 배열에 담아 반환한다.

```java
Job[] jobs = Job.values();
for(var i : jobs){
    System.out.println(i.name());
}
```

```
DOCTOR
NURSE
TEACHER
```

<br/>

## **valueOf()**

지정된 열거형에서 name과 일치하는 열거형을 반환한다.

```java
Job job = Job.valueOf("DOCTOR");

if(job == Job.DOCTOR){
    System.out.println("동일합니다.");
}
```

```
동일합니다.
```

<br/>

## **ordinal()**

상수가 정의된 순서를 반환한다.

```java
enum Fruit{
    APPLE, BANANA, ORANGE;
}

public class Ordinal {
    public static void main(String[] args) {
        Fruit apple = Fruit.APPLE;
        Fruit banana = Fruit.BANANA;
        Fruit orange = Fruit.ORANGE;

        System.out.println(apple.ordinal());
        System.out.println(banana.ordinal());
        System.out.println(orange.ordinal());
    }
```

```
0
1
2
```

**ordianl은 Enum 내부에서 사용하기 위해 만든 것이지, 아래와 같이 개발자들이 사용하는 것은 안티패턴이다.**

```java
if(apple.ordinal() == 0){
    System.out.println("사과가 맞습니다.");
}else if(apple.ordinal() != 0){
    System.out.println("사과가 아닙니다.");
}
```

이유는 아래와 같이 다른 개발자에 의해 Enum의 정의 순서가 바뀌면, apple.ordinal()은 1이 되어 틀린 로직이 되기 때문이다.

```java
enum Fruit{
    KIWI, APPLE, BANANA, ORANGE;
}
```

<br/>

# 💡 11-4 java.lang.Enum

```java
public abstract class Enum<E extends Enum<E>>
        implements Comparable<E>, Serializable {

    private final String name;

    public final String name() {
        return name;
    }
    ...
}
```
java.lang 에 포함된 Enum 클래스는 모든 자바 열거형의 조상이다.  
모든 열거형은 Enum 클래스를 상속받기 때문에 enum 타입은 상속이 불가능하다.  
앞서 언급했던 메서드들은 모두 여기 정의되어 있다. toString을 제외한 대부분의 메서드는 final로 선언되어 있기 때문에 별도의 오버라이딩을 할 수 없다.

<br/>

# 💡 11-5 EnumSet

## **EnumSet의 특징**

* EnumSet은 AbstractSet 클래스를 상속하고 Set 인터페이스를 구현한다.

* 오직 열거형 상수만을 값으로 가질 수 있다. 또한 모든 값은 같은 enum 타입이어야 한다.

* null value를 추가하는 것을 허용하지 않는다. NullPointerException을 던지는 것도 허용하지 않는다.

* ordinal 값의 순서대로 요소가 저장된다.

* tread-safe하지 않다. 동기식으로 사용하려면 Collections.synchronizedMap을 사용하거나, 외부에서 동기화를 구현해야한다.

* 모든 메서드는 arithmetic bitwise operation을 사용하기 때문에 모든 기본 연산의 시간 복잡도가 O(1)이다.

<br/>

## **사용하는 이유**

특정 열거형의 모든 상수를 리스트화 시킬 일이 생길 수 도 있다. 그 때 EnumSet을 사용하지 않고 구현하려면 매우 번거롭기 때문이다.

아래의 코드와 같이 단 한줄로 모든 상수를 리스트화 시킬 수 있다.

```java
// HashSet을 사용
Set<Color> enumSet = new HashSet<>();
enumSet.add(Color.RED);
enumSet.add(Color.BLUE);
...

//EnumSet을 사용한 경우
EnumSet<Fruit> enumSet = EnumSet.allOf(Fruit.class);
```

<br/>

## **사용법**

```java
enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE
    
}

public class EnumDemo {

    public static void main(String[] args) {
        EnumSet<Color> set1, set2, set3, set4, set5;

        set1 = EnumSet.allOf(Color.class);
        set2 = EnumSet.of(Color.RED, Color.GREEN, Color.BLUE);
        set3 = EnumSet.complementOf(set2);
        set4 = EnumSet.range(Color.YELLOW, Color.BLACK);

        set5 = EnumSet.noneOf(Color.class);
        set5.add(Color.BLACK);
        set5.add(Color.BLUE);
        set5.remove(Color.BLUE);

        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);
        System.out.println("set3 = " + set3);
        System.out.println("set4 = " + set4);
        System.out.println("set5 = " + set5);
        System.out.println(set5.contains(Color.BLACK));
    }
}
```

```java
set1 = [RED, YELLOW, GREEN, BLUE, BLACK, WHITE]
set2 = [RED, GREEN, BLUE]
set3 = [YELLOW, BLACK, WHITE]
set4 = [YELLOW, GREEN, BLUE, BLACK]
set5 = [BLACK]
true
```

<br/>

# 💡 (추가)실무에서의 활용

실무에서 Enum을 어떻게 활용하는지 정리된 글이다. 나중에 다시 읽어보자.
### **[Java Enum 활용기](https://woowabros.github.io/tools/2017/07/10/java-enum-uses.html)**


<br/>


# 참고
* 이것이 자바다 - 신용권
* https://wisdom-and-record.tistory.com/52
* https://sujl95.tistory.com/66
* https://woowabros.github.io/tools/2017/07/10/java-enum-uses.html