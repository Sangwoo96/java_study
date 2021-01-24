# 10주차 - 멀티스레드 프로그래밍
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

# ✔ 목표
자바의 멀티스레드 프로그래밍에 대해 학습하세요.

<br/>

# ✔ 목차
* Thread 클래스와 Runnable 인터페이스
* 스레드의 상태
* 스레드의 우선순위
* Main 스레드
* 동기화
* 데드락

<br/>

# 💡 10-1 Thread 클래스와 Runnable 인터페이스

<br/>

## **프로세스와 스레드**

우리가 사용하는 프로그램을 **프로세스(process)**라고 부른다. 사용자가 프로그램을 실행하면 OS로부터 자원을 할당받아 프로세스(process)가 된다. 만약 크롬 브라우저를 실행했다고 가정한다면, 우리는 크롬이라는 프로그램을 실행한 것이고 실행되어 OS에게 자원을 할당받은 크롬 브라우저는 프로세스가 된 것이다.

우리는 컴퓨터를 사용할 때 하나의 프로그램만 사용하지 않고 코딩을 하면서 유튜브로 노래를 듣는 것처럼 여러 프로그램을 동시에 사용한다. 이와 같이 두 가지 이상의 작업을 동시에 하는 것을 **멀티 태스킹(multi tasking)**이라고 부르며, 가능한 이유는 OS가 CPU 및 자원을 프로세스마다 할당하고, 병렬로 실행시키기 때문이다. 

멀티 태스킹은 꼭 멀티 프로세스를 뜻하지는 않는다. 한 프로세스 내에서 멀티 태스킹을 할 수 있기 때문이다. 대표적인 것이 메신저이다. 메신저는 채팅을 하면서 파일을 전송할 수 있는 것처럼 하나의 프로세스가 또 여러 작업을 동시에 처리할 수 있기 때문이다. 

어떻게 하나의 프로세스가 두 개 이상의 작업을 처리할 수 있을까?  

그 이유는 프로세스가 **자원과 스레드**들로 구성되어 있으며 **스레드**들이 프로세스의 자원을 이용해 채팅과 파일 전송(실제 작업들)을 수행하기 때문이다. 이를 **멀티 스레드**라고 부른다.

위의 설명을 그림으로 보면 다음과 같다.

