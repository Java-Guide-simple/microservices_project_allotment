package in.stack.microservices.allotment.controller;

import in.stack.microservices.allotment.model.Allotment;
import in.stack.microservices.allotment.service.AllotmentService;
import in.stack.microservices.allotment.vo.ResponseTempleteVO;
import in.stack.microservices.allotment.vo.ResultEmployeeHasProjects;
import in.stack.microservices.allotment.vo.ResultEmployeesHaveProject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allotment")
@Slf4j
public class AllotmentController {

    @Autowired
    private AllotmentService allotmentService;

    // Adding New Allotment Record
    @PostMapping("/save")
    public Allotment saveAllotment(@RequestBody Allotment allotment) {
        log.info("Inside Controller#saveAllotment Method");
        return allotmentService.saveAllotment(allotment);
    }

    // Displaying Allotment Details BY Allotment ID
    @GetMapping("/show/{id}")
    public Allotment findByIdAllotment(@PathVariable int id) {
        log.info("Inside Controller#findByIdAllotment Method");
        return allotmentService.findAllotmentById(id);
    }

    // Updating Allotment Details BY Sending Allotment Details With Existing Allotment (ID)
    @PutMapping("/update")
    public String updateAllotment(@RequestBody Allotment allotment) {
        log.info("Inside Controller#updateAllotment Method");
        Allotment updated = allotmentService.updateAllotment(allotment);
        if (updated != null)
            return "Record Updated Successfully";
        else
            return "Something went Wrong";

    }

    // Deleting Allotment By Id
    @DeleteMapping("/delete/{id}")
    public String deleteAllotment(@PathVariable int id) {
        log.info("Inside Controller#deleteAllotment Method");
        Boolean deleted = allotmentService.deleteAllotment(id);
        if (deleted)
            return "Record Deleted Successfully";
        else
            return "Something went Wrong";
    }

    // Displaying All Allotmemt Details
    @GetMapping("/show")
    public List<Allotment> showAllAllotements() {
        log.info("Inside Controller#showAllAllotements Method");
        return allotmentService.showAllAllotment();
    }

    // Displaying Allotment Details With Project and Employee Details
    @GetMapping("/show-details/{id}")
    public ResponseTempleteVO showDetails(@PathVariable int id) {
        log.info("Inside Controller#showDetails Method");
        return allotmentService.findAllotmentByIdwithFullDetails(id);
    }

    // Displaying Employees Details who are handling Particular Project
    @GetMapping("/get-employee-byProjectid/{id}")
    public ResultEmployeesHaveProject getEmployee(@PathVariable int id) {
        log.info("Inside Controller#getEmployee Method");
        ResultEmployeesHaveProject employeesByProjectId = allotmentService.findEmployeesByProjectId(id);
        return employeesByProjectId;

    }

    // Displaying Projects Details which are handling By Particular Employee
    @GetMapping("/get-projects-handleBy/{id}")
    public ResultEmployeeHasProjects getProjects(@PathVariable int id) {
        log.info("Inside Controller#getProjects Method");
        ResultEmployeeHasProjects employeeHavingProjects = allotmentService.findProjectsPerEmployee(id);
        return employeeHavingProjects;

    }


}
