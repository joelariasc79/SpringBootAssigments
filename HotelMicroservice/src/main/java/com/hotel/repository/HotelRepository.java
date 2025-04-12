package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.domain.Hotels;


@Repository
public interface HotelRepository extends JpaRepository<Hotels, Integer>{

}
