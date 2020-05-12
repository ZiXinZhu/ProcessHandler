package com.zzx.transactions.dao;

import com.zzx.transactions.entity.RefundmentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RefundmentDOMapper {

    @Select("select * from refundment where id=#{id} for update")
    RefundmentDO lock(@Param("id") int id);

    int deleteByPrimaryKey(Integer id);

    int insert(RefundmentDO record);

    int insertSelective(RefundmentDO record);

    RefundmentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefundmentDO record);

    int updateByPrimaryKey(RefundmentDO record);
}