package com.sph.controller;

import com.sph.pojo.Account;
import com.sph.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;


/**
 * @author wxy
 * @date 2023/8/25 13:31
 */

/**
 * @Project ATM
 * @Classname TransferController
 * @Description
 * @Version 1.0.0
 * @Date 2023/8/27 16:41
 * @Created by panqiang
 */

@Controller
public class TransferController {
    @Autowired
    AtmService atmService;

    @RequestMapping("/transfer")
    String transfer() {
        return "transfer";
    }

    /**
     * @Methodname tr
     * @Description TODO
     * @Param id
     * @Param amount
     * @Param session
     * @Param model
     * @Return String
     * @Author panqiang
     * @Date 2023/8/27 16:48
     */
    @Transactional
    @PostMapping("/totransfer")
    String tr(@RequestParam("id") String id, @RequestParam(value = "amount",required = false) Integer amount, HttpSession session, Model model) throws SQLException {

        if (amount==null){
            model.addAttribute("msg", "请输入正确金额");
            return "transfer";
        }
        //检查对方账户是否存在
        Account account = atmService.query(id);
        if (account != null) {
            //检查余额够不够
            if ((Integer) session.getAttribute("balance") - amount >= 0) {
                // 检查今日取款额度
                Integer sum = atmService.todayWithdraw(session.getAttribute("loginUser").toString(), new Date(System.currentTimeMillis()));
                if (sum == null)
                    sum = 0;
                if (sum + amount > 10000) {
                    model.addAttribute("msg", "超过每日取款额度");
                    return "transfer";
                }

                //操作流水表,取款
                atmService.withdrawTurnover((String) session.getAttribute("loginUser"), amount, new Date(System.currentTimeMillis()));
                //操作账户表，更新总余额
                atmService.withdraw((String) session.getAttribute("loginUser"), (Integer) session.getAttribute("balance") - amount);
                session.setAttribute("balance",
                        (Integer) session.getAttribute("balance") - amount);


                Integer targetBalance = atmService.query(id).getBalance();
                //新增流水表条目，存款
                atmService.depositTurnover(id, amount, new Date(System.currentTimeMillis()));
                //操作账户表，更新总余额
                atmService.deposit(id, targetBalance + amount);

                return "redirect:/main";


            } else {
                model.addAttribute("msg", "余额不足");
                return "transfer";
            }

        } else {

            model.addAttribute("msg", "账户不存在");
            return "transfer";

        }

    }

}
