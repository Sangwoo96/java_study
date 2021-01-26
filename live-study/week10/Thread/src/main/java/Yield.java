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
