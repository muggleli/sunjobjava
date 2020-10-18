package com.test;

import pojo.Student;

import java.lang.reflect.Field;

public class Test2   {
    public static void main(String[] args) {

        Student student = new Student();
        Class c = student.getClass();

        Field[] fields = c.getFields();//得到自己以及父类的public属性
        System.out.println(fields.length);
        Field[] fields1 = c.getDeclaredFields();//得到自己声明的属性  和父类无关
        System.out.println(fields1.length);
    }
}
