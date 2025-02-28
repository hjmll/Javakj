package com.hjm.ex1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: hjm
 * @Date: 2025/02/28/14:09
 * @Description: 反射构建student对象
 */
public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException {
        try{
            Class<?> studentClass = Class.forName("com.hjm.ex1.Student");

            // 获取有参构造函数
            Constructor<?> constructor = studentClass.getConstructor(int.class, String.class, int.class);

            // 使用构造函数创建Student对象
            Object student = constructor.newInstance(1, "Liming", 21);

            System.out.println("反射创建的Student对象: " + student);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
