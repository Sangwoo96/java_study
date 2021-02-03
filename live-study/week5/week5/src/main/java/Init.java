public class Init {
    int num1;
    static int num2;

    static {
        System.out.println("클래스 초기화 블록");
        num2 = 100;
    }

    {
        System.out.println("인스턴스 초기화 블록");
        num1 = 10;
    }

    public Init(int num1) {
        System.out.println("생성자 블록");
        this.num1 = num1;
    }

    public static void main(String[] args) {
        Init init = new Init(1000);
        System.out.println("num1 : " + init.num1);
        System.out.println("num2 : " + Init.num2);
    }
}
