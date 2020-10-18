package com.service;

import com.mapper.DepMapper;
import com.pojo.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepService {

    @Autowired
    private DepMapper depMapper ;

    public List<Dep> findall(){
        List<Dep> list = depMapper.findall();
        return list ;
    }

    public int delbyid(String depid ){
        int n = depMapper.delById(depid);
        return n ;
    }





}
