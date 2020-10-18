package com.test;

import com.pojo.Dep;
import com.service.DepService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        DepService depService = (DepService) applicationContext.getBean("depService");
        List<Dep> list =  depService.findall();
        for (Dep dep : list) {
            System.out.println(dep.getDepid()+"/t"+dep.getDepname());
        }
    }
}
