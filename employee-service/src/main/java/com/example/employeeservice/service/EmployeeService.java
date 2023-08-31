package com.example.employeeservice.service;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.EmailEmployeeExistsException;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper mapper;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient webClient;

    @Autowired
    private ApiClient apiClient;

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

    public ApiResponseDto getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (!optionalEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee", "id", id);
        }

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8081/api/departments/" + optionalEmployee.get().getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8081/api/departments/" + optionalEmployee.get().getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();
        DepartmentDto departmentDto = apiClient.getDepartment(optionalEmployee.get().getDepartmentCode());
        EmployeeDto employeeDto = mapper.map(optionalEmployee.get(), EmployeeDto.class);

        ApiResponseDto response = new ApiResponseDto(employeeDto, departmentDto);
        return response;
    }
}
