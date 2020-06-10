package ru.shmakovofficial.perpod.entities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployerRepository extends PagingAndSortingRepository<Employer, Long> {
    Page<Employer> findAllByApprovedTrue(Pageable p);
    List<Employer> findAllByApprovedTrue();
}
