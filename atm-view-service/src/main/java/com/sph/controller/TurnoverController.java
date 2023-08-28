package com.sph.controller;

import com.sph.pojo.Turnover;
import com.sph.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class TurnoverController {

    @Autowired
    AtmService atmService;

    /**
     * @Methodname list
     * @Description TODO
     * @Param model
     * @Param session
     * @Return String
     * @Author panqiang
     * @Date 2023/8/27 16:48
     */
    @RequestMapping("/turnover")
    public String list(Model model, HttpSession session) {

        Collection<Turnover> turnovers = atmService.list(session.getAttribute("loginUser").toString());
        model.addAttribute("turnovers", turnovers);
        return "turnover/list";
    }

}
