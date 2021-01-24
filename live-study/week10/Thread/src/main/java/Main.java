public class Main {
    public static void main(String[] args) {

        //Thread 클래스를 상속받은 Task1 객체를 매개값으로 쓰레드 생성자 호출
        Thread thread1 = new Task1();
        
        //Runnable 인터페이스를 구현한 Task2 객체를 매개값으로 쓰레드 생성자를 호출
        Thread thread2 = new Thread(new Task2());
        
        //쓰레드 생성자를 호출할 때 Runnable 익명 객체를 매개값으로 사용
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                //쓰레드가 실행할 코드
                for(int i = 0; i<100; i++){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        //쓰레드 생성자를 호출할 때 람다식을 매개값으로 사용
        Thread thread4 = new Thread(()->{
            //쓰레드가 실행할 코드
            for(int i = 0; i<100; i++){
                System.out.println(Thread.currentThread().getName());
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
            System.out.println(Thread.currentThread().getName());
        }
    }


}

class Task2 implements Runnable{
    @Override
    public void run() {
        //쓰레드가 실행할 코드
        for(int i = 0; i<100; i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}



