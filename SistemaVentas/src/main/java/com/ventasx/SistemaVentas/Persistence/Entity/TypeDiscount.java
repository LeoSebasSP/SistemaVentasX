package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TypeDiscount {

    PERCENTAGE("Porcentaje"),
    EXACT_AMOUNT("Monto Fijo")
    ;

    private final String typeContributorMeaning;
}
