package ru.shmakovofficial.perpod.controllers;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shmakovofficial.perpod.entities.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class AlbumController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public String getEmployers(Model model, @RequestParam(required = false) Integer pageNum) {
        if (pageNum == null) pageNum = 1;
        Page<Employer> page = employerRepository.findAll(PageRequest.of(pageNum - 1, 9));
        model.addAttribute("employers", page.getContent());
        model.addAttribute("current", pageNum);
        model.addAttribute("pageNumbers", IntStream.range(1, page.getTotalPages() + 1).boxed().collect(Collectors.toList()));
        return "albums/home";
    }

    @GetMapping("/employer/{id}")
    public String getTeachers(Model model, @NonNull @PathVariable Long id, @RequestParam(required = false) Integer pageNum) {
        Employer employer = employerRepository.findById(id).orElse(null);
        if (employer == null) return "redirect:/";
        model.addAttribute("employer", employer);
        if (pageNum == null) pageNum = 1;
        Page<Teacher> page = teacherRepository.findAllByEmployersContainingAndApprovedTrue(employer, PageRequest.of(pageNum - 1, 9));
        model.addAttribute("teachers", page.getContent());
        model.addAttribute("current", pageNum);
        model.addAttribute("pageNumbers", IntStream.range(1, page.getTotalPages() + 1).boxed().collect(Collectors.toList()));
        return "albums/employer";
    }

    @GetMapping("/teacher/{id}")
    public String getReviews(Model model, @NonNull @PathVariable Long id, @RequestParam(required = false) Integer pageNum) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher == null) return "redirect:/";
        if (pageNum == null) pageNum = 1;
        model.addAttribute("teacher", teacher);
        model.addAttribute("mean", round(teacher.getReviewsMean()));
        Page<Review> page = reviewRepository.findAllByTeacherAndApprovedTrue(teacher, PageRequest.of(pageNum - 1, 9));
        model.addAttribute("reviews", page.getContent());
        model.addAttribute("current", pageNum);
        model.addAttribute("pageNumbers", IntStream.range(1, page.getTotalPages() + 1).boxed().collect(Collectors.toList()));
        return "albums/teacher";
    }

    private static float round(float number) {
        int pow = 10;
        for (int i = 1; i < 2; i++)
            pow *= 10;
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }
}
