package com.dao;

import com.pojo.EmpAndDep;
import com.util.DBUtil;

import java.util.List;

public class EmpAndDepDao {
    public List<EmpAndDep> findall(){
        String sql = "select username , password from userinfo ";
        List<EmpAndDep> list = DBUtil.query(sql, EmpAndDep.class);
        return list ;
    }
}
