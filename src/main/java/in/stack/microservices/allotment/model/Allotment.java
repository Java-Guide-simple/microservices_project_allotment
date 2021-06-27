package in.stack.microservices.allotment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Allotment {

    @Id
    @GeneratedValue
    private int aid;
    private int employeeId;
    private int projectId;
    private int contribution;


}
