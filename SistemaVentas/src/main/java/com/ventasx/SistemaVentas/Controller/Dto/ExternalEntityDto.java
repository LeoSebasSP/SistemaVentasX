package com.ventasx.SistemaVentas.Controller.Dto;

import com.ventasx.SistemaVentas.Persistence.Entity.TypeContributor;
import com.ventasx.SistemaVentas.Persistence.Entity.TypeDocument;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalEntityDto {

    private Long id;

    @NotNull(message = "{customer.name.notNull}")
    @NotEmpty(message = "{customer.name.notEmpty}")
    @Size(message = "{customer.name.size}", max = 100)
    private String name;

    @NotNull(message = "{customer.documentNumber.notNull}")
    @NotEmpty(message = "{customer.documentNumber.notEmpty}")
    @Size(message = "{customer.documentNumber.size}", max = 20)
    private String documentNumber;

    @Size(message = "{customer.phone.size}", max = 20)
    private String phone;

    @Email(message = "{customer.email.email}")
    @Size(message = "{customer.email.size}", max = 50)
    private String email;

    @NotNull(message = "{customer.isSupplier.notNull}")
    private Boolean isSupplier;

    @NotNull(message = "{customer.isCustomer.notNull}")
    private Boolean isCustomer;

    @NotNull(message = "{customer.isEnabled.notNull}")
    private Boolean isEnabled;

    @NotNull(message = "{customer.typeContributor.notNull}")
    private TypeContributor typeContributor;

    @NotNull(message = "{customer.typeDocument.notNull}")
    private TypeDocument typeDocument;
}
