package com.test;

import com.mapper.DepMapper;
import com.pojo.Dep;

import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test extends TestCase {
    public void _testFindAll() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSessionFactory.getConfiguration().addMapper(DepMapper.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DepMapper depMapper = sqlSession.getMapper(DepMapper.class);
        System.out.println(depMapper.getClass());
        List<Dep> list = depMapper.findall();
        for (Dep dep: list) {
            System.out.println(dep.getDepid());
            System.out.println(dep.getDepname());
        }
    }

    public void _testFindById() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSessionFactory.getConfiguration().addMapper(DepMapper.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DepMapper depMapper = sqlSession.getMapper(DepMapper.class);
        Dep dep = depMapper.findbyid(1);
        System.out.println(dep.getDepid()+"\t" + dep.getDepname());

    }

    public void testFindBy() throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSessionFactory.getConfiguration().addMapper(DepMapper.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DepMapper depMapper = sqlSession.getMapper(DepMapper.class);
        Dep dep = depMapper.findby(1 , "管理");
        System.out.println(dep.getDepid()+"\t" + dep.getDepname());


    }
}
