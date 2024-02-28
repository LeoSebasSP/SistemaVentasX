package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.MeasureUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IMeasureUnitRepository extends IGenericRepository<MeasureUnit, Integer>{

    @Query(value = "select * from measure_unit where lower(name) like lower(:name)\n" +
            "or translate(lower(name), 'áéíóú', 'aeiou') = translate(lower(:name), 'áéíóú', 'aeiou') limit 1;", nativeQuery = true)
    Optional<MeasureUnit> findAllByName(@Param("name") String name);

}
