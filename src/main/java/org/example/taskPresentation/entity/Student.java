package org.example.taskPresentation.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "course")
@EqualsAndHashCode(of = "name")
@Builder
@Entity
@Table(name = "student", schema = "myschema")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;

    @OneToOne(mappedBy = "student")
    private StudentProfile studentProfile;

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
        studentProfile.setStudent(this);
    }
}
