package ru.shmakovofficial.perpod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.shmakovofficial.perpod.entities.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AlbumController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("/")
    public String getEmployers(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "home";
    }

    @GetMapping("/employer/{id}")
    public String getTeachers(Model model, @PathVariable Long id) {
        Optional<Employer> emp = employerRepository.findById(id);
        if (!emp.isPresent()) return "redirect:/";
        Employer employer = emp.get();
        model.addAttribute("teachers", employer.getTeachers().stream().filter(Teacher::getApproved).collect(Collectors.toSet()));
        model.addAttribute("employer", employer);
        return "employer";
    }

    @GetMapping("/teacher/{id}")
    public String getReviews(Model model, @PathVariable Long id) {
        Optional<Teacher> t = teacherRepository.findByIdAndApprovedTrue(id);
        if (!t.isPresent()) return "redirect:/";
        Teacher teacher = t.get();
        model.addAttribute("teacher", teacher);
        Set<Review> reviewSet = teacher.getReviews();
        model.addAttribute("reviews", reviewSet);
        double rating = reviewSet.stream().map(Review::getMark).mapToInt(Enum::ordinal).average().orElse(-1) + 1;
        String colour;
        if (rating == 0) {
            colour = "badge badge-info";
        } else if (rating < 3) {
            colour = "badge badge-danger";
        } else if (rating < 4) {
            colour = "badge badge-warning";
        } else if (rating <= 5) {
            colour = "badge badge-success";
        } else {
            colour = "badge badge-info";
        }
        model.addAttribute("colour", colour);
        model.addAttribute("rating", rating);
        return "teacher";
    }
}
