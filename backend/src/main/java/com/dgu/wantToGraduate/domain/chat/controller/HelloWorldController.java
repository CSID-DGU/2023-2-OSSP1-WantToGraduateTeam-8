package com.dgu.wantToGraduate.domain.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloWorldController {

    @GetMapping("/helloWorld/{name}")
    public String test(Model model, @PathVariable("name") String name) {
        model.addAttribute("name", name);
        return "chat/helloWorld";
    }
}
