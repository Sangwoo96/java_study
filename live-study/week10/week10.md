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
* 데몬 스레드
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

        //Thread 클래스를 상속받은 Task1 인스턴스 생성
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
11111111111111111111111114444444444444444444444444444444444444444444444443333333333332222222222222222....
```

`Thread1` : Thread 클래스를 상속받은 Task1을 인스턴스를 생성하였다.

`Thread2` : Runnable 인터페이스를 구현한 Task2를 매개값으로 스레드 생성자를 호출하였다.

`Thread3` : Runnable 익명 객체를 매개값으로 스레드 생성자를 호출하였다.

`Thread4` : 람다식을 매개값으로 스레드 생성자를 호출하였다.

<br/>

## **어느걸 사용 해야할까?**

스레드를 구현하는 두 가지 방법 중에서 어느 쪽을 선택하든 스레드를 구현하는데 차이는 없다. 다만 Thread 클래스를 상속받으면 다른 클래스들을 상속받을 수 없기 때문에, Runnable 인터페이스를 구현하는 방법이 일반적이다.

Runnable 인터페이스를 구현하는 방법은 재사용성이 높고 코드의 일관성을 유지할 수 있기 때문에 보다 객체지향적인 방법이라고 말할 수 있다.

<br/>

## **Thread는 순서대로 동작할까?**

순서대로 동작하지 않는다. 위 실행결과를 보면 생성된 스레드순으로 실행 하지 않는 것을 볼 수 있다. 스레드는 OS의 스케쥴링에 따라 작업 시간과 순서가 정해져 순서대로 실행 하지 않는다. 컴퓨터의 성능에 따라 순서가 달라질 수 있으며 매번 결과는 다르다.

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

## **스레드의 메소드**

스케줄링과 관련된 메소드들은 아래 스레드의 상태에서 다루겠다.

* currentThread() : 현재 실행중인 스래드 객체의 참조를 반환한다.
* destroy() : clean up 없이 스레드를 파과한다. @deprecated 된 메소드로 suspend()와 같이 데드락을 발생시키기 쉽다.
* isAlive() 스레드가 살아있는지 확인하는 메소드이다.
* setPriority(int newPriority) : 스레드의 우선순위를 새로 설정할 수 있는 메소드이다.
* getPriority() : 스레드의 우선순위를 반환한다.
* setName(String name) : 스레드의 이름을 새로 설정한다.
* getName() : 스레드의 이름을 반환한다.
* getThreadGroup() : 스레드가 속한 스레드 그룹을 반환한다. 종료됐거나 정지된 스레드라면 null을 반환한다.
* activeCount() : 현재 스레드의 스레드 그룹 내의 스레드 수를 반환한다.
* enumerate(Thread[] tarray) : 현재 스레드의 스레드 그룹내에 있는 모든 활성화된 스레드들을 인자로 받은 배열에 넣는다. 그리고 활성화된 스레드의 숫자를 int 타입의 정수로 반환한다.
* dumpStack() : 현재 스레드의 stack trace를 반환한다.
* setDaemon(boolean on) : 이 메소드를 호출한 스레드를 데몬 스레드 또는 사용자 스레드로 설정한다. JVM은 모든 스레드가 데몬 스레드만 있다면 종료됩니다. 이 메소드는 스레드가 시작되기 전에 호출되야한다.
* isDaemon() : 이 스레드가 데몬 스레드인지 아닌지 확인하는 메소드입니다. 데몬스레드면 true, 아니면 false 반환
* getStackTrace() : 호출하는 스레드의 스택 덤프를 나타내는 스택 트레이스 요소의 배열을 반환한다.
* getAllStackTrace() : 활성화된 모든 스레드의 스택 트레이스 요소의 배열을 value로 가진 map을 반환한다. key는 thread 입니다.
* getId() : 스레드의 고유값을 반환한다. 고유값은 long 타입의 정수 입니다.
* getState() : 스레드의 상태를 반환한다.

<br/>

# 💡 10-2 스레드의 상태

스레드에는 5 가지 상태가 있다.

* 생성(NEW) : 스레드 객체가 생성되었지만, 아직 시작하지 않은 상태
* 실행 대기(RUNNABLE) : 실행 상태로 언제든지 갈 수 있는 상태
* 일시 정지
  * WAITING : 다른 스레드가 부를 때까지 기다리는 상태
  * TIMED_WAITING : 주어진 시간동안 기다리는 상태
  * BLOCKED : 락이 풀릴 때까지 기다리는 상태
* 종료(TERMINATED) : 실행을 마친 상태

![b](https://user-images.githubusercontent.com/55661631/105609420-921d7080-5dec-11eb-8530-f001aa5ffa84.png)
출처 : https://wisdom-and-record.tistory.com/48

1. 스레드를 생성하고 start()를 호출하면 바로 실행되는 것이 아니라 실행대기열(큐)에 저장되며 자신의 차례가 올 때까지 기다린다.
2. 자신의 차례가 되면 실행상태가 된다.
3. 할당된 시간이 다되거나 yield() 메소드를 만나면 다시 실행 대기상태가 되고 다음 스레드가 실행된다.
4. 실행 중에 suspend(), sleep(), wait(), join(), I/O block에 의해 일시정지 상태가 될 수 있다.
5. 지정된 정지 시간이 다 되거나 notify(), resume(), interrupt()가 호출되면 일시 정지 상태를 벗어나 실행 대기열에 저장되어 실행을 기다린다.
6. 실행을 모두 마치고나 stop()이 호출되면 스레드는 소멸된다.

<br/>

## **스레드 상태 제어 메소드**

<br/>

## **sleep()**

* **public static void sleep(long millis)** : 지정된 시간동안 스레드를 일시정지 상태로 만든다.(millis, 1/1000초 단위)

* **public static void sleep(long millis, int nanos)** : nanos 10억분의 1초

**주의할 점**

밀리세컨드와 나노세컨드의 시간단위로 세밀하게 값을 지정할 수 있지만 오차가 발생할 수 있다.

sleep()에 의해 일시정지 상태가 된 스레드는 지정된 시간이 다 되거나 interrupt()가 호출되면(interruptException을 발생시킨다) 일시 정지 상태에서 깨어나 실행 대기 상태가 된다.

sleep()을 호출할 때는 항상 try-catch문으로 interruptedException을 예외처리 해줘야 한다.

sleep()은 실행 중인 스레드에 대해서만 작동하며, static으로 선언되어 있기 때문에 참조변수보다는 Thread.sleep()과 같이 호출해야한다.

```java
public class sleep {
    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            System.out.print("<thread1 시작>");
            for(int i=0; i<50; i++){
                System.out.print("1");
            }
            System.out.print("<thread1 종료>");
        });

        Thread thread2 = new Thread(()->{
            System.out.print("<thread2 시작>");
            for(int i=0; i<50; i++){
                System.out.print("2");
            }
            System.out.print("<thread2 종료>");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.sleep(5000);
        } catch (InterruptedException e) {
        }

        System.out.println("<메인 스레드 종료>");
    }
}
```
위 코드는 thread1과 thread2를 생성해 thread1을 sleep() 메서드로 5초간 일시 정지 시킨다. thread1이 5초간 일시 정지되어 가장 늦게 끝날 것 같지만 사실 메인 스레드가 가능 늦게 끝난다.

<br/>

결과화면
```
<thread2 시작>22222222222222222222222222222222222222222222222222<thread1 시작>1111111111<thread2 종료>1111111111111111111111111111111111111111<thread1 종료><메인 스레드 종료>
```

<br/>

## **interrupt()**

**public void interrupt()** : 스레드가 일시 정지 상태에 있을 때 InterruptedException 예외를 발생시키는 역활을 한다. 이것을 이용하면 run() 메소드를 정상 종료시킬 수 있다. 또한, 스레드의 interrupted 상태를 false에서 true로 변경한다. 주목할 점은 스레드가 실행 대기 또는 실행 상태에 있을 때 InterruptedException 예외가 발생하지 않아 interrupt() 메소드 호출은 아무런 의미가 없다.

**public boolean isInterrupted()** : 현재 스레드의 interrupted 상태를 반환한다.

**public static boolean interrupted()** : 현재 스레드의 interrupted 상태를 반환 후, false로 변경한다.

아래 코드는 스레드에서 sleep(1000)으로 1초 동안 지연하도록 하였다. 메인 스레드에서 interrupt() 메소드를 호출하여도 카운트는 종료 되지 않는데 이것은 sleep(1000) 에서 InterruptedException이 발생했기 때문이다. sleep()에 의해 멈춰 있을 때 interrupt() 를 호출하면 InterruptedException이 발생하고 스레드의 Interrupted 상태는 false로 자동 초기화 된다.

```java
public class Interrupt {
    public static void main(String[] args) {
        Thread thread1 = new Thread_1();

        thread1.start();

        try { Thread.sleep(3000); } catch (InterruptedException e) {} //메인 스레드를  3조 일시 정지

        thread1.interrupt(); //interrupt 상태가 true 가 됨
        System.out.println("isInterrupted() : " + thread1.isInterrupted());

    }
}

