package com.test;

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
    public  void testFindALL() throws IOException {
        //读取总的配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //创建session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //得到session
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Dep> list = sqlSession.selectList("com.pojo.Dep.findall");

        for (Dep dep :list){
            System.out.println(dep.getDepid()+"\t"+dep.getDepname());
        }

    }
}
