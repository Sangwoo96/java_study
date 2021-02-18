public class Car extends Test{
    public int wheel;
    public String color;
    public static String test;

    public Car(int wheel, String color) {
        this.wheel = wheel;
        this.color = color;
    }

    public void drive(){
        System.out.println("[Car] --- drive");
    }

    public void stop() {
        System.out.println("[Car] --- stop");
    }
}
