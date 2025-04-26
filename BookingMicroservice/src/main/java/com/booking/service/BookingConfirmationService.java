package com.booking.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.repository.EmailRepository;
import com.booking.domain.Booking;


import jakarta.mail.MessagingException;

@Service
public class BookingConfirmationService {

    @Autowired
    private EmailRepository emailRepository;

    public String sendBookingConfirmation(Booking booking, String pdfSavePath) throws IOException, MessagingException {
    	
        return emailRepository.generatePdfAndSendEmail(booking, pdfSavePath);
    }
}