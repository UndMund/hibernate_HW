package org.example.taskPresentation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "courseName")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course", schema = "myschema")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String courseName;

    @Builder.Default
    @OneToMany(mappedBy = "course", orphanRemoval = true)
    private Set<Student> students = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseTrainer> courseTrainers = new ArrayList<>();

    public void addStudent(Student student) {
        this.students.add(student);
        student.setCourse(this);
    }
}
