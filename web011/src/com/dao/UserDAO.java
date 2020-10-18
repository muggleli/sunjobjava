package com.dao;

import com.pojo.UserInfo;
import com.util.DBUtil;

import java.util.List;

public class UserDAO {
    //查找所有用户
    public static List<UserInfo> findall() {
        String sql = "select username , password from userinfo ";
        List<UserInfo> list = DBUtil.query(sql, UserInfo.class);
        return list;
    }

    //查找用户量
    public double getCount(){
        String sql = "select count(*) from userinfo ";
        double d = DBUtil.uniqueQuery(sql);
        return d ;
    }

    public static void main(String[] args) {
        List<UserInfo> list = UserDAO.findall();
        for (int i = )
    }
}
