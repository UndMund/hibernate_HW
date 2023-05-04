package org.example.task10.dao;

import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.example.HibernateUtil;
import org.example.task10.entity.Bike;

import java.util.Optional;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BikeDao implements Dao <Long, Bike> {
    private static final BikeDao INSTANCE = new BikeDao();

    public static BikeDao getInstance() {
        return INSTANCE;
    }
    @Override
    public Bike add(Bike entity) {
        @Cleanup var sessionFactory = HibernateUtil.get().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        entity.setId((Long) session.save(entity));
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Bike entity) {
        @Cleanup var sessionFactory = HibernateUtil.get().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    @Override
    public Optional<Bike> findById(Long id) {
        @Cleanup var sessionFactory = HibernateUtil.get().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        return Optional.of(session.get(Bike.class, id));
    }
}