package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.ICrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class CrudServiceImpl<T, ID> implements ICrudService<T, ID> {
    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T create(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> getAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public Page<T> getAllPagination(Pageable pageable) throws Exception {
        return getRepo().findAll(pageable);
    }

    @Override
    public T getById(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
