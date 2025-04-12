package com.synex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Students;
import com.synex.repository.StudentsRepository;

@Service
public class StudentsService {

	@Autowired
	StudentsRepository studentsRepository;
	
	public Students saveStudent(Students student) {
		
		return studentsRepository.save(student);
		
	}
	
	public List<Students> getStudents() {
		
		return studentsRepository.findAll();
		
	}
	
	
}
