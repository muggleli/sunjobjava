package com.test;

import com.ioc.ApplicationContext;
import com.pojo.Student;

public class Test2 {
    public static void main(String[] args) {
        Student student = (Student) ApplicationContext.getInstance("student");
        student.setStuid(1);
    }
}
