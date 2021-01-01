package com.company;

public class Main {
    public static void main(String[] args) {
        School school = new School();
        Student student = new Student();
        Child child = new Child();

        System.out.println(school instanceof School);
        System.out.println(student instanceof School);
        System.out.println(child instanceof Parent);
        System.out.println(student instanceof Parent);
    }
}

class School {}
interface Parent {}
class Student extends School {}
class Child implements Parent {}
