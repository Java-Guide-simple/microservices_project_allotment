package in.stack.microservices.allotment.vo;

import in.stack.microservices.allotment.model.Allotment;
import in.stack.microservices.allotment.model.Employee;
import in.stack.microservices.allotment.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class is using To Display Allotment Details With Employee and Project Details
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTempleteVO {
    private Allotment allotment;
    private Employee employee;
    private Project project;


}
