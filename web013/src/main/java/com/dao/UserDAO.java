package com.dao;

import com.pojo.UserInfo;
import com.util.DBUtil;

import java.util.List;

public class UserDAO {
    public static List<UserInfo> findall(){
        String sql = "Select username ,password from userinfo";
        List<UserInfo> list = DBUtil.query(sql,UserInfo.class);
        return list;
    }
}
