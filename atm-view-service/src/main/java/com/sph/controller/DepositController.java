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


@Controller
// 存款
public class DepositController {
    @Autowired
    AtmService atmService;


    @RequestMapping("/deposit")
        // 从commons.html进入 存款页面deposit.html
    String deposit() {
        return "deposit";
    }

/**
 * @Methodname de
 * @Description TODO
 * @Param amount
 * @Param session
 * @Return String
 * @Author panqiang
 * @Date 2023/8/27 16:47
 */
    @Transactional
    @PostMapping("/todeposit")
        // deposit.html前端传进来amount，session
    String de(@RequestParam(value = "amount",required = false) Integer amount, HttpSession session, Model model) {

        if (amount==null){
            model.addAttribute("msg", "请输入正确金额");
            return "deposit";
        }

        //新增流水表条目
        atmService.depositTurnover((String) session.getAttribute("loginUser"), amount, new Date(System.currentTimeMillis()));
        //操作账户表，更新总余额
        atmService.deposit((String) session.getAttribute("loginUser"), (Integer) session.getAttribute("balance") + amount);

        session.setAttribute("balance",
                (Integer) session.getAttribute("balance") + amount);

        return "redirect:/main";
    }
}
