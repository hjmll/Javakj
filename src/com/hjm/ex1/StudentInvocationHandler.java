package com.hjm.ex1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: hjm
 * @Date: 2025/02/28/14:12
 * @Description:
 */
public class StudentInvocationHandler implements InvocationHandler {
    private final Object target;

    public StudentInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " method is invoked");
        return method.invoke(target, args);
    }
}
