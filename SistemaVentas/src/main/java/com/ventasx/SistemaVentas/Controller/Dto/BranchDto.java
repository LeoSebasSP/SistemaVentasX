package com.ventasx.SistemaVentas.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {

    private String name;
    private String description;
    private String address;
    private String phone;
    private String codeAnnex;
    private Long userCreatorId;
    private Long userUpdaterId;
}
