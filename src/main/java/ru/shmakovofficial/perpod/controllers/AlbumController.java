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
import ru.shmakovofficial.perpod.entities.Employer;
import ru.shmakovofficial.perpod.entities.Review;
import ru.shmakovofficial.perpod.entities.Teacher;
import ru.shmakovofficial.perpod.repositories.EmployerRepository;
import ru.shmakovofficial.perpod.repositories.ReviewRepository;
import ru.shmakovofficial.perpod.repositories.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class AlbumController {

    @Autowired
    public AlbumController(TeacherRepository teacherRepository, EmployerRepository employerRepository, ReviewRepository reviewRepository) {
        this.teacherRepository = teacherRepository;
        this.employerRepository = employerRepository;
        this.reviewRepository = reviewRepository;
    }

    private final TeacherRepository teacherRepository;

    private final EmployerRepository employerRepository;

    private final ReviewRepository reviewRepository;

    @GetMapping("/")
    public String getEmployers(Model model, @RequestParam(required = false) Integer pageNum) {
        if (pageNum == null) pageNum = 1;
        Page<Employer> page = employerRepository.findAllByApprovedTrueOrderByNameAscCityAsc(PageRequest.of(pageNum - 1, 9));
        List<SelectReturn> selects = employerRepository.findAllByApprovedTrue()
                .stream()
                .map(SelectReturn::new)
                .collect(Collectors.toList());
        model.addAttribute("selects", selects);
        model.addAttribute("employers", page.getContent());
        model.addAttribute("current", pageNum);
        model.addAttribute("pageNumbers", IntStream.range(1, page.getTotalPages() + 1).boxed().collect(Collectors.toList()));
        return "albums/home";
    }

    @GetMapping("/employer/{id}")
    public String getTeachers(Model model, @NonNull @PathVariable Long id, @RequestParam(required = false) Integer pageNum) {
        Employer employer = employerRepository.findById(id).orElse(null);
        if (employer == null) return "redirect:/";
        if (pageNum == null) pageNum = 1;
        Page<Teacher> page = teacherRepository.findAllByEmployersContainingAndApprovedTrueOrderByLastNameAscFirstNameAscMiddleNameAsc(employer, PageRequest.of(pageNum - 1, 9));
        List<SelectReturn> selects = teacherRepository.findAllByEmployersContainingAndApprovedTrue(employer)
                .stream()
                .map(SelectReturn::new)
                .collect(Collectors.toList());
        model.addAttribute("selects", selects);
        model.addAttribute("employer", employer);
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
        Page<Review> page = reviewRepository.findAllByTeacherAndApprovedTrueOrderByReviewDateDesc(teacher, PageRequest.of(pageNum - 1, 9));
        List<Review> reviews = page.getContent();
        List<String> badges = reviews
                .stream()
                .map(Review::getMark)
                .map(Enum::ordinal)
                .map(AlbumController::incr)
                .map(AlbumController::badgeClass)
                .collect(Collectors.toList());
        List<Integer> pageNumbers = IntStream
                .range(1, page.getTotalPages() + 1)
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("teacher", teacher);
        model.addAttribute("mean", round(teacher.getReviewsMean()));
        model.addAttribute("badge", badgeClass(teacher.getReviewsMean()));
        model.addAttribute("reviews", reviews);
        model.addAttribute("badges", badges);
        model.addAttribute("current", pageNum);
        model.addAttribute("pageNumbers", pageNumbers);
        return "albums/teacher";
    }

    private static float round(float number) {
        int pow = 10;
        for (int i = 1; i < 2; i++)
            pow *= 10;
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }


    private static String badgeClass(float mark) {
        if (mark < 1 || mark > 5) {
            return "badge badge-info";
        } else if (mark < 3) {
            return "badge badge-danger";
        } else if (mark == 3) {
            return "badge badge-warning";
        } else {
            return "badge badge-success";
        }
    }

    private static int incr(int value) {
        return value + 1;
    }
}
