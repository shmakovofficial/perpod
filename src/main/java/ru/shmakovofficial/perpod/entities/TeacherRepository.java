package ru.shmakovofficial.perpod.entities;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    public Iterable<Teacher> findByApprovedTrue();

    public Optional<Teacher> findByIdAndApprovedTrue(@NonNull Long id);

    public Iterable<Teacher> findByEmployersContains(@NonNull Employer id);
}
