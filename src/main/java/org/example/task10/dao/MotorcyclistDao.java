package org.example.task10.dao;

import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.example.HibernateUtil;
import org.example.task10.entity.Motorcyclist;

import java.util.Optional;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MotorcyclistDao implements org.example.task10.dao.Dao<Long, Motorcyclist> {
    private static final MotorcyclistDao INSTANCE = new MotorcyclistDao();

    public static MotorcyclistDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Motorcyclist add(Motorcyclist entity) {
        @Cleanup var sessionFactory = HibernateUtil.get().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        entity.setId((Long) session.save(entity));
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Motorcyclist entity) {
        @Cleanup var sessionFactory = HibernateUtil.get().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    @Override
    public Optional<Motorcyclist> findById(Long id) {
        @Cleanup var sessionFactory = HibernateUtil.get().buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        return Optional.of(session.get(Motorcyclist.class, id));
    }
}