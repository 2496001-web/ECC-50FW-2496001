package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 선언
public class FirstController {

    @GetMapping("/hi") //URl 요청 접수
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "예은");
        return "greetings"; //greetings.mustache 페이지 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "예은");
        return "goodbye";
    }

}