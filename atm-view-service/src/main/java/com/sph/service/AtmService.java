package com.sph.service;

import com.sph.client.AtmClientFeign;
import com.sph.pojo.Account;
import com.sph.pojo.Turnover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @Project parentProject
 * @Classname AtmService
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/8/27 21:38
 * @Created by panqiang
 */
@Service
public class AtmService {
    @Autowired
    AtmClientFeign atmClientFeign;

    public void depositTurnover(String fk, Integer amount, Date date) {
        atmClientFeign.depositTurnover(fk, amount, date);
    }

    public void deposit(String fk, Integer balance) {
        atmClientFeign.deposit(fk, balance);
    }


    public Account query(String username) {
        return atmClientFeign.query(username);
    }

    public Integer todayWithdraw(String fk, Date date) {
        return atmClientFeign.todayWithdraw(fk, date);
    }

    public void withdrawTurnover(String fk, Integer amount,
                                 Date date){
        atmClientFeign.withdrawTurnover(fk,amount,date);
    }


    public void withdraw(String fk,
                         Integer balance){
        atmClientFeign.withdraw(fk,balance);
    }

    public List<Turnover> list(String fk){
        return atmClientFeign.list(fk);
    }

}
