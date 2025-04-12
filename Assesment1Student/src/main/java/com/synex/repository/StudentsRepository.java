package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer>{
	

}
