package in.stack.microservices.allotment.repository;

import in.stack.microservices.allotment.model.Allotment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllotmentRepository extends JpaRepository<Allotment, Integer> {

    public Allotment findByAid(int id);

    // Finding List OF allotment By Project Id
    public List<Allotment> findAllByProjectId(int id);

    // Finding List OF allotment By Employee Id
    public List<Allotment> findAllByEmployeeId(int id);


//    @Query("Select a.employeeId from allotment a where a.projectId=:projectId")
//    public int[] findAllByProjectId(@Param("projectId") int projectId);


}
