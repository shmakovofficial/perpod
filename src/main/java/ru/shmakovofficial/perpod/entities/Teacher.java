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
public class Teacher {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String middleName;

    @NonNull
    private String lastName;

    @NonNull
    private Boolean approved = false;

    @NonNull
    private String imageUrl;

    @NonNull
    private Integer reviewsCount = 0;

    @NonNull
    private Float reviewsMean = .0F;

    @NonNull
    @ManyToMany
    private Set<Employer> employers = new HashSet<>();

    @NonNull
    @OneToMany
    private Set<Review> reviews = new HashSet<>();
}