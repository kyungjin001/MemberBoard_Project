package com.icia.project.controller;

import com.icia.project.dto.MemberSaveDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {

        MemberSaveDTO member = new MemberSaveDTO();
        model.addAttribute("member",member);

        return "index";
    }


}
