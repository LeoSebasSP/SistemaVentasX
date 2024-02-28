package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Exception.DataErrorNullImport;
import com.ventasx.SistemaVentas.Exception.ErrorGenericImport;
import com.ventasx.SistemaVentas.Exception.ResourceNotFound;
import com.ventasx.SistemaVentas.Persistence.Entity.*;
import com.ventasx.SistemaVentas.Persistence.Repository.*;
import com.ventasx.SistemaVentas.Service.IDataExportService;
import com.ventasx.SistemaVentas.Service.IDataImportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataExportServiceImpl implements IDataExportService {

    private final IProductRepository productRepository;

    @Override
    public void exportProductsToExcel(OutputStream outputStream, Boolean enabledOrDisabled) throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet("Productos");

            // Headers
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Código");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Descripción");
            headerRow.createCell(3).setCellValue("Unidad Medida");
            headerRow.createCell(4).setCellValue("Precio de Venta (Soles)");
            headerRow.createCell(5).setCellValue("Grupo");
            headerRow.createCell(6).setCellValue("Categoría");
            headerRow.createCell(7).setCellValue("Tipo");
            headerRow.createCell(8).setCellValue("Marca");

            // Content
            List<Product> listProducts = new ArrayList<>();
            if (enabledOrDisabled){
                listProducts = productRepository.findAllByIsEnabledTrueOrderByCreationDateDesc();
            }else{
                listProducts = productRepository.findAllByIsEnabledFalseOrderByCreationDateDesc();
            }

            int rowIndex = 1;
            for (Product product : listProducts) {
                XSSFRow row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(product.getCode());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getDescription());
                row.createCell(3).setCellValue(product.getMeasureUnit().getName());
                if (product.getSellingPriceSoles()!=null){
                    row.createCell(4).setCellValue(product.getSellingPriceSoles());
                } else {
                    row.createCell(4).setBlank();
                }
                row.createCell(5).setCellValue(product.getGroupProduct().getName());
                row.createCell(6).setCellValue(product.getCategoryProduct().getName());
                row.createCell(7).setCellValue(product.getTypeProduct().getName());
                row.createCell(8).setCellValue(product.getBrandProduct().getName());
            }

            // Column Size
            for (int i = 0; i <= 8; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(outputStream);
        }
    }
}
