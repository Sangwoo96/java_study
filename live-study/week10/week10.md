# 10주차 - 멀티쓰레드 프로그래밍
본 글은 [백기선님의 live-study](https://github.com/whiteship/live-study/issues)를 진행하며 작성한 자료입니다. 자료에서 사용한 **예제 코드**는 직접 만들었습니다.

<br/>

## ✔ 목표
자바의 멀티쓰레드 프로그래밍에 대해 학습하세요.

<br/>

## ✔ 목차
* Thread 클래스와 Runnable 인터페이스
* 쓰레드의 상태
* 쓰레드의 우선순위
* Main 쓰레드
* 동기화
* 데드락

<br/>

## 💡 10-1 Thread 클래스와 Runnable 인터페이스

---

<br/>

## **프로세스와 쓰레드**

우리가 사용하는 프로그램을 **프로세스(process)**라고 부른다. 사용자가 프로그램을 실행하면 OS로부터 자원을 할당받아 프로세스(process)가 된다. 만약 크롬 브라우저를 실행했다고 가정한다면, 우리는 크롬이라는 프로그램을 실행한 것이고 실행되어 OS에게 자원을 할당받은 크롬 브라우저는 프로세스가 된 것이다.

우리는 컴퓨터를 사용할 때 하나의 프로그램만 사용하지 않고 코딩을 하면서 유튜브로 노래를 듣는 것처럼 여러 프로그램을 동시에 사용한다. 이와 같이 두 가지 이상의 작업을 동시에 하는 것을 **멀티 태스킹(multi tasking)**이라고 부르며, 가능한 이유는 OS가 CPU 및 자원을 프로세스마다 할당하고, 병렬로 실행시키기 때문이다. 

멀티 태스킹은 꼭 멀티 프로세스를 뜻하지는 않는다. 한 프로세스 내에서 멀티 태스킹을 할 수 있기 때문이다. 대표적인 것이 메신저이다. 메신저는 채팅을 하면서 파일을 전송할 수 있는 것처럼 하나의 프로세스가 또 여러 작업을 동시에 처리할 수 있기 때문이다. 

어떻게 하나의 프로세스가 두 개 이상의 작업을 처리할 수 있을까?  

그 이유는 프로세스가 **자원과 쓰레드**들로 구성되어 있으며 **쓰레드**들이 프로세스의 자원을 이용해 채팅과 파일 전송(실제 작업들)을 수행하기 때문이다. 이를 **멀티 쓰레드**라고 부른다.

위의 설명을 그림으로 보면 다음과 같다.

![9](https://user-images.githubusercontent.com/55661631/105519149-70ed4f00-5d1c-11eb-9118-ffdfef9c5432.PNG)

각 프로세스는 OS에게 할당받은 메모리가 다르기 때문에 독립적이다. 따라서 하나의 프로세스에서 오류가 발생해도 다른 프로세스에는 영향을 미치지 않는다.

그러나 프로세스 내의 스레드 사이에서는 독립적이지 않고 영향일 미치기 때문에 예외 처리가 매우 중요하다.

<br/>

## **쓰레드 생성과 실행**

자바에서는 쓰레드를 관리하기 위한 메서드와 변수들을 java.lang.Thread 클래스에서 제공한다.

Thread 클래스로부터 작업 스레드 객체를 직접 생성하려면 다음과 같이 Runnable을 매개값으로 갖는 생성자를 호출해야한다.

```java
Thread thread = new Thread(Runnable target)
```

Runnable은 인터페이스 타입이기 때문에 구현 객체를 만들어 대입해야 한다. Runnable에는 run() 메소드 하나가 정의되어 있는데, 구현 클래스는 run()을 재정의해서 작업 스레드가 실행할 코드를 작성해야한다.

```java
class Task implements Runnable {
    public void run(){
        쓰레드가 실행할 코드;
    }
}
```

Runnable은 작업 내용을 가지고 있는 객체이지 실제 쓰레드는 아니다. 따라서 아래의 코드와 같이 Runnable 구현 객체를 생성한 후 Thread 생성자의 매개값으로 사용해 작업 스레드를 생성해야한다.

```java
Runnable task = new Task();
Thread thread = new Thread(task);
```

<br/>

아래 코드는 Runnable과 Thread를 사용해 작업 스레드를 구현한 예제 코드이다.

`Thread1` : Thread 클래스를 상속받은 Task1을 매개값으로 쓰레드 생성자를 호출하였다.

`Thread2` : Runnable 인터페이스를 구현한 Task2를 매개값으로 쓰레드 생성자를 호출하였다.

`Thread3` : Runnable 익명 객체를 매개값으로 쓰레드 생성자를 호출하였다.

`Thread4` : 람다식을 매개값으로 쓰레드 생성자를 호출하였다.

```java
public class Main {
    public static void main(String[] args) {

        //Thread 클래스를 상속받은 Task1 객체를 매개값으로 쓰레드 생성자 호출
        Thread thread1 = new Thread(new Task1());
        
        //Runnable 인터페이스를 구현한 Task2 객체를 매개값으로 쓰레드 생성자를 호출
        Thread thread2 = new Thread(new Task2());
        
        //쓰레드 생성자를 호출할 때 Runnable 익명 객체를 매개값으로 사용
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                //쓰레드가 실행할 코드
                for(int i = 0; i<100; i++){
                    System.out.print("3");
                }
            }
        });

        //쓰레드 생성자를 호출할 때 람다식을 매개값으로 사용
        Thread thread4 = new Thread(()->{
            //쓰레드가 실행할 코드
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
        //쓰레드가 실행할 코드
        for(int i = 0; i<100; i++){
            System.out.print("1");
        }
    }
}

class Task2 implements Runnable{
    @Override
    public void run() {
        //쓰레드가 실행할 코드
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

<br/>

각 쓰레드는 1,2,3,4를 출력하도록 구현하였으며 실행결과를 보면 1 2 3 4가 뒤섞여있는 것을 확인할 수 있다. 

쓰레드들은 OS의 스케쥴링에 따라 작업 시간과 순서가 정해져 번갈아가며 작업을 수행한다. 

위에서는 동시에 쓰레드들이 동시에 수행된다고 말했지만 사실은 번갈아가며 수행된다. 매우 빠르게 작업을 수행하기 때문에 동시에 실행되는 것 같은 효과를 보여주는 것이다.