class Thread_1 extends Thread{
    @Override
    public void run() {
        {
            int i = 10;
            while(i >= 0 && !isInterrupted()){
                System.out.println(i--);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    
                }
            }
            System.out.println("thread1 종료");
        }
    }
}
```

결과화면
```
10
9
8
7
isInterrupted() : false
6
5
4
3
2
1
0
thread1 종료
```

만약 interrupt() 호출 시 카운트다운을 종료하려면 catch 블럭에 interrupt()를 추가하여 스레드의 interrupted 상태를 true로 바꿔야한다.

```java
catch (InterruptedException e) {
    interrupt(); // interrupted 상태를 다시 true 로 바꿔줌
}
```

결과화면
```
10
9
8
thread1 종료
isInterrupted() : true
```

<br/>

## **yield()**

스레드가 처리하는 작업은 반복적인 실행을 위해 for문이나 while 문을 포함하는 경우가 많은데, 가끔 반복분은 어떠한 실행문을 실행하지 않고 무의미한 반복을 하는 경우가 있다. 이것보다는 다른 스레드에게 실행을 양보하고 자신은 실행 대기 상태로 돌아가는 것이 전체 프로그램 성능에 도움이 된다. 이때 사용하는 메소드가 yield()이다.

정리하자면 yield() 메소드를 호출한 스레드는 실행 대기 상태로 돌아가고 동일한 우선순위 또는 높은 우선순위를 갖는 다른 스레드에게 실행 기회를 준다.

아래 코드는 stop의 값을 true로 변경하여 th1 스레드에서 yield() 메소드를 호출하게끔 한다. th1, th2 스레드들의 start() 메소드를 호출하면 두 스레드 모두 실행되는 것을 볼 수 있고 stop 값을 true로 변경하면 th1 스레드에서 yield() 메소드를 호출해 th2 스레드만 실행되는 것을 볼 수 있다.
```java
public class Yield {
    public static void main(String[] args) {
        Th_1 th1 = new Th_1();
        Thread th2 = new Th_2();

        System.out.println("th1 th2 실행");
        th1.start();
        th2.start();
        
        try{ Thread.sleep(3000); }catch (InterruptedException e){}
        System.out.println("th2만 실행");
        th1.stop = true;


    }
}

