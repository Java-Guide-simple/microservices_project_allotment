package in.stack.microservices.allotment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This Class mapped with Client Webservice employee http://localhost:8080/api/employee/get-one/{id}
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private int id;
    private String name;
    private String designation;
    private String profile_pic;
}