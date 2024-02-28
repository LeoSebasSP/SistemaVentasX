package com.ventasx.SistemaVentas.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

public interface IDataImportService {

    void createProductsFromExcel(MultipartFile file) throws IOException;
}
