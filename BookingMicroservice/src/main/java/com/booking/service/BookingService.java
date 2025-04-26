package com.booking.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.domain.Booking;
import com.booking.repository.BookingRepository;
import com.booking.repository.EmailRepository;
import com.booking.service.BookingConfirmationService;
import com.itextpdf.text.DocumentException;

import jakarta.mail.MessagingException;


@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	BookingConfirmationService bookingConfirmationService;
	
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	public Booking findByCustomerName(String customerName) {
		return bookingRepository.findByCustomerName(customerName);
	}
	
	public String generatePdfAndSendEmail(Booking booking, String pdfSavePath) throws IOException, MessagingException {
		try {
			return bookingConfirmationService.sendBookingConfirmation(booking, pdfSavePath);
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
		return pdfSavePath;
    }
	
}
