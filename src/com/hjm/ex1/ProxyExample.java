package com.hjm.ex1;
import java.lang.reflect.Proxy;

/**
 * @Author: hjm
 * @Date: 2025/02/28/14:17
 * @Description:
 */

public class ProxyExample {
    public static void main(String[] args) {
        // 创建Student对象
        Student student = new Student(1, "Liming", 21);

        // 创建InvocationHandler对象
        StudentInvocationHandler handler = new StudentInvocationHandler(student);

        // 创建代理对象
        IStudent proxyStudent = (IStudent) Proxy.newProxyInstance(
                IStudent.class.getClassLoader(),
                new Class<?>[]{IStudent.class},
                handler
        );

        // 调用代理对象的方法
        proxyStudent.doExercise("JavaEE");
    }
}
