package ru.shmakovofficial.perpod.entities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {

    Page<Teacher> findAllByEmployersContainingAndApprovedTrue(Employer e, Pageable p);
    List<Teacher> findAllByEmployersContainingAndApprovedTrue(Employer e);
}
