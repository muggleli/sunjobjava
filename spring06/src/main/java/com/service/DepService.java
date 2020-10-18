package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mapper.DepMapper;
import com.pojo.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepService {

    @Autowired
    private DepMapper depMapper ;

    public IPage<Dep> findall(){
        IPage<Dep> iPage = new Page<>(2,2);
        iPage =  depMapper.selectPage(iPage , null);
        return iPage ;
    }

}
