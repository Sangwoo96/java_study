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
                    //InterruptedException 발생 시 자동으로 interrupted 상태가 false 로 초기화
                    interrupt(); // interrupted 상태를 다시 true 로 바꿔줌
                }
            }
            System.out.println("thread1 종료");
        }
    }
}
