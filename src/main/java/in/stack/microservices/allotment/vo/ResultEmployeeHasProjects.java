package in.stack.microservices.allotment.vo;


import in.stack.microservices.allotment.model.Employee;
import in.stack.microservices.allotment.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

// This class is using To Display Projects Details Which are Handling By Particular Employee
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEmployeeHasProjects {


    private Employee employee;
    private Set<Project> projects;
}