class Th_1 extends Thread{
    public boolean stop = false;
    @Override
    public void run() {
        while(true){
            if(!stop){
                System.out.println("th1 작업 중");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                }
            }else{
                Thread.yield();
            }
        }
    }
}

class Th_2 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i<10; i++) {
            System.out.println("th2 작업 중");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
```

결과화면

```
th1 th2 실행
th1 작업 중
th2 작업 중
th1 작업 중
th2 작업 중
th1 작업 중
th2 작업 중
th2만 실행
th2 작업 중
th2 작업 중
th2 작업 중
th2 작업 중
th2 작업 중
th2 작업 중
th2 작업 중
```

<br/>

## **join()**

스레드는 다른 스레드와 독립적으로 실행하는 것이 기본이지만 다른 스레드가 종료될 때까지 기다렸다가 실행해야하는 경우가 발생할 수 있다. 예를 들어 계산 작업을 하는 스레드가 모든 계산 작업을 마쳤을 때, 계산 결과값을 받아 이용하는 경우가 이에 해당된다. 이런 경우에 사용되는 메소드가 join()이다.

<br/>

**public void join()**

**public void join(long millis)**

**public void join(long millis, long nanos)**

<br/>

매개변수로 시간을 지정하지 않으면, 해당 스레드가 작업을 모두 마칠 때까지 기다린다.

join 메소드도 sleep 메소드처럼 interrupt()에 의해 대기상태에서 벗어날 수 있으며, join()이 호출되는 부분을 try-catch문으로 감싸서 InterruptedException을 catch 해야한다.

sleep과 다른점은 join()은 특정 스레드에 대해 동작하여 static 메소드가 아니다.

아래 코드는 메인 스레드가 SumThread의 계산 작업을 모두 마칠 때까지 일시 정지 상태에 있다가 계산이 끝나면 결과값을 출력하는 프로그램이다.

```java
public class Join {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        sumThread.start();

