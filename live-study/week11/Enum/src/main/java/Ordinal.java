enum Fruit{
    KIWI, APPLE, BANANA, ORANGE;
}

public class Ordinal {
    public static void main(String[] args) {
        Fruit[] fruits = Fruit.values();

        for(var i : fruits){
            System.out.println(i.name());
        }

        Fruit apple = Fruit.APPLE;
        Fruit banana = Fruit.BANANA;
        Fruit orange = Fruit.ORANGE;

        System.out.println(apple.ordinal());
        System.out.println(banana.ordinal());
        System.out.println(orange.ordinal());

        if(apple.ordinal() == 0){
            System.out.println("사과가 맞습니다.");
        }else if(apple.ordinal() != 0){
            System.out.println("사과가 아닙니다.");
        }
    }
}
