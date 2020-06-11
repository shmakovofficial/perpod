package ru.shmakovofficial.perpod.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetController {

    @GetMapping("/rules")
    public String getRules() {
        return "rules";
    }

    @GetMapping("/success")
    public String getSuccess() {
        return "success";
    }

    @GetMapping("/failure")
    public String getFailure() {
        return "failure";
    }

}
