package com.sph.mapper;

import com.sph.pojo.Account;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @Project ATM
 * @Classname AccountMapper
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/8/27 13:48
 * @Created by panqiang
 */
public interface AccountMapper {


    @Select("select * from account where id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "password", column = "passWord"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "phoneNumber", column = "phoneNumber"),

    }
    )
    Account query(String id);
}
