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