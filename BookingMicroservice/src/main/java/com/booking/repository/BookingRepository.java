package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.domain.Booking;



@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
	
	public Booking findByCustomerName(String customerName);

}
