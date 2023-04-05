package com.example.samltest.controller;


import com.example.samltest.pojo.Department;
import com.example.samltest.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    private final HttpServletRequest request;
    @Autowired
    public EmployeeController(HttpServletRequest request) {
        this.request = request;
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }


    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        Employee emp1 = Employee.builder().name("aaa").role("user").id(String.valueOf(1)).build();
        Employee emp2 = Employee.builder().name("bbb").role("user").id(String.valueOf(2)).build();
        List<Employee> emps = new ArrayList<>();
        emps.add(emp1);
        emps.add(emp2);
        return new ResponseEntity<>(emps, HttpStatus.OK);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDeparts() {
        Department dept1 = Department.builder().name("HR").id(String.valueOf(1)).build();
        Department dept2 = Department.builder().name("Sales").id(String.valueOf(2)).build();
        List<Department> departments = new ArrayList<>();
        departments.add(dept1);
        departments.add(dept2);
        return new ResponseEntity<>(departments,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/access-denied-response")
    public String accessDenied() {
        return "Access Denied... You don't have permission.";
    }


    @GetMapping(value = "/logout")
    public RedirectView logout() throws ServletException {
        request.logout();
        return new RedirectView("departments");
    }
}
