package com.test;

import pojo.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //获取类的class 对象
        Class c = Student.class;

        //通过class对象获取属性
       Field field = c.getDeclaredField("studio");
        System.out.println(field.getType());

        //还可以给属性赋值
        Student student = new Student();
        field.setAccessible(true);
        field.set(student,1);
        System.out.println(Modifier.toString(student.getStudio()));
    }
}
