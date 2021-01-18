import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        method();
    }

    static void method(){
        throw new RuntimeException();
    }
}


//    static void method() throws IOException {
//        throw new IOException();
//    }