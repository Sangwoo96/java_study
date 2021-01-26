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
