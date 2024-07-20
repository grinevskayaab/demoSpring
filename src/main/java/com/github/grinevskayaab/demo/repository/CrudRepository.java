package com.github.grinevskayaab.demo.repository;

import java.util.List;

public interface CrudRepository<K, V> {
    V findById(K k);

    List<V> findAll();

    V create(V v);

    V update(V v);

    void delete(K k);
}
