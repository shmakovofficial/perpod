package ru.shmakovofficial.perpod.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shmakovofficial.perpod.entities.Employer;
import ru.shmakovofficial.perpod.entities.Teacher;

import java.util.List;


public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {

    Page<Teacher> findAllByEmployersContainingAndApprovedTrueOrderByLastNameAscFirstNameAscMiddleNameAsc(Employer e, Pageable p);
    List<Teacher> findAllByEmployersContainingAndApprovedTrue(Employer e);

}
