package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/emp")

public class democontroller {
    @Autowired
    demoservice ds;

    @PostMapping("/addemp")
    public String addemp(@RequestBody demo p){
        String add=ds.addemp(p);
        return add;
    }
    @PostMapping("/addproject")
    public String addproject(@RequestBody demo p){
        String add=ds.addproject(p);
        return add;
    }
    
    @PostMapping("/addproj_emp")
    public String addproj_emp(@RequestBody demo p){
        String add=ds.addproj_emp(p);
        return add;
    }

    @PostMapping("/details")
    public Map<Integer, List<String>> getProjectDetails() {
        return ds.details();
    }

    @PostMapping("/task")
    public String tasklist(@RequestBody demo p){
        String add=ds.tasklist(p);
        return add;
    }

    @PostMapping("/det")
    public List select_PDetails(@RequestBody demo p){
        return ds.select_PDetails(p);

    }
    
}
