package com.github.caijh.sample.es.controller;

import javax.inject.Inject;

import com.github.caijh.framework.web.controller.BaseController;
import com.github.caijh.sample.es.model.Employee;
import com.github.caijh.sample.es.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("es")
public class EsController extends BaseController {

    @Inject
    private EmployeeRepository employeeRepository;

    @PostMapping("employee")
    public String add() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("xuxu");
        employee.setLastName("zh");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        employeeRepository.save(employee);
        return "success";
    }

    @GetMapping("employee/{id}")
    public Employee get(@PathVariable String id) {
        return employeeRepository.queryEmployeeById(id);
    }

    @DeleteMapping("employee/{id}")
    public String delete(@PathVariable String id) {
        Employee employee = employeeRepository.queryEmployeeById(id);
        employeeRepository.delete(employee);
        return "success";
    }


}
