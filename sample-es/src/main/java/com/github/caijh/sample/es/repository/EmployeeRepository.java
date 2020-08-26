package com.github.caijh.sample.es.repository;

import com.github.caijh.sample.es.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

    Employee queryEmployeeById(String id);

}
