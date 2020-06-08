package ru.shmakovofficial.perpod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.shmakovofficial.perpod.entities.*;

import java.util.Date;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/employer/{id}/add")
    public String addTeacher(
            String firstName,
            String middleName,
            String lastName,
            String imageUrl,
            @PathVariable Long id
    ) {
        Teacher t = new Teacher();
        t.setFirstName(firstName);
        t.setMiddleName(middleName);
        t.setLastName(lastName);
        t.setImageUrl(imageUrl);
        Optional<Employer> e = employerRepository.findById(id);
        if (!e.isPresent()) return "redirect:/failure";
        t.getEmployers().add(e.get());
        teacherRepository.save(t);
        return "redirect:/success";
    }

    @PostMapping("/teacher/{id}/add")
    public String addReview(
            String course,
            int mark,
            String text,
            @PathVariable Long id
    ) {
        Review r = new Review();
        r.setCourse(course);
        r.setMark(Mark.values()[mark - 1]);
        Optional<Teacher> t = teacherRepository.findById(id);
        if (!t.isPresent()) return "redirect:/failure";
        Teacher teacher = t.get();
        r.setTeacher(teacher);
        r.setText(text);
        r.setReviewDate(new Date());
        teacher.getReviews().add(r);
        t.get().setReviewsCount(t.get().getReviewsCount() + 1);
        reviewRepository.save(r);
        return "redirect:/success";
    }
}