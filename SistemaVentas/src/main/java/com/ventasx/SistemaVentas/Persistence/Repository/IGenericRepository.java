package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IGenericRepository <T, ID> extends JpaRepository<T, ID> {
    Page<T> findAllByIsEnabledTrueOrderByCreationDateDesc(Pageable pageable);

    Page<T> findAllByIsEnabledFalseOrderByCreationDateDesc(Pageable pageable);

    List<T> findAllByIsEnabledTrueOrderByCreationDateDesc();

    List<T> findAllByIsEnabledFalseOrderByCreationDateDesc();

}
