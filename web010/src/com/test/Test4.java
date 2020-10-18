package com.test;

import pojo.Student;

public class Test4 {
    public static void main(String[] args) {
        Class c = Student.class;
        Class clazz = c.getSuperclass();
        System.out.println(clazz.getName());
    }
}
