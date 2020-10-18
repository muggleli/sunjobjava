package com.mapper;

import com.pojo.Dep;

import java.util.List;

public interface DepMapper {
    public List<Dep> findall();
    public int delById(String depid);
}
