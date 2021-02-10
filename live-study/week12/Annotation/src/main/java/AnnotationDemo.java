
public class AnnotationDemo {

    @AnnotationName("*")
    public static void method1() {
        System.out.println("method1");
    }

    @AnnotationName("**")
    public static void method2() {
        System.out.println("method2");
    }

    @AnnotationName("***")
    public static void method3() {
        System.out.println("method3");
    }
}
