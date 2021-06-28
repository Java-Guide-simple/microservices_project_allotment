package in.stack.microservices.allotment.controller;

import in.stack.microservices.allotment.model.Allotment;
import in.stack.microservices.allotment.service.AllotmentService;
import in.stack.microservices.allotment.vo.ResponseTempleteVO;
import in.stack.microservices.allotment.vo.ResultEmployeeHasProjects;
import in.stack.microservices.allotment.vo.ResultEmployeesHaveProject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    // Adding New Allotment Record
    @ApiOperation(value = "Add New Allotment Details",
            response = Allotment.class, tags = "Allotment")
    @PostMapping("/save")
    public Allotment saveAllotment(@RequestBody Allotment allotment) {
        log.info("Inside Controller#saveAllotment Method");
        return allotmentService.saveAllotment(allotment);
    }

    // Displaying Allotment Details BY Allotment ID
    @ApiOperation(value = "Get Allotment Details By Id",
            response = Allotment.class, tags = "Allotment")
    @GetMapping("/show/{id}")
    public Allotment findByIdAllotment(@PathVariable int id) {
        log.info("Inside Controller#findByIdAllotment Method");
        return allotmentService.findAllotmentById(id);
    }

    // Updating Allotment Details BY Sending Allotment Details With Existing Allotment (ID)
    @ApiOperation(value = "Update Allotment Details",
            response = Allotment.class, tags = "Allotment")
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
    @ApiOperation(value = "Delete Allotment Details By Id",
            response = Allotment.class, tags = "Allotment")
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
    @ApiOperation(value = "Get All Allotments Details",
            response = Allotment.class, tags = "Allotment")
    @GetMapping("/show")
    public List<Allotment> showAllAllotements() {
        log.info("Inside Controller#showAllAllotements Method");
        return allotmentService.showAllAllotment();
    }

    // Displaying Allotment Details With Project and Employee Details
    @ApiOperation(value = "Get Allotment Details With Employee and Project Detail By Allotment Id",
            response = ResponseTempleteVO.class, tags = "Allotment")
    @GetMapping("/show-details/{id}")
    public ResponseTempleteVO showDetails(@PathVariable int id) {
        log.info("Inside Controller#showDetails Method");
        return allotmentService.findAllotmentByIdwithFullDetails(id);
    }


    // Displaying Employees Details who are handling Particular Project
    @ApiOperation(value = "Displaying Employees Details who are handling Particular Project BY Project Id",
            response = ResultEmployeesHaveProject.class, tags = "Project")
    @GetMapping("/get-employee-byProjectid/{id}")
    public ResultEmployeesHaveProject getEmployee(@PathVariable int id) {
        log.info("Inside Controller#getEmployee Method");
        ResultEmployeesHaveProject employeesByProjectId = allotmentService.findEmployeesByProjectId(id);
        return employeesByProjectId;

    }

    // Displaying Projects Details which are handling By Particular Employee
    @ApiOperation(value = " Displaying Projects Details which are handling By Particular Employee By Employee Id",
            response =  ResultEmployeeHasProjects.class, tags = "Employee")
    @GetMapping("/get-projects-handleBy/{id}")
    public ResultEmployeeHasProjects getProjects(@PathVariable int id) {
        log.info("Inside Controller#getProjects Method");
        ResultEmployeeHasProjects employeeHavingProjects = allotmentService.findProjectsPerEmployee(id);
        return employeeHavingProjects;

    }


}