        try {
            sumThread.join();
        } catch (InterruptedException e) {
        }
        System.out.println(SumThread.sum);
    }
}

class SumThread extends Thread{
    static int sum = 0;
    @Override
    public void run() {
        while (sum < 100) {
            sum += 1;
        }
    }
}
```

결과화면
```
100
```

만약 메인 스레드가 join 메소드를 사용하지 않았다면 0이 나오게 된다. 그 이유는 SumThread의 계산 작업을 완료하지 않고 출력하기 때문이다.

<br/>

## **suspend(), resume(), stop()**

suspend() : sleep()처럼 스레드를 일시정지한다. 

resume() : suspend()에 의해 일시정지 상태에 있는 스레드를 실행대기 상태로 만든다.

stop() : 호출되는 즉시 스레드가 종료된다.

suspend(), resume(), stop()은 스레드의 실행을 제어하는 가장 손쉬운 방법이지만, suspend()와 stop()이 교착상태(deadlock)를 일으키기 쉽게 작성되어 있으므로 이 메소드들은 모두 '@Deprecated' (사용이 권장되지 않음)되었다.

<br/>

# 💡 10-3 스레드의 우선순위

## **동시성(Concurrency) 또는 병렬성(Parallelism)**

멀티 스레드는 동시성(Concurrency) 또는 병렬성(Parallelism)으로 실행되기 때문이 이 용어에 대해 정확하게 이해해야한다.

**동시성**은 멀티 작업을 위해 하나의 코어에서 멀티 스레드가 번갈아가며 실행하는 성질을 말하고, **병렬성**은 멀티 작업을 위해 멀티 코어에서 개별 스레드를 동시에 실행하는 성질을 말한다.

싱글 코어 CPU를 이용한 멀티 스레드 작업은 병령적으로 실행되는 것처럼 보이지만, 사실은 벌갈아가며 실행하는 동시성 작업이다. 번갈아 실행하는 것이 워낙 빠르다보니 병렬성으로 보일 뿐이다.

![c](https://user-images.githubusercontent.com/55661631/105810683-c19bcb00-5fee-11eb-99e4-6fcb8565eeb4.PNG)

<br/>

## **스레드 스케줄링**

스레드의 개수가 코어의 수보다 많을 경우, 스레드를 어떤 순서에 의해 동시성으로 실행할 것인가를 **스레드 스케줄링**이라고 한다. 스레드 스케줄링에 의해 스레드들은 자신의 run() 메소드를 조금씩 실행시킨다.

그럼 스레드 스케줄링은 어떤 방식이 있을까?

다음과 같이 자바에서의 스레드 스케줄링은 두 가지 방식을 사용한다.

### **우선순위(Priority)** 

우선순위 방식은 우선순위가 높은 스레드가 실행 상태를 더 많이 가지도록 스케줄링하는 것을 말한다. 스레드 우선순위 방식은 스레드 객체에 우선순위 번호를 부여할 수 있기 때문에 개발자가 코드로 제어할 수 있다. 

우선순위 방식에서 우선순위는 1~10까지 부여되는데, 1이 가장 우선순위가 낮고 10이 가장 높다. 우선순위를 부여하지 않으면 기본적으로 5를 할당받는다. 만약 우선 순위를 변경하고 싶으면 Thread.setPriorty() 메소드를 이용하면 된다.

MAX_PRIORITY, NORM_PRIORITY, MIN_PRIORITY는 각 10, 5, 1의 값을 가지고 있다.

아래 코드는 Thread0~8은 우선순위를 1로 주었고 Thread9는 우선순위를 10을 주고 계산이 먼저 끝난 스레드를 출력하는 프로그램이다.

thread9의 계산이 가장 빨리 끝난다.

```java
public class Priority {
    public static void main(String[] args) {
        for(int i =1; i<=10; i++) {
            Thread thread = new Thread(new Calc());
            if(i != 10){
                thread.setPriority(1);
            }else{
                thread.setPriority(10);
            }
            thread.start();
        }
    }
}

