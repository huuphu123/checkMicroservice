package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoDepartmentMapper {

    AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class);

    DepartmentDto mapToDepartmentDto(Department department);
    Department mapToDepartment(DepartmentDto department);
}
