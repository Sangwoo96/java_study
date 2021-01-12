import java.util.ArrayList;
import java.util.List;

public class ArrayIndexOutOfBoundsExceptionTest {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        System.out.println(list.get(0));
    }
}
