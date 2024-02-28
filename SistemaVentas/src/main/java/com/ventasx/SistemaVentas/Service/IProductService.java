package com.ventasx.SistemaVentas.Service;

import com.opencsv.exceptions.CsvValidationException;
import com.ventasx.SistemaVentas.Persistence.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService extends ICrudService<Product, Long>{

    void disableProductsById(Long id);
    void enableProductById(Long id);

}
