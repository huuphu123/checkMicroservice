package com.example.departmentservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    @JsonProperty("departmentName")
    private String departmentName;

    @JsonProperty("departmentDescription")
    private String departmentDescription;

    @JsonProperty("departmentCode")
    private String departmentCode;
}
