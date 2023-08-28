package com.sph.controller;

import com.sph.mapper.AccountMapper;
import com.sph.mapper.TurnoverMapper;
import com.sph.pojo.Account;
import com.sph.pojo.Turnover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * @Project parentProject
 * @Classname MainController
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/8/27 21:58
 * @Created by panqiang
 */
@RestController
public class MainController {

    @Autowired
    TurnoverMapper turnoverMapper;

    @Autowired
    AccountMapper accountMapper;

    @PostMapping("/depositTurnover")
    public void depositTurnover(@RequestParam("fk") String fk,
                                @RequestParam("amount") Integer amount,
                                @RequestParam("date") Date date) {
        turnoverMapper.depositTurnover(fk, amount, date);

    }

    @PutMapping("/deposit")
    public void deposit(@RequestParam("fk") String fk,
                        @RequestParam("balance") Integer balance) {
        turnoverMapper.deposit(fk, balance);
    }

    @GetMapping(value="/query")
    public Account query(@RequestParam("id") String id)
    {
        return accountMapper.query(id);
    }

    @GetMapping("/todayWithdraw")
    public Integer todayWithdraw(@RequestParam("fk") String fk,
                                 @RequestParam("date") Date date) {
        return turnoverMapper.todayWithdraw(fk, date);
    }


    @PostMapping("/withdrawTurnover")
    public void withdrawTurnover(@RequestParam("fk") String fk,
                                 @RequestParam("amount") Integer amount,
                                 @RequestParam("date") Date date) {
        turnoverMapper.withdrawTurnover(fk, amount, date);
    }

    @PutMapping("/withdraw")
    public void withdraw(@RequestParam("fk") String fk,
                         @RequestParam("balance") Integer balance) {
        turnoverMapper.withdraw(fk, balance);
    }

    @GetMapping("/list")
    public List<Turnover> list(@RequestParam("fk") String fk) {
        return turnoverMapper.list(fk);
    }


}
