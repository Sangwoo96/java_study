import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4); //스레드개수 4개

        for(int i=0; i<10; i++){

            //작업 정의
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 스레드 총 개수 및 작업 스레드 이름 출력
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();  // poolSize 총 스레드 개수
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[총 스레드 개수 : " + poolSize + "] 작업 스레드 이름 : " + threadName);

                }
            };

            //작업 처리 요청
            executorService.submit(runnable);

            Thread.sleep(10);
        }
        
        //스레드풀 종료
        executorService.shutdown();
    }
}
