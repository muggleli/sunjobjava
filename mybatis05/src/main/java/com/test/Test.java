package com.test;

import com.pojo.Dep;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Test extends TestCase {
    public void _testSelectIn() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);


        List<Dep> deps =  sqlSession.selectList("com.pojo.Dep.selectin",list);

        for (Dep dep : deps){
            System.out.println(dep.getDepid()+"\t"+dep.getDepname());
        }
    }

    public  void testCache() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();


       Dep dep =  sqlSession.selectOne("com.pojo.Dep.findbyid",1);
        System.out.println(dep.getDepid()+"\t"+dep.getDepname());

        sqlSession.commit();
        sqlSession.close();

    }
}
