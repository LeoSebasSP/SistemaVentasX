package com.ventasx.SistemaVentas.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICrudService<T, ID> {

    T create(T t) throws Exception;

    List<T> getAll() throws Exception;

    Page<T> getAllPagination(Pageable pageable) throws Exception;

    T getById(ID id) throws Exception;

    T update(T t) throws Exception;

    void delete(ID id) throws Exception;

    Page<T> findAllByIsEnabledTrueOrderByCreationDateDescPagination(Pageable pageable);

    Page<T> findAllByIsEnabledFalseOrderByCreationDateDescPagination(Pageable pageable);

    List<T> findAllByIsEnabledTrueOrderByCreationDateDesc();

    List<T> findAllByIsEnabledFalseOrderByCreationDateDesc();
}
