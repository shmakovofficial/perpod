package ru.shmakovofficial.perpod.entities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployerRepository extends PagingAndSortingRepository<Employer, Long> {
    Page<Employer> findAllByApprovedTrue(Pageable p);
}
