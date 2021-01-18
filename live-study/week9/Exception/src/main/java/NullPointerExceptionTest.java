import java.io.FileNotFoundException;

public class NullPointerExceptionTest {
     public static void main(String[] args){
         try{
             int a = 100/0;
         }catch (RuntimeException | ArithmeticException e){
             System.out.println("예외 발생");
         }
     }

    NoSuchMethodException
}
