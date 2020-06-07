package ru.shmakovofficial.perpod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shmakovofficial.perpod.entities.Teacher;
import ru.shmakovofficial.perpod.entities.TeacherRepository;

@Controller
public class MainController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "home";
    }

    @GetMapping(value = "/add")
    public String addNewUserPage() {
        return "add";
    }

    @PostMapping(path = "/add")
    public String addNewUser(
            @RequestParam String firstName,
            @RequestParam String middleName,
            @RequestParam String lastName
    ) {
        Teacher t = new Teacher(firstName, middleName, lastName);
        teacherRepository.save(t);
        return "redirect:/";
    }
}
