package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.repository.BookingRepository;
import com.booking.domain.Booking;
import com.booking.service.BookingService;

import java.io.File;  // Import for File

@RestController
@RequestMapping("/api/bookings")
public class EmailController {

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	BookingService bookingService;
	
	@Value("${pdf.save.path}")
	private String pdfSavePath;


    @GetMapping("/customer/{customerName}/pdf")
    public ResponseEntity<String> getBookingPdfAndEmail(@PathVariable String customerName, @PathVariable String content, 
    		@PathVariable String email) {
        try {
            // 1. Retrieve booking data
            Booking booking = bookingService.findByCustomerName(customerName);
            
            try {
    			
            	// Create customerName sub directory
            	pdfSavePath += "/" + booking.getCustomerName();
            	File directory = new File(pdfSavePath);
            	
            	if (!directory.exists())
            		directory.mkdirs();
            	
            	
            	// Call service method to generate PDF and send email
    			String response = bookingService.generatePdfAndSendEmail(booking, pdfSavePath);
    			return ResponseEntity.ok(response);
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    			return ResponseEntity.status(500).body("Error generating PDF or sending email.");
    		}
            

        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return new ResponseEntity<>("Error processing request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