class Calc implements Runnable{
    int sum = 0;
    @Override
    public void run() {
        for(int i = 0; i < 10000000; i++){
            sum += 1;
        }
        System.out.println(Thread.currentThread().getName() + "종료");
    }
}
```

결과화면
```
Thread-9종료
Thread-2종료
Thread-4종료
Thread-8종료
Thread-6종료
Thread-3종료
Thread-5종료
Thread-7종료
Thread-0종료
Thread-1종료
```

### **순환 할당(Round-Robin)** 

순환 할당이라 함은, 각자 시간 할당량을 정해서 하나의 스레드를 정해진 시간만큼만 실행하고 다시 다른 스레드를 실행하는 것을 말하는데, 이런 순환 할당 방식은 JVM이 정하기 때문에 코드로 제어할 수는 없다.

<br/>

# 💡 10-4 Main 스레드

자바 애플리케이션은 기본적으로 하나의 메인 스레드를 가진다. 자바 프로그램을 실행하기 위해 Main 스레드는 main() 메소드를 실행한다. main() 메소드는 메인 스레드의 시작점을 선언하는 것이다.

메인 스레드는 자바에서 처음으로 실행되는 스레드이자 모든 스레드는 메인 스레드로 부터 생성된다.

메인 스레드가 종료되더라도 생성된 스레드가 실행 중 이라면 모든 스레드가 작업을 완료하고 종료될 때 까지 프로그램은 종료되지 않는다. 즉, 실행중인 사용자 스레드가 하나도 없다면 프로그램은 종료된다.

![d](https://user-images.githubusercontent.com/55661631/105814071-273e8600-5ff4-11eb-9bd6-09e30a6d78b7.PNG)

출처 : https://m.blog.naver.com/PostView.nhn?blogId=qkrghdud0&logNo=220691381975&proxyReferer=https:%2F%2Fwww.google.com%2F

<br/>

# 💡 10-5 데몬 스레드

주 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드이다. 주 스레드가 모두 종료되면 데몬 스레드는 강제적으로 자동 종료됩니다. 데몬 스레드는 보조역할을 수행하므로 주 스레드가 모두 종료되고 나면 데몬 스레드의 존재는 의미가 없기 때문이다.

이 점을 제외하면 일반 스레드와 크게 차이가 없다.

데몬 스레드의 적용 예는 워드프로세서의 자동 저장, 미디어 플레이어의 동영상 및 음악 재생, 가비지 컬렉터 등이 있다.

<br/>


## **데몬 스레드와 관련된 메소드**

isDaemon() : 스레드가 데몬인지 확인한다. 데몬 스레드면 true, 아니면 false

setDaemon() : 스레드를 데몬 스레드로 또는 주 스레드로 변경합니다. true면 데몬 스레드가 된다.

<br/>

아래의 코드는 데몬 스레드를 지정하고 메인 스레드가 종료되면 같이 종료되는지 확인하는 프로그램입니다.

```java
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new DThread();
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        System.out.println("메인 스레드 종료");
    }
}

