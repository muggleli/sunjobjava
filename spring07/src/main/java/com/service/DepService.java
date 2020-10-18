package com.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mapper.DepMapper;
import com.pojo.Dep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepService {

    @Autowired
    private DepMapper depMapper ;

    public IPage<Dep> fenye(IPage page , QueryWrapper queryWrapper){
        IPage<Dep> iPage = depMapper.selectPage(page,queryWrapper);
        return iPage;
    }

}
