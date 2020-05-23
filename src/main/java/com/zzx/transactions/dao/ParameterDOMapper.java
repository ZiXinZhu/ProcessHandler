package com.zzx.transactions.dao;

import com.zzx.transactions.entity.ParameterDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ParameterDOMapper {

    @Select("select * from parameter where key_word=#{keyWord}")
    ParameterDO getSelectParameter(@Param("keyWord") String keyWord);

    int deleteByPrimaryKey(Integer id);

    int insert(ParameterDO record);

    int insertSelective(ParameterDO record);

    ParameterDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParameterDO record);

    int updateByPrimaryKey(ParameterDO record);
}