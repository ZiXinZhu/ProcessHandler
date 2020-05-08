package com.zzx.transactions.dao;

import com.zzx.transactions.entity.TradeDO;
import org.apache.ibatis.annotations.*;
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

    @Insert("insert into t_trade (id,trade_date,trade_time,money,trade_type,identity,bank,report,bank_account,insertime)" +
            " values (#{id},#{tradeDate},#{tradeTime},#{money},#{tradeType},#{identity},#{bank},#{report},#{bankAccount},#{insertime})")
    int insertOne(TradeDO tradeDO);
}
