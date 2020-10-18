package com.test;

import pojo.Student;

public class Test5 {
    public static void main(String[] args) {
        Class c = Student.class;
        Class[] clazz = c.getInterfaces();
        for (Class clazzs : clazz) {
            System.out.println(clazzs.getName());
        }
        System.out.println(clazz.length);
    }
}
