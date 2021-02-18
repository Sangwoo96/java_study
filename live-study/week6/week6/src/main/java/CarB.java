public class CarB extends Car{

    public CarB(int wheel, String color) {
        super(wheel, color);
    }

    @Override
    public void drive() {
        System.out.println("[CarB] --- drive");
    }

    @Override
    public void stop() {
        System.out.println("[CarB] --- stop");
    }
}
