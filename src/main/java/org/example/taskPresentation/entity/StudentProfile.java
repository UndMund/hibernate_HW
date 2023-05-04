package org.example.taskPresentation.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@ToString(exclude = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student_profile", schema = "myschema")
public class StudentProfile {
    @Id
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "student")
    )
    @GeneratedValue(generator = "generator")
    private Long studentId;
    @Column(nullable = false)
    private BigDecimal gpa;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Student student;
    //hibernate не любит натуральные ключи перепиши с норм PK
}
