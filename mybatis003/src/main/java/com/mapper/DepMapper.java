package com.mapper;

import com.pojo.Dep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepMapper {

@Select("select depid,depname from dep")
public List<Dep> findall();

@Select("select depid ,depname from dep where depid = #{depid}")
public Dep findbyid(int depid);

@Select("select depid ,depname from dep where depid = #{depid} and depname = #{depname}")
public Dep findby(@Param("depid") int depid , @Param("depname") String depname);

}
