package ru.shmakovofficial.perpod.entities;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    Page<Review> findAllByTeacherAndApprovedTrue(@NonNull Teacher t, Pageable p);

}
