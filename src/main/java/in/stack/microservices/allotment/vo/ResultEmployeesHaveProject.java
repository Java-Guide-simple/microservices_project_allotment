package in.stack.microservices.allotment.vo;


import in.stack.microservices.allotment.model.Employee;
import in.stack.microservices.allotment.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

// This class is using To Display Employees Details Who are handling particular Project
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEmployeesHaveProject {
    private Set<Employee> Employees;
    private Project project;

}
