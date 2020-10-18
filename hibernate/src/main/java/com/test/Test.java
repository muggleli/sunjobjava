package com.test;


import com.pojo.Dep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test  {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session  = sessionFactory.openSession();

        Dep dep =new Dep();
        dep.setDepid(5);
        dep.setDepname("装甲");

        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(dep);
        transaction.commit();
        session.close();
    }
}
