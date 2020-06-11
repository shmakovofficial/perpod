package ru.shmakovofficial.perpod.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shmakovofficial.perpod.entities.Employer;

import java.util.List;

public interface EmployerRepository extends PagingAndSortingRepository<Employer, Long> {
    Page<Employer> findAllByApprovedTrueOrderByNameAscCityAsc(Pageable p);
    List<Employer> findAllByApprovedTrue();
}
