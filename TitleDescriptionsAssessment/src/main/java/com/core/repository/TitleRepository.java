package com.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.domain.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer>{

	
}
