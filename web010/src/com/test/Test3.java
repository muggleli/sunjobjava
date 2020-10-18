package com.test;

import pojo.Student;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test3 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        Class c = Class.forName("pojo.Student");

        Method method = c.getDeclaredMethod("setStudio", int.class);
        //通过反射可以获取类的实例

        Student student = (Student) c.newInstance();
        //student.setStuid(1);//调用方法
        method.invoke(student, 1);

        System.out.println(student.getStudio());

        Method[] methods = c.getMethods();
        for (Method m : methods) {
            System.out.println(m.getName() + "\t" + m.getReturnType() + "\t" + m.getModifiers() + "\t" + m.getParameterTypes());
        }

        Method[] methods1 = c.getDeclaredMethods();
    }
}
