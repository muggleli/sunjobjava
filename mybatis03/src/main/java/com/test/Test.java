package com.test;

import com.pojo.Dep;
import com.pojo.Emp;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Test extends TestCase {
      public void _testAdd() throws IOException {
          Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
          SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
          SqlSession sqlSession = sqlSessionFactory.openSession();

          Dep dep = new Dep();
          dep.setDepname("国防部");

          int n = sqlSession.insert("com.pojo.Dep.insert" , dep);

          Emp emp = new Emp();
          emp.setEmpname("小鹏");
          emp.setDid(dep.getDepid());

          int m = sqlSession.insert("com.pojo.Emp.insert" , emp);

          sqlSession.commit();
          sqlSession.close();
      }

    public void testAddEmp() throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Emp emp = new Emp();
        emp.setEmpname("俊哥");

        int n = sqlSession.insert("com.pojo.Emp.insertuuid" , emp);
        sqlSession.commit();
        sqlSession.close();

        System.out.println(emp.getEmpid());

    }
}