![9](https://user-images.githubusercontent.com/55661631/105519149-70ed4f00-5d1c-11eb-9118-ffdfef9c5432.PNG)

각 프로세스는 OS에게 할당받은 메모리가 다르기 때문에 독립적이다. 따라서 하나의 프로세스에서 오류가 발생해도 다른 프로세스에는 영향을 미치지 않는다.

그러나 프로세스 내의 스레드 사이에서는 독립적이지 않고 영향을 미치기 때문에 예외 처리가 매우 중요하다.

<br/>

## **스레드 생성과 실행**

자바에서는 스레드를 관리하기 위한 메서드와 변수들을 java.lang.Thread 클래스에서 제공한다.

스레드를 생성하는 방법은 크게 두 가지 방법이 있다.

1. Thread 클래스 사용
2. Runnable 인터페이스 사용

아래 코드는 Runnable과 Thread를 사용해 작업 스레드를 구현한 예제 코드이다.

```java
public class Main {
    public static void main(String[] args) {

        //Thread 클래스를 상속받은 Task1 객체를 매개값으로 스레드 생성자 호출
        Thread thread1 = new Task1();
        
        //Runnable 인터페이스를 구현한 Task2 객체를 매개값으로 스레드 생성자를 호출
        Thread thread2 = new Thread(new Task2());
        
        //스레드 생성자를 호출할 때 Runnable 익명 객체를 매개값으로 사용
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                //스레드가 실행할 코드
                for(int i = 0; i<100; i++){
                    System.out.print("3");
                }
            }
        });

        //스레드 생성자를 호출할 때 람다식을 매개값으로 사용
        Thread thread4 = new Thread(()->{
            //스레드가 실행할 코드
            for(int i = 0; i<100; i++){
                System.out.print("4");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}

class Task1 extends Thread{
    @Override
    public void run() {
        //스레드가 실행할 코드
        for(int i = 0; i<100; i++){
            System.out.print("1");
        }
    }
}

class Task2 implements Runnable{
    @Override
    public void run() {
        //스레드가 실행할 코드
        for(int i = 0; i<100; i++){
            System.out.print("2");
        }
    }
}
```

<br/>

실행결과
```
11111111111111111111111114444444444444444444444444444444444444444444444444444444444444444444443333333333332222222222222222....
```

`Thread1` : Thread 클래스를 상속받은 Task1을 매개값으로 스레드 생성자를 호출하였다.

`Thread2` : Runnable 인터페이스를 구현한 Task2를 매개값으로 스레드 생성자를 호출하였다.

`Thread3` : Runnable 익명 객체를 매개값으로 스레드 생성자를 호출하였다.

`Thread4` : 람다식을 매개값으로 스레드 생성자를 호출하였다.

<br/>

## **어느걸 사용 해야할까?**

* Task(Thread) 클래스가 다른 클래스를 추가로 확장할 필요가 있으면 Runnable 인터페이스를 구현하고 아니면 Thread 클래스를 상속받는 것이 좋다.

* Thread 클래스를 상속받으면 다양한 메소드를 오버라이딩 할 수 있다. 반면 Runnable 인터페이스를 구현하면 Run() 메소드면 오버라이딩이 가능하다.

<br/>

## **Thread는 순서대로 동작할까?**

순서대로 동작하지 않는다. 위 실행결과를 보면 생성된 스레드순으로 실행 하지 않는 것을 볼 수 있다. 스레드는 OS의 스케쥴링에 따라 작업 시간과 순서가 정해져 순서대로 실행 하지 않는다. 컴퓨터의 성능에 따라 순서가 달라질 수 있으며 매변 결과는 다르다.

<br/>

## **start()와 run()**

출처 : https://wisdom-and-record.tistory.com/48

스레드를 실행하기 위해서는 start 메서드를 통해 해당 스레드를 호출해야 한다. start 메서드는 스레드가 작업을 실행할 호출 스택을 만들고 그 안에 run 메서드를 올려주는 역할을 한다.

![a](https://user-images.githubusercontent.com/55661631/105608787-787a2a00-5de8-11eb-979a-b35e2bd4e413.png)

위 예제에서 start를 호출하지 않고 run을 호출하면, 새로운 호출 스택이 생성되지 않기 때문에, 그냥 한 메서드 안에서 코드를 실행하는 것과 같다.

```java
thread1.run();
thread2.run();
thread3.run();
thread4.run();
```

실행결과
```
11111111111...222222222222...3333333333...4444444444
```

실행결과를 보면 run()을 사용하면 순서대로 출력되는 것을 확인할 수 있다.

<br/>

# 💡 10-2 스레드의 상태

![b](https://user-images.githubusercontent.com/55661631/105609420-921d7080-5dec-11eb-8530-f001aa5ffa84.png)
출처 : https://wisdom-and-record.tistory.com/48

스레드에는 위 그림과 같이 5 가지 상태가 있다.

* 생성(NEW) : 스레드 객체가 생성되었지만, 아직 시작하지 않은 상태
* 실행 대기(RUNNABLE) : 실행 상태로 언제든지 갈 수 있는 상태
* 일시 정지
  * WAITING : 다른 스레드가 부를 때까지 기다리는 상태
  * TIMED_WAITING : 주어진 시간동안 기다리는 상태
  * BLOCKED : 락이 풀릴 때까지 기다리는 상태
* 종료(TERMINATED) : 실행을 마친 상태

<br/>

## **interrupt()**

일시 정지 상태의 스레드에서 interruptedException 예외를 발생시켜, 예외 처리 코드(catch)에서 실행 대기 상태로 가거나 종료 상태로 갈 수 있도록 한다.

<br/>

## **notify(), notifyAll()**

동기화 블록 내에서 wait() 메소드에 의해 일시 정지 상태에 있는 스레드를 실행 대기 상태로 만든다.