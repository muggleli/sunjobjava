package com.service;

import com.mapper.DepMapper;
import com.pojo.Dep;

import java.util.List;

public class DepService {
    public DepMapper depMapper ;

    public DepMapper getDepMapper() {
        return depMapper;
    }

    public void setDepMapper(DepMapper depMapper) {
        this.depMapper = depMapper;
    }

    public List<Dep> findall(){
        List<Dep> list = depMapper.findall();
        return list;
    }

    public int add(Dep dep){
        int n = depMapper.add(dep);
        return  n ;
    }
}
