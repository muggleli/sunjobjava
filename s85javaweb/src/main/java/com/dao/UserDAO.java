package com.dao;

import com.pojo.UserInfo;
import com.util.DBUtil;

import java.util.List;

public class UserDAO {
    public  int regist(String username,String password){
        String sql = "insert into userinfo(username,password) value(?,?) ";
        int n = DBUtil.zsg(sql,username,password);
        return  n ;
    }

    public UserInfo login(String username,String password){
        String sql ="select username , password from userinfo where username =? and password =? ";
        List<UserInfo> list = DBUtil.query(sql,UserInfo.class,username,password);
        if (list.size()>0){
            return  list.get(0);
        }
        return  null;
    }

}
