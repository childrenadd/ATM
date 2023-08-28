package com.sph.controller;

import com.sph.pojo.Account;
import com.sph.service.AtmService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController
{

    @Autowired
    AtmService atmService;

/**
 * @Methodname login
 * @Description TODO
 * @Param username
 * @Param password
 * @Param model
 * @Param session
 * @Return String
 * @Author panqiang
 * @Date 2023/8/27 16:47
 */
    @SneakyThrows
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session)
    {
//        AccountDao accountDao = new AccountDao();
//        // 从账户表account根据id查找
//        ResultSet query = accountDao.query(username);

        Account account = atmService.query(username);
//        if (query.next())
        if (account!=null)
        {
            // 获得第二列参数，为密码
            String psw = account.getPassword();
            //如果密码正确
            if (!StringUtils.isEmpty(psw) && psw.equals(password))
            {
                session.setAttribute("loginUser", username);
                session.setAttribute("firstName", account.getFirstName());
                session.setAttribute("lastName", account.getLastName());
                session.setAttribute("balance", account.getBalance());
                return "redirect:/main";
            }
            else //有账号，但密码错误
            {
                model.addAttribute("msg", "卡号密码错误");
                return "index";
            }
        }
        else   //
        {
            model.addAttribute("msg", "卡号不存在");
            return "index";
        }
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/index.html";
    }
}
