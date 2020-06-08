package ru.shmakovofficial.perpod.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employer {

    @NonNull
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String city;

    @ManyToMany(mappedBy = "employers")
    private Set<Teacher> teachers = new HashSet<>();
}
