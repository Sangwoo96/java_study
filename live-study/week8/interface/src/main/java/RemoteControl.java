public interface RemoteControl {
    void turnOn();
    void turnOff();
    default void defaultMethod(){
        System.out.println("defaultMethod");
    }
    private void privateMethod() {
        System.out.println("privateMethod");
    }

    private static void privateStaticMethod() {
        System.out.println("privateStaticMethod");
    }
}
