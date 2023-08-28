package com.sph.client;

import com.sph.pojo.Account;
import com.sph.pojo.Turnover;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * @Project parentProject
 * @Classname AtmClientFeign
 * @Description
 * @Version 1.0.0
 * @Date 2023/8/27 19:13
 * @Created by panqiang
 */

@FeignClient(value = "ATM-DATA-SERVICE")
public interface AtmClientFeign {
    @PostMapping("/depositTurnover")
    public void depositTurnover(@RequestParam("fk") String fk,
                                @RequestParam("amount") Integer amount,
                                @RequestParam("date") Date date);

    @PutMapping("/deposit")
    public void deposit(@RequestParam("fk") String fk,
                        @RequestParam("balance") Integer balance);


    @GetMapping(value="/query")
    public Account query(@RequestParam("id") String id);


    @GetMapping("/todayWithdraw")
    public Integer todayWithdraw(@RequestParam("fk")String fk,
                                 @RequestParam("date") Date date);

    @PostMapping("/withdrawTurnover")
    public void withdrawTurnover(@RequestParam("fk") String fk,
                                     @RequestParam("amount") Integer amount,
                                     @RequestParam("date") Date date);


    @PutMapping("/withdraw")
    public void withdraw(@RequestParam("fk") String fk,
                         @RequestParam("balance") Integer balance);

    @GetMapping("/list")
    public List<Turnover> list(@RequestParam("fk") String fk);





}


