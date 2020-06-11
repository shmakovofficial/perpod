package ru.shmakovofficial.perpod.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shmakovofficial.perpod.entities.Review;
import ru.shmakovofficial.perpod.entities.Teacher;


public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    Page<Review> findAllByTeacherAndApprovedTrueOrderByReviewDateDesc(Teacher t, Pageable p);

}
