package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.exception.ResourceNotFoundException;
import com.example.departmentservice.mapper.AutoDepartmentMapper;
import com.example.departmentservice.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper mapper;


    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = mapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = mapper.map(savedDepartment, DepartmentDto.class);

        return savedDepartmentDto;
    }

    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Optional<Department> department = departmentRepository.findByDepartmentCode(departmentCode);
        if (!department.isPresent()) {
            throw new ResourceNotFoundException("Department", "code", departmentCode);
        }
        DepartmentDto departmentDto = mapper.map(department.get(), DepartmentDto.class);
        return departmentDto;
    }
}
