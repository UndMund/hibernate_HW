package org.example.taskPresentation.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course_trainer", schema = "myschema")
public class CourseTrainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public void setCourse(Course course) {
        this.course = course;
        this.course.getCourseTrainers().add(this);
    }
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        this.trainer.getCourseTrainers().add(this);
    }
}
