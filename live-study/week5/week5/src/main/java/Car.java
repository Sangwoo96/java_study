public class Car {
    //필드
    String company;
    String model;
    int speed;

    //디폴트 생성자
    Car() { }

    //파라미터를 가진 생성자
    Car(String company, String model, int speed){ }

    //생성자 내에서 다른 생성자 호출
    Car(String model){
        this("company", model, 10);
    }

    //차량의 speed를 가져오는 메소드
    int getSpeed() { return speed; }
}

