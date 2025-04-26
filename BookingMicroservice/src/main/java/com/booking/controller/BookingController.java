package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.domain.Booking;
import com.booking.repository.BookingRepository;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	EmailController emailController;
	
	@Autowired
	BookingRepository bookingRepository;
	
	
	// Create a new booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        try {
        	Booking savedBooking = bookingRepository.save(booking);       
        	emailController.getBookingPdfAndEmail(savedBooking.getCustomerName(), "hola", savedBooking.getEmail());
        	
        	
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

