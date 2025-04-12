package com.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.domain.Hotels;
import com.hotel.repository.HotelRepository;


@Service
public class HotelService {

	@Autowired
	HotelRepository hotelsRepository;
	
	public Hotels saveHotels(Hotels hotels) {
		
		return hotelsRepository.save(hotels);
		
	}
	
}
