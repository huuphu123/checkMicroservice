package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.EmailEmployeeExistsException;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper mapper;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDto.getEmail());

        if (optionalEmployee.isPresent()) {
            throw new EmailEmployeeExistsException("Employee email has already existed!");
        }

        Employee employee = mapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = mapper.map(savedEmployee, EmployeeDto.class);

        return savedEmployeeDto;

    }

    public EmployeeDto getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (!optionalEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee", "id", id);
        }

        EmployeeDto employeeDto = mapper.map(optionalEmployee.get(), EmployeeDto.class);

        return employeeDto;
    }
}