class DThread extends Thread{
    @Override
    public void run() {
        while(true){
            System.out.println("데몬 스레드 작업 중");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
```

결과화면
```
데몬 스레드 작업 중
데몬 스레드 작업 중
데몬 스레드 작업 중
데몬 스레드 작업 중
데몬 스레드 작업 중
메인 스레드 종료
```

<br/>

# 💡 10-6 동기화

싱글 스레드 프로그램은 한 개의 스레드가 객체를 독차지해서 사용하면 되지만, 멀티 스레드 프로그램은 스레드들이 객체를 공유해서 작업해야 하는 경우가 있다. 이 경우에는 여러 스레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에 엉터리 값을 이용할 수 있다.

멀티 스레드 프로그램에서 동기화가 이뤄지지 않는다면 다음과 같은 오류가 생길 수 있다.

예금이 5만원인 통장에 두 스레드가 접근해 10만원, 5만원을 입금해서 20만원이 됐어야 하는데 최종적으로 예금은 10만원이 되는 일이 발생하였다.

이렇게 멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 임계 영역(critical section)이라고 한다. 예시에서 예금을 확인하고 입금한 뒤 저장하는 부분이 임계 영역이다.

<br/>

## **임계 영역 해결 조건**

* 상호 배제(mutual exclusion) : 한 스레드가 임계 영역에 들어가면 다른 스레드는 임계 영역에 들어갈 수 없다. 이것이 지켜지지 않으면 임계 영역을 설정한 의미가 없다.

* 한정 대기(bounded waiting) : 한 스레드가 계속 자원을 사용하고 있어 다른 스레드가 사용하지 못한 채 계속 기다리면 안된다. 어떤 스레드도 무한 대기(infinite postpone)하지 않아야 한다. 즉 특정 스레드가 임계 영역에 진입하지 못하면 안된다.

* 진행의 융통성(progress flexibility) : 한 스레드가 다른 스레드의 작업을 방해해서는 안된다.

<br/>

## **해결 방법**

임계 영역(critical section)과 잠금(lock)의 개념을 활용해서 해결 조건을 만족할 수 있다.

공유 자원을 사용하는 코드 영역을 임계 영역으로 지정해놓고, 공유 자원(객체)가 가지고 있는 lock을 획득한 단 하나의 스레드만 이 영역 내의 코드를 수행할 수 있게한다. 해당 스레드가 임계 영역 내의 모든 코드를 수행하고 벗어나면 lock을 반납하여 다른 스레드가 lock을 획득할 수 있게 한다.

이처럼 한 스레드가 진행 중인 작업을 다른 스레드가 간섭하지 못하도록 막는 과정을 **스레드의 동기화(synchronization)** 라고 한다.

<br/>

## **자바에서의 동기화 방법**

아래의 코드는 동기화하지 않은 코드이다. 실행 결과를 보면 잔고가 10000원인 계좌에서 user1은 3000원을 user2는 5000원을 순서대로 출금했는데, user1에게 보여주는 계좌 잔액이 7000원이 아닌 2000원이다. 그 이유는 잔액을 보여주기전에 user2가 5000원을 출금했기 때문이다.

```java
public class Bank {
    public static void main(String[] args) {
        Account account = new Account();
        User1 user1 = new User1(account);
        User2 user2 = new User2(account);

        user1.start();
        user2.start();
    }
}

class Account {
    private int balance = 10000;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int money){
        balance -= money;
        try { Thread.sleep(2000); } catch (InterruptedException e) {} //통장 예금을 확인하는데 2초가 걸린다고 가정
        System.out.println(Thread.currentThread().getName() + " : " + getBalance());
    }
}

class User1 extends Thread{
    Account account;

    public User1(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.withdraw(3000);
    }
}
class User2 extends Thread{
    Account account;

    public User2(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.withdraw(5000);
    }
}
```

결과화면
```
Thread-0 2000
Thread-1 2000
```

<br/>

### **1. synchronized를 이용한 동기화**

* 메소드 전체를 임계 영역으로 지정
    * 메소드가 호출된 시점부터 lock을 얻어서 작업하고 메소드가 종료되면 lock을 반환한다.
        ```java
        public synchronized void method(){
            임계 영역; //단 하나의 스레드만 실행
        }
        ```
    * 예시 코드 해결방법
        ```java
        public synchronized void withdraw(int money){
                balance -= money;
                try { Thread.sleep(2000); } catch (InterruptedException e) {} //통장 예금을 확인하는데 2초가 걸린다고 가정
                System.out.println(Thread.currentThread().getName() + " : " + getBalance());
        }
        ```
        결과화면

        ```
        Thread-0 : 7000
        Thread-1 : 2000
        ```
* 특정한 영역을 임계 영역으로 지정
    * 참조변수는 lock을 걸고자하는 객체를 지정해주며 이 블럭의 영역 안으로 들어가면서 부터 스레드는 지정된 객체의 lock을 얻게 되고, 이 블럭을 벗어나면 lock을 반납한다.
        ```java
        public void method(){
            //여러 스레드가 실행 가능
            synchronized(공유객체){
                임계 영역; //단 하나의 스레드만 실행
            }
            //여러 스레드가 실행 가능 영역
        }
        ```
    * 예시 코드 해결방법
        ```java
        public void withdraw(int money){
            synchronized (this){
                balance -= money;
                try { Thread.sleep(2000); } catch (InterruptedException e) {} //통장 예금을 확인하는데 2초가 걸린다고 가정
                System.out.println(Thread.currentThread().getName() + " : " + getBalance());
            }
        }
        ```
        결과화면

        ```
        Thread-0 : 7000
        Thread-1 : 2000
        ```

두 가지 방법 모두 lock의 획득과 반납이 자동적으로 이루어지므로 우리가 해야 할 일은 그저 임계 영역만 지정해주면된다.

임계 영역은 멀티 스레드 프로그램의 성능을 좌우하기 때문에 가능하면 메소드 전체에 락을 거는 것보다 synchronized 블럭으로 임계구역을 최소화해서 보다 효율적인 프로그램이 되도록 노력해야한다.

<br/>

### **2. wait()와 notify()**

synchronized로 동기화하면 공유 자원을 보호할 수 있지만 특정 스레드가 객체의 락을 가진 상태로 오랜시간을 보내지 않게 하는것도 중요하다.

만약 계좌에 입금할 돈이 부족해서 한 스레드가 락을 보유한 채로 돈이 입금될 때까지 오랜 시간을 보낸다면, 다른 스레드들은 모두 해당 객체의 락을 기다리느라 다른 작업들도 원활히 진행되지 않을 것이다.

이런 상황을 wait()와 notify()를 이용해 해결할 수 있다.

동기화된 임계 영역의 코드를 수행하다가 작업을 더 이상 진행할 상황이 아니라면, wait()를 호출하여 스레드가 락을 반납하고 기다리게 한다. 이때 다른 스레드가 락을 얻어 해당 객체에 대한 작업을 수행할 수 있게 된다. 이후 작업을 다시 진행할 수 있는 상황이 되면 notify()를 호출해서, 작업을 중단했던 스레드가 다시 락을 얻어 작업을 진행할 수 있다.

**wait(), notify(), notifyAll()**
- Object에 정의 되어 있는 메소드이다.
- 동기화 블록(synchronized블록)내에서만 사용할 수 있다. 
- 보다 효율적인 동기화를 가능하게 한다.

wait() : 쓰레드가 락을 반납하고 기다리게한다.

notify() : 객체의 대기실에서 대기중인 모든 쓰레드 중 임의의 쓰레드에게 lock을 얻을 수 있는 상태로 바꿔준다.

notifyAll() : 기다리고 있는 모든 객체에게 통지하여 lock을 얻을 수 있는 상태로 바꿔줍니다. notifyAll()이 호출된 객체의 waiting pool에 대기중인 쓰레드만 해당된다.

아래의 코드는 두 스레드의 작업을 wait(), notify() 메소드를 사용해 교대로 호출하는 프로그램이다.

```java
public class Company {
    public static void main(String[] args) {
        WorkObject workObject = new WorkObject();
        Worker1 worker1 = new Worker1(workObject);
        Worker2 worker2 = new Worker2(workObject);

        worker1.start();
        worker2.start();
    }
}

class WorkObject {
    public synchronized void method1(){
        System.out.println("Worker1 작업 중");
        notify(); //worker2를 실행 대기 상태로 만든다
        try {
            wait(); //worker1을 일시 정지 상태로 만든다
        } catch (InterruptedException e) {
        }
    }
    public synchronized void method2(){
        System.out.println("Worker2 작업 중");
        notify(); //worker1를 실행 대기 상태로 만든다
        try {
            wait(); //worker2을 일시 정지 상태로 만든다
        } catch (InterruptedException e) {
        }
    }
}

class Worker1 extends Thread{
    WorkObject workObject;

    public Worker1(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 500; i ++){
            workObject.method1();
        }
    }
}

class Worker2 extends Thread{
    WorkObject workObject;

    public Worker2(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 500; i ++){
            workObject.method2();
        }
    }
}
```

결과화면

```
Worker1 작업 중
Worker2 작업 중
Worker1 작업 중
Worker2 작업 중
Worker1 작업 중
Worker2 작업 중
Worker1 작업 중
Worker2 작업 중
...
```

만약 notify()와 wait()로 순서를 임의로 정하지 않았다면 일정 시간동안 method1과 method2가 일정 시간동안 번갈아가면서 출력될것이다.