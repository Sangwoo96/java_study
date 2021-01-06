public class Main {
    public static void main(String[] args){
        Car car1 = new Car();
        Car car2 = new Car("company", "model", 10);
        car1.getSpeed();
        car2.getSpeed();
    }
}
