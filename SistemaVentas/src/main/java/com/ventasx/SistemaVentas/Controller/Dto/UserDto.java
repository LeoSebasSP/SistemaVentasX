package com.ventasx.SistemaVentas.Controller.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String password;
    private Boolean isEnabled;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long userCreatorId;
    private Long userUpdaterId;
}
