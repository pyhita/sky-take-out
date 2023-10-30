package com.sky.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLoginVO implements Serializable {

    @Schema(description = "id", example = "xxxxxxx")
    private Long id;
    @Schema(description = "username", example = "kante")
    private String userName;
    @Schema(description = "real name", example = "黎明")
    private String name;
    @Schema(description = "jwt token", example = "xxx.yyy.zzz")
    private String token;

}
