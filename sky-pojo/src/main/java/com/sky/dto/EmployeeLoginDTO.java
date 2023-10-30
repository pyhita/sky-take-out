package com.sky.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeLoginDTO implements Serializable {

    @Schema(description = "username", example = "kante")
    private String username;
    @Schema(description = "password", example = "123456")
    private String password;

}
