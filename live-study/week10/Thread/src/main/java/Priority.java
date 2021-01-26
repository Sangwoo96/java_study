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