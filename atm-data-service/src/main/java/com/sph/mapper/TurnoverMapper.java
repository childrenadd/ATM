package com.sph.mapper;

import com.sph.pojo.Turnover;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

/**
 * @Project ATM
 * @Classname TurnoverMapper
 * @Description
 * @Version 1.0.0
 * @Date 2023/8/27 16:02
 * @Created by panqiang
 */

public interface TurnoverMapper {
    //操作流水表
    @Insert("insert into turnover(fk,type,amount,date) values (#{card},\"存款\", #{amount}, #{date})")
    void depositTurnover(@Param("card") String fk, @Param("amount") Integer amount, @Param("date") Date date);

    //操作账户表，更新总余额
    @Update("update account set balance = #{balance} where id = #{card}")
    void deposit(@Param("card") String fk, @Param("balance") Integer balance);

    @Select("select sum(amount) from turnover where fk = #{fk} and type = '取款' and date = #{date}")
    Integer todayWithdraw(String fk, Date date);

    //操作流水表
    //@Insert("insert into turnover(fk,type,amount,date) values (#{card},\"取款\", #{amount}, #{date})")
    //void withdrawTurnover (@Param("card") String fk, @Param("amount") Integer amount, @Param("date") Date date);
    @Insert("insert into turnover(fk,type,amount,date) values (#{fk},\"取款\", #{amount}, #{date})")
    void withdrawTurnover(String fk, Integer amount, Date date);

    //操作账户表，更新总余额
    //@Update("update account set balance = #{balance} where id = #{card}")
    //void withdraw (@Param("card") String fk, @Param("balance") Integer balance);
    @Update("update account set balance = #{balance} where id = #{fk}")
    void withdraw(String fk, Integer balance);


    @Select("select * from turnover where fk = #{fk} order by id desc")
    List<Turnover> list(String fk);

}
