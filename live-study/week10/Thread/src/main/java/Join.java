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