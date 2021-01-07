public class Student extends School{
    String studentName;
    int age;

    Student(School school, String studentName, int age) {
        super(school.schoolName, school.location);
        this.studentName = studentName;
        this. age = age;
    }

    @Override
    public void method1() {
        System.out.println("Student의 method1입니다");
    }
}
