package com.test;

import com.pojo.Dep;
import com.pojo.Emp;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Test extends TestCase {
public void _testAdd() throws IOException {
    Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

    Dep dep = new Dep();
    dep.setDepname("aaaa");
    Dep dep1 = new Dep();
    dep1.setDepname("bbbb");
    Dep dep2 = new Dep();
    dep2.setDepname("cccc");

    sqlSession.insert("com.pojo.Dep.insert",dep);
    sqlSession.insert("com.pojo.Dep.insert",dep1);
    sqlSession.insert("com.pojo.Dep.insert",dep2);

    sqlSession.commit();
    sqlSession.close();
}
public void _testUpdate() throws IOException {
    Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    Emp emp = new Emp();
    emp.setEmpid("5");
    emp.setEmpname("小俊俊");
    emp.setGender(1);

    int n = sqlSession.update("com.pojo.Emp.update",emp);
    sqlSession.commit();
    sqlSession.close();
}

public void _testFindAll() throws IOException {

    Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    Emp emp = new Emp();
    emp.setEmpid("5");


    List<Emp> list = sqlSession.selectList("com.pojo.Emp.findby",emp);
    for (Emp e : list) {
        System.out.println(e.getEmpid()+"\t"+e.getEmpname()+"\t"+e.getGender());
    }

}

public void testBatch() throws IOException {
    Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession sqlSession = sqlSessionFactory.openSession();

    Emp emp = new Emp();
    emp.setEmpid("8");
    emp.setEmpname("萨满");
    emp.setGender(1);

    Emp emp2 = new Emp();
    emp2.setEmpid("9");
    emp2.setEmpname("缓急");
    emp2.setGender(1);

    Emp emp3 = new Emp();
    emp3.setEmpid("10");
    emp3.setEmpname("家居");
    emp3.setGender(1);

    List<Emp> list = new ArrayList<Emp>();
    list.add(emp);
    list.add(emp2);
    list.add(emp3);

    int n = sqlSession.insert("com.pojo.Emp.batchinsert" , list);
    sqlSession.commit();
    sqlSession.close();
}
}
