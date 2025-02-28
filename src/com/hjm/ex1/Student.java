package com.hjm.ex1;

/**
 * @Author: hjm
 * @Date: 2025/02/28/14:05
 * @Description:
 */
public class Student implements IStudent{
    private int id;
    private String name;
    private int age;

    // 无参构造函数
    public Student() {
    }

    // 有参构造函数
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter和Setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void doExercise(String courseName) {
        System.out.println(courseName);
    }
}
