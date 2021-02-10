import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        Method[] methods = AnnotationDemo.class.getDeclaredMethods();

        for (Method method : methods) {
            if(method.isAnnotationPresent(AnnotationName.class)){
                System.out.println("[" + method.getName() + "]");

                AnnotationName annotationName = method.getAnnotation(AnnotationName.class);
                System.out.println("value : "  + annotationName.value());
            }

            System.out.println();
        }
    }
}
