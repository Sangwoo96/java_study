public class Teacher extends School{
    String teacherName;
    int age;

    Teacher(School school, String teacherName, int age) {
        super(school.schoolName, school.location);
        this.teacherName = teacherName;
        this. age = age;
    }

    @Override
    public void method1() {
        System.out.println("Teacher의 method1입니다");
    }
}