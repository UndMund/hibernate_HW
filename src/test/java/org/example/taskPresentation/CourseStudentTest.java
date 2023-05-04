package org.example.taskPresentation;

import lombok.Cleanup;
import org.example.HibernateUtil;
import org.example.taskPresentation.entity.*;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.management.Query;
import java.math.BigDecimal;
import java.util.List;

public class CourseStudentTest {
    private static SessionFactory sessionFactory;
    private static Course course1;
    private static Course course2;
    private static Student student1;
    private static Student student2;
    private static StudentProfile student1Profile;
    private static StudentProfile student2Profile;
    private static Trainer trainer;

    @BeforeAll
    public static void initCourse() {
        sessionFactory = HibernateUtil.get().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        student1 = Student.builder()
                .name("Nazar")
                .build();
        student2 = Student.builder()
                .name("Oleg")
                .build();

        course1 = Course.builder()
                .courseName("Java Enterprise")
                .build();

        course2 = Course.builder()
                .courseName("Java Core")
                .build();

        student1Profile = StudentProfile.builder()
                .gpa(new BigDecimal("7.45"))
                .build();

        student2Profile = StudentProfile.builder()
                .gpa(new BigDecimal("4.00"))
                .build();
        trainer = Trainer.builder()
                .name("Stepan")
                .profession(TrainerProfession.QA)
                .build();


        course1.setId((Long) session.save(course1));
        course2.setId((Long) session.save(course2));

        course1.addStudent(student1);
        course1.addStudent(student2);

        student1.setId((Long) session.save(student1));
        student2.setId((Long) session.save(student2));

        student1.setStudentProfile(student1Profile);
        student2.setStudentProfile(student2Profile);

        student1Profile.setStudentId((Long) session.save(student1Profile));
        student2Profile.setStudentId((Long) session.save(student2Profile));

        trainer.setId((Long) session.save(trainer));

        session.getTransaction().commit();
    }

    @AfterAll
    public static void tearDown() {
        sessionFactory.close();
    }

    @Test
    public void javaEStudents() {
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        System.out.println("----Task 2----");
        System.err.println(course1);
        System.err.println(course1.getStudents());

        session.getTransaction().commit();
    }

    @Test
    public void deleteStudentsWhereGpaBelowSix() {
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        System.out.println("----Task 4----");
        course1.getStudents()
                .removeIf(student -> student.getStudentProfile().getGpa().compareTo(new BigDecimal(6)) == -1);
        System.err.println(course1.getStudents());

        session.getTransaction().commit();
    }

    @Test
    public void addTrainerCourses() {
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        System.out.println("----Task 8----");

        trainer = session.get(Trainer.class, trainer.getId());
        course1 = session.get(Course.class, course1.getId());
        course2 = session.get(Course.class, course2.getId());

        var course1Trainer = new CourseTrainer();
        course1Trainer.setTrainer(trainer);
        course1Trainer.setCourse(course1);

        var course2Trainer = new CourseTrainer();
        course2Trainer.setTrainer(trainer);
        course2Trainer.setCourse(course2);

        session.getTransaction().commit();
    }

    @Test
    public void updateJavaECourse() {
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        System.out.println("----Task 9----");
        course1.setCourseName("New javaE course");
        session.update(course1);
        System.err.println(course1);

        session.getTransaction().commit();
    }

    @Test
    public void deleteJavaECourse() {
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        System.out.println("----Task 6/10----");
        //session.delete(course1);
        //System.err.println(session.get(Course.class, course1.getId()));

        session.getTransaction().commit();
    }
}
