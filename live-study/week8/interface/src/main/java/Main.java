public class Main {
    public static void main(String[] args){
        RemoteControl tv = new Television();
        RemoteControl audio = new Audio();
        RemoteControl rc = new RemoteControl(){
            @Override
            public void turnOn() {
                //실행문
            }

            @Override
            public void turnOff() {
                //실행문
            }
        };

        tv.turnOn();
        tv.turnOff();
        tv.defaultMethod();
        audio.turnOn();
        audio.turnOff();
        tv.defaultMethod();

        //상위 인터페이스 -> methodA() 만 사용 가능
        InterfaceA a = new Class();
        //하위 인터페이스 -> methodA(), methodB() 사용 가능
        InterfaceB b = new Class();


    }
}
