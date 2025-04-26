package com.booking.repository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.properties.TextAlignment;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import com.booking.domain.Booking;
import com.booking.domain.BookingRules;

@Repository
public class EmailRepository {
	
	private final JavaMailSender emailSender;
	
	@Autowired
	public EmailRepository(JavaMailSender emailSender) {
		this.emailSender=emailSender;
	}

	public String generatePdfAndSendEmail(Booking booking, String pdfSavePath) throws IOException, MessagingException {
	    // Define the file path where PDF will be saved
	    
		pdfSavePath = pdfSavePath + "/booking_confirmation_" + System.currentTimeMillis() + ".pdf";
	    
		try {
			String pdfContent = generateBookingConfirmationContent(booking, pdfSavePath);
			
			// Send the email with the generated PDF as attachment
		    sendEmailWithAttachment(booking.getEmail(), booking.getCustomerName() + " Booking PDF Report", "Please find the attached PDF.", pdfContent);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return "PDF generated and email sent successfully!";
	}
	
	private String generateBookingConfirmationContent(Booking booking, String pdfSavePath) throws DocumentException, IOException {
    	Document document = new Document();
    	String filePath = pdfSavePath;
    	FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        PdfWriter.getInstance(document, fileOutputStream);
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        document.open();
        
        // Hotel Information
        Phrase hotelNamePhrase = new Phrase();
        hotelNamePhrase.add(new Phrase(booking.getHotelName(), boldFont));
        Paragraph paragraphHotelName = new Paragraph(hotelNamePhrase);
        paragraphHotelName.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphHotelName);
        
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        
        // Hotel Image
        try (InputStream imageStream = getClass().getClassLoader().getResourceAsStream(booking.getPathHotelPic())) {
            if (imageStream != null) {
                byte[] imageData = imageStream.readAllBytes();
                Image hotelImage = Image.getInstance(imageData);
                hotelImage.scaleToFit(500, 400); // Adjust scale as needed
                document.add(hotelImage);
                hotelImage.setAlignment(Element.ALIGN_CENTER);
            } else {
                document.add(new Paragraph("Image of Hotel (Resource: " + booking.getPathHotelPic() + ") - Not found in classpath."));
            }
        } catch (Exception e) {
            document.add(new Paragraph("Error loading hotel image: " + e.getMessage()));
        }
        
        document.add(new Paragraph("\n"));

        
        // Customer Information
        Phrase customerNamePhrase = new Phrase();
        customerNamePhrase.add(new Phrase("Customer Name: ", boldFont));
        customerNamePhrase.add(new Phrase(booking.getCustomerName(), regularFont));
        document.add(new Paragraph(customerNamePhrase));

        Phrase checkInPhrase = new Phrase();
        checkInPhrase.add(new Phrase("Check-In Date: ", boldFont));
        checkInPhrase.add(new Phrase(dateFormat.format(booking.getCheckInDate()), regularFont));
        document.add(new Paragraph(checkInPhrase));

        Phrase checkOutPhrase = new Phrase();
        checkOutPhrase.add(new Phrase("Check-Out Date: ", boldFont));
        checkOutPhrase.add(new Phrase(dateFormat.format(booking.getCheckOutDate()), regularFont));
        document.add(new Paragraph(checkOutPhrase));

        document.add(new Paragraph("\n"));
        
        
        // Hotel Rules
        Phrase phraseRules = new Phrase("\nHotel Rules:", boldFont);
        Paragraph paragraphHotelRules = new Paragraph(phraseRules);
        paragraphHotelRules.setAlignment(Element.ALIGN_CENTER);
        
        document.add(paragraphHotelRules);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        // Hotel Rules Table
        if (booking.getBookingRules() != null && !booking.getBookingRules().isEmpty()) {
            PdfPTable table = new PdfPTable(2); // 2 columns: Rule Name and Rule Description
            table.setWidthPercentage(100);
            table.addCell(new PdfPCell(new Paragraph("Rule Name")));
            table.addCell(new PdfPCell(new Paragraph("Rule Description")));

            for (BookingRules rule : booking.getBookingRules()) {
                table.addCell(new PdfPCell(new Paragraph(rule.getRuleName())));
                table.addCell(new PdfPCell(new Paragraph(rule.getRuleDescription())));
            }
            document.add(table);
        } else {
            document.add(new Paragraph("No specific rules found for this booking."));
        }

        document.close();
        fileOutputStream.close();

        return filePath;
    }


	public String sendEmailWithAttachment(String to, String subject, String text, String filePath) throws MessagingException {
	    MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    // Set email details
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text);

	    // Attach the PDF file
	    File pdfFile = new File(filePath);
	    helper.addAttachment(pdfFile.getName(), pdfFile);

	    emailSender.send(message);
	    
	    return "PDF generated and email sent successfully!";
	}

}
