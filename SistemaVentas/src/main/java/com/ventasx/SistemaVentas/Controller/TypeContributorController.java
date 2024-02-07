package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Persistence.Entity.TypeContributor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ventasx/v1/types_contributor")
@RequiredArgsConstructor
public class TypeContributorController {

    @GetMapping
    public ResponseEntity<List<TypeContributor>> listAll() throws Exception {
        List<TypeContributor> list = Arrays.asList(TypeContributor.values());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
