package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Students;
import com.synex.service.StudentsService;

@RestController
public class StudentsController {

	@Autowired 
	StudentsService studentsService;
	
	@RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
	public Students saveBooks(@RequestBody Students student) {
		return studentsService.saveStudent(student);
		
	}
	
	
	@RequestMapping(value = "/getStudents", method = RequestMethod.GET)
	public List<Students> getStudents() {
		return studentsService.getStudents();
		
	}
	

	
}
