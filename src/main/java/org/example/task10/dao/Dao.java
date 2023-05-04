package org.example.task10.dao;

import java.util.Optional;

public interface Dao <K, E> {
    E add(E entity);
    void delete(E entity);
    Optional<E> findById(K id);
}
