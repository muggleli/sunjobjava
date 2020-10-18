package com.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Dep;
import com.pojo.Emp;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test extends TestCase {

    public void _testFindDep() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Dep dep = sqlSession.selectOne("com.pojo.Dep.findbyid",1);


    }

    public void _testFindDep2() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Dep dep = sqlSession.selectOne("com.pojo.Dep.findbyid2",1);
        System.out.println(dep.getDepid()+"\t"+dep.getDepname());
        Set<Emp> emps = dep.getEmps();
        for (Emp emp : emps) {
            System.out.println(emp.getEmpid()+"\t"+emp.getEmpname());
        }

    }

    public void testFindEmp() throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Emp emp = sqlSession.selectOne("com.pojo.Emp.findbyid" , 1);
        System.out.println(emp.getEmpid()+"\t"+emp.getEmpname());
        Dep dep = emp.getDep();
        System.out.println(dep.getDepid()+"\t"+dep.getDepname());

    }

    public void _testFindEmp2() throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Emp emp = sqlSession.selectOne("com.pojo.Emp.findbyid2" , 1);
        System.out.println(emp.getEmpid()+"\t"+emp.getEmpname());
        Dep dep = emp.getDep();
        System.out.println(dep.getDepid()+"\t"+dep.getDepname());
    }

    public void  testFenye() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        PageHelper.startPage(1,2,true);
        List<Emp> list = sqlSession.selectList("com.pojo.Emp.fenye");
        PageInfo<Emp> pageInfo = new PageInfo<Emp>(list);
        for (Emp emp : list) {
            System.out.println(emp.getEmpid()+"\t" + emp.getEmpname());
        }

        System.out.println(pageInfo.getTotal()+"\t"+pageInfo.getPages()+"\t"+pageInfo.getPageNum());

    }


}
