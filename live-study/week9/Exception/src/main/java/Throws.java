public class Throws {
    static void method() throws ClassNotFoundException{
        Class clazz = Class.forName("java.lang.없는클래스");
    }
}
