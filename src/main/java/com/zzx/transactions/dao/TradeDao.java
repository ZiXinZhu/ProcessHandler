package com.zzx.transactions.dao;

import com.zzx.transactions.entity.TradeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TradeDao {


    @Select("select * from t_trade where id=#{id} for update")
    TradeDO queryOne(@Param("id") int id);

    @Update("update t_trade set bank=#{bank} where id=#{id}")
    int updateBank(@Param("bank") String bank, @Param("id") int id);

    @Update("update t_trade set remark=#{remark} where id=#{id}")
    int updateRemark(@Param("remark") String remark, @Param("id") int id);
}
