package ru.shmakovofficial.perpod.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @NonNull
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Mark mark;

    @Column(columnDefinition = "TEXT")
    private String text;

    @NonNull
    private String course;

    @NonNull
    private Boolean approved = false;

    @NonNull
    @ManyToOne
    private Teacher teacher;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date reviewDate;
}
