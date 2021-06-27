package in.stack.microservices.allotment.service;

import in.stack.microservices.allotment.model.Allotment;
import in.stack.microservices.allotment.model.Employee;
import in.stack.microservices.allotment.model.Project;
import in.stack.microservices.allotment.repository.AllotmentRepository;
import in.stack.microservices.allotment.vo.ResponseTempleteVO;
import in.stack.microservices.allotment.vo.ResultEmployeeHasProjects;
import in.stack.microservices.allotment.vo.ResultEmployeesHaveProject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AllotmentService {

    @Autowired
    private AllotmentRepository allotmentRepository;


    @Autowired
    private RestTemplate restTemplate;


    // Displaying Allotment Details BY Allotment ID
    public Allotment findAllotmentById(int id) {
        log.info("Inside AllotmentService#findAllotmentById Method");
        return allotmentRepository.findByAid(id);

    }


    // Adding New Allotment Record
    public Allotment saveAllotment(Allotment allotment) {
        log.info("Inside AllotmentService#saveAllotment Method");
        return allotmentRepository.save(allotment);
    }


    // Updating Allotment Details BY Sending Allotment Details With Existing Allotment (ID)
    public Allotment updateAllotment(Allotment allotment) {
        log.info("Inside AllotmentService#updateAllotment Method");
        Boolean b = allotmentRepository.existsById(allotment.getAid());
        if (b) {
            Allotment updated = allotmentRepository.saveAndFlush(allotment);
            return updated;
        } else {
            return null;
        }

    }


    // Deleting Allotment By Id
    public Boolean deleteAllotment(int id) {
        log.info("Inside AllotmentService#deleteAllotment Method");
        Boolean b = allotmentRepository.existsById(id);
        if (b) {
            allotmentRepository.deleteById(id);
        }
        return b;
    }


    // Displaying All Allotmemt Details
    public List<Allotment> showAllAllotment() {
        log.info("Inside AllotmentService#showAllAllotment Method");
        return allotmentRepository.findAll();
    }


    // Displaying Allotment Details With Project and Employee Details
    public ResponseTempleteVO findAllotmentByIdwithFullDetails(int id) {
        log.info("Inside AllotmentService#findAllotmentByIdwithFullDetails Method");
        ResponseTempleteVO result = new ResponseTempleteVO();
        Allotment allotment = allotmentRepository.findByAid(id);
        int employee_id = allotment.getEmployeeId();
        int project_id = allotment.getProjectId();
        Employee employee = restTemplate.getForObject("http://localhost:8080/api/employee/get-one/" + employee_id, Employee.class);
        Project project = restTemplate.getForObject("http://localhost:8083/api/project/get-one/" + project_id, Project.class);
        result.setAllotment(allotment);
        result.setEmployee(employee);
        result.setProject(project);
        return result;
    }


    // Displaying Employees Details who are handling Particular Project
    public ResultEmployeesHaveProject findEmployeesByProjectId(int project_id) {
        log.info("Inside AllotmentService#findEmployeesByProjectId Method");
        // fetching the Project details for provided Project Id
        Project project = restTemplate.getForObject("http://localhost:8083/api/project/get-one/" + project_id, Project.class);
        // fetching List of Allotments for provided Project Id
        List<Allotment> allotments = allotmentRepository.findAllByProjectId(project_id);
        Set<Employee> employees = new HashSet<>();

        // fetching Employees Details who are the handling the Provided Project Id
        allotments.stream().forEach(allotment ->
                /*
                    fetching one by one Employee details who is handling provided Project Id
                    and adding that details in employees (Set<Employee>)
                 */
                employees.add(restTemplate.getForObject("http://localhost:8080/api/employee/get-one/" + allotment.getEmployeeId(), Employee.class))

        );

        // instance of ResultEmployeesHaveProject
        ResultEmployeesHaveProject result = new ResultEmployeesHaveProject();
        // initializing the properties of ResultEmployeesHaveProject class

        // setting the value in the Project class instance
        result.setProject(project);
        // setting the value in the Set<Employee>  instance
        result.setEmployees(employees);

        return result;
    }


    // Displaying Projects Details which are handling By Particular Employee
    public ResultEmployeeHasProjects findProjectsPerEmployee(int id) {
        log.info("Inside AllotmentService#findProjectsPerEmployee Method");
        // List Of Allotments for Provided Employee ID
        List<Allotment> allotments = allotmentRepository.findAllByEmployeeId(id);
        // Employee Details For Provided Employee Id
        Employee employee = restTemplate.getForObject("http://localhost:8080/api/employee/get-one/" + id, Employee.class);

        Set<Project> projects = new HashSet<>();

        // Projects Details which are handling by Provided Employee Id
        allotments.stream().forEach(allotment ->
                /* fetching one by one project details which is handling by provided employee Id
                    adding that project details in instance of Set
                 */
                projects.add(restTemplate.getForObject("http://localhost:8083/api/project/get-one/" + allotment.getProjectId(), Project.class))

        );

        // instance of ResultEmployeeHasProjects
        ResultEmployeeHasProjects result = new ResultEmployeeHasProjects();
        // initializing the properties of ResultEmployeeHasProjects class

        // setting the value in the Set<Project> instance
        result.setProjects(projects);
        // setting the value in the Employee class instance
        result.setEmployee(employee);

        return result;

    }


}
