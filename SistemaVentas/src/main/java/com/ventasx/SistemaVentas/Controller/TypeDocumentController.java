package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Persistence.Entity.TypeDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ventasx/v1/types_document")
@RequiredArgsConstructor
public class TypeDocumentController {

    private final MessageSource messageSource;

    @GetMapping
    public ResponseEntity<List<String>> listAll() throws Exception {
//        List<String> list = Arrays.stream(TypeDocument.values()).map(TypeDocument::getTypeDocumentMeaning).collect(Collectors.toList());
//        return new ResponseEntity<>(list, HttpStatus.OK);
        return null;
    }
}
