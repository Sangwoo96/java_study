import java.util.EnumSet;

enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE

}

public class EnumSetDemo {
    public static void main(String[] args) {
        EnumSet<Color> set1, set2, set3, set4, set5;

        set1 = EnumSet.allOf(Color.class);
        set2 = EnumSet.of(Color.RED, Color.GREEN, Color.BLUE);
        set3 = EnumSet.complementOf(set2);
        set4 = EnumSet.range(Color.YELLOW, Color.BLACK);

        set5 = EnumSet.noneOf(Color.class);
        set5.add(Color.BLACK);
        set5.add(Color.BLUE);
        set5.remove(Color.BLUE);

        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);
        System.out.println("set3 = " + set3);
        System.out.println("set4 = " + set4);
        System.out.println("set5 = " + set5);
        System.out.println(set5.contains(Color.BLACK));
    }
}
