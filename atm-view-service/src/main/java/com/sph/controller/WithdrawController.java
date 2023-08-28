package com.sph.controller;

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


/**
 * @Project ATM
 * @Classname WithdrawController
 * @Description 取款
 * @Version 1.0.0
 * @Date 2023/8/27 12:10
 * @Created by panqiang
 */
@Controller
public class WithdrawController {


    @Autowired
    AtmService atmService;

    @RequestMapping("/withdraw")
    String withdraw() {
        return "withdraw";
    }

    /**
     * @Methodname wi
     * @Description TODO
     * @Param amount
     * @Param session
     * @Param model
     * @Return String
     * @Author panqiang
     * @Date 2023/8/27 16:42
     */
    @Transactional
    @PostMapping("/towithdraw")
    String wi(@RequestParam("amount") Integer amount, HttpSession session, Model model) {

        Integer sum =atmService.todayWithdraw(session.getAttribute("loginUser").toString(), new Date(System.currentTimeMillis()));
        if (sum == null)
            sum = 0;

        if (sum + amount > 10000) {
            model.addAttribute("msg", "超过每日取款额");
            return "withdraw";
        }
//          里面钱够的话，balance是从页面获得的账户余额
        if ((Integer) session.getAttribute("balance") - amount >= 0) {
//            操作流水表
            atmService.withdrawTurnover((String) session.getAttribute("loginUser"), amount, new Date(System.currentTimeMillis()));
//            操作账户表，更新总余额
            atmService.withdraw((String) session.getAttribute("loginUser"), (Integer) session.getAttribute("balance") - amount);
            session.setAttribute("balance",
                    (Integer) session.getAttribute("balance") - amount);
            return "redirect:/main";
        } else
//            钱不够的话
        {
            model.addAttribute("msg", "余额不足");
            return "withdraw";
        }
    }
}
