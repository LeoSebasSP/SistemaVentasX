package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Exception.DataErrorNullImport;
import com.ventasx.SistemaVentas.Exception.ErrorGenericImport;
import com.ventasx.SistemaVentas.Exception.ResourceNotFound;
import com.ventasx.SistemaVentas.Persistence.Entity.*;
import com.ventasx.SistemaVentas.Persistence.Repository.*;
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
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DataImportServiceImpl implements IDataImportService {

    private final IProductRepository productRepository;
    private final IMeasureUnitRepository measureUnitRepository;
    private final IGroupProductRepository groupProductRepository;
    private final ICategoryProductRepository categoryProductRepository;
    private final ITypeProductRepository typeProductRepository;
    private final IBrandProductRepository brandProductRepository;

    @Override
    @Transactional
    public void createProductsFromExcel(MultipartFile file) throws IOException{
        int rowNumber = 0;
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())){
            GroupProduct group = groupProductRepository.findById(1L).orElse(null);
            CategoryProduct category = categoryProductRepository.findById(1L).orElse(null);
            TypeProduct type = typeProductRepository.findById(1L).orElse(null);
            BrandProduct brand = brandProductRepository.findById(1L).orElse(null);

            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;

            Product product;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                rowNumber = i + 1;
                row = sheet.getRow(i);
                product = new Product();
                product.setIsEnabled(true);
                product.setCode(setCellValue(row, 0, "Código", i + 1));
                product.setName(setCellValue(row, 1, "Nombre", i + 1));
                product.setDescription(setCellValue(row, 2, "Descripción", i + 1));
                product.setMeasureUnit(measureUnitValidation(row.getCell(3), i+1));
                if (setCellValue(row, 4, "Precio Venta Soles", i + 1)!=null){
                    product.setSellingPriceSoles(Float.parseFloat(setCellValue(row, 4, "Precio Venta Soles", i + 1)));
                }else{
                    product.setSellingPriceSoles(null);
                }
                product.setBrandProduct(brandProductValidation(row.getCell(8), brand));

                if (row.getCell(5) == null || row.getCell(6) == null || row.getCell(7) == null){
                    product.setGroupProduct(group);
                    product.setCategoryProduct(category);
                    product.setTypeProduct(type);
                    productRepository.save(product);
                    continue;
                }

                String cell5Value = row.getCell(5).toString().trim();
                String cell6Value = row.getCell(6).toString().trim();
                String cell7Value = row.getCell(7).toString().trim();

                if (cell5Value.isEmpty() || cell6Value.isEmpty() || cell7Value.isEmpty()){
                    product.setGroupProduct(group);
                    product.setCategoryProduct(category);
                    product.setTypeProduct(type);
                    productRepository.save(product);
                    continue;
                }

                GroupProduct groupProduct = groupProductRepository.findAllByName(cell5Value)
                        .orElseGet(()-> groupProductCreation(cell5Value));
                product.setGroupProduct(groupProduct);
                CategoryProduct categoryProduct = categoryProductRepository.findAllByGroupProductAndName(cell6Value, groupProduct.getId())
                        .orElseGet(()-> categoryProductCreation(cell6Value, groupProduct));
                product.setCategoryProduct(categoryProduct);
                product.setTypeProduct(typeProductRepository.findAllByCategoryProductAndName(cell7Value, categoryProduct.getId())
                        .orElseGet(() -> typeProductCreation(cell7Value, categoryProduct)));

                productRepository.save(product);
            }
        }
        catch (Exception e){
            throw new ErrorGenericImport("Product", e.getMessage(), rowNumber);
        }
    }

    public MeasureUnit measureUnitValidation(Cell cell, int row) {
        if (cell == null) {
            throw new DataErrorNullImport("Producto", "Unidad Medida", row);
        }

        String cellValue = cell.toString().trim();
        if (cellValue.isEmpty()) {
            throw new DataErrorNullImport("Producto", "Unidad Medida", row);
        }

        return measureUnitRepository.findAllByName(cellValue)
                .orElseThrow(() -> new ResourceNotFound("Unidad Medida","Nombre", cellValue));
    }

    public BrandProduct brandProductValidation(Cell cell, BrandProduct defaultBrand) {
        if (cell == null) {
            return defaultBrand;
        }

        String cellValue = cell.toString().trim();
        if (cellValue.isEmpty()) {
            return defaultBrand;
        }

        return brandProductRepository.findAllByName(cellValue)
                .orElseGet(() -> brandProductCreation(cellValue));
    }

//    private MeasureUnit measureUnitCreation(String cellValue) {
//        return measureUnitRepository.save(MeasureUnit.builder()
//                .name(cellValue.toUpperCase())
//                .description(cellValue.toUpperCase())
//                .creationDate(LocalDateTime.now())
//                .isEnabled(true)
//                .build());
//    }

    private BrandProduct brandProductCreation(String cellValue) {
        return brandProductRepository.save(BrandProduct.builder()
                .name(cellValue.toUpperCase())
                .description(cellValue.toUpperCase())
                .creationDate(LocalDateTime.now())
                .isEnabled(true)
                .build());
    }

    private GroupProduct groupProductCreation(String cellValue) {
        return groupProductRepository.save(GroupProduct.builder()
                .name(cellValue.toUpperCase())
                .description(cellValue.toUpperCase())
                .creationDate(LocalDateTime.now())
                .isEnabled(true)
                .build());
    }

    private TypeProduct typeProductCreation(String cellValue, CategoryProduct categoryProduct) {
        return typeProductRepository.save(TypeProduct.builder()
                .name(cellValue.toUpperCase())
                .description(cellValue.toUpperCase())
                .categoryProduct(categoryProduct)
                .creationDate(LocalDateTime.now())
                .isEnabled(true)
                .build());
    }

    private CategoryProduct categoryProductCreation(String cellValue, GroupProduct groupProduct) {
        return categoryProductRepository.save(CategoryProduct.builder()
                .name(cellValue.toUpperCase())
                .description(cellValue.toUpperCase())
                .groupProduct(groupProduct)
                .creationDate(LocalDateTime.now())
                .isEnabled(true)
                .build());
    }

    private String setCellValue(XSSFRow row, int cellIndex, String columnName, int rowIndex) {
        if (row.getCell(cellIndex) == null) {
            if(columnName.equalsIgnoreCase("Descripción")){
                return row.getCell(1).toString().trim();
            }
            if(columnName.equalsIgnoreCase("Precio Venta Soles")){
                return null;
            }
            throw new DataErrorNullImport("Producto", columnName, rowIndex);
        }
        if (row.getCell(cellIndex).toString().trim().isEmpty()){
            if(columnName.equalsIgnoreCase("Descripción")){
                return row.getCell(1).toString().trim();
            }
            if(columnName.equalsIgnoreCase("Precio Venta Soles")){
                return null;
            }
            throw new DataErrorNullImport("Producto", columnName, rowIndex);
        }
        return row.getCell(cellIndex).toString().trim();
    }


}
