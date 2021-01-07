public class Main {
    public static void main(String[] args){
        School school = new School("신사초등학교", "서울");
        School teacher = new Teacher(school, "홍길동", 30);
        Student student = new Student(school, "김철수", 9);

        school.method1();
        teacher.method1(); //동적 메소드 디스패치
        student.method1(); //정적 메소드 디스패치
    }
}
