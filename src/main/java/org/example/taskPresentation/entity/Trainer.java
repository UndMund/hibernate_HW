package org.example.taskPresentation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trainer", schema = "myschema")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrainerProfession profession;
    @Builder.Default
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<CourseTrainer> courseTrainers = new ArrayList<>();
}
