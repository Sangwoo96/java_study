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
