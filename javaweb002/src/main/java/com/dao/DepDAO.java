package com.dao;

import com.pojo.Dep;
import com.util.DBUtil;

import java.util.List;

public class DepDAO {
    public List<Dep> findall(){
        String sql = "select depid , depname from dep";
        List<Dep> list = DBUtil.query( sql ,Dep.class );
        return list ;
    }
}
