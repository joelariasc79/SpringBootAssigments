package com.booking.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class) // Enable JPA auditing
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hotelName;
    private String pathHotelPic;
    private Date checkInDate;
    private Date checkOutDate;
    private String customerName;
    private String email;
    private String mobile;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<BookingRules> bookingRules; 
    

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime bookingDate;

    

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Booking(int id, String hotelName, String pathHotelPic, Date checkInDate, Date checkOutDate,
			String customerName, String email, String mobile, List<BookingRules> bookingRules, LocalDateTime bookingDate) {
		super();
		this.id = id;
		this.hotelName = hotelName;
		this.pathHotelPic = pathHotelPic;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.customerName = customerName;
		this.email = email;
		this.mobile = mobile;
		this.bookingRules = bookingRules;
		this.bookingDate = bookingDate;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getHotelName() {
		return hotelName;
	}



	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}



	public String getPathHotelPic() {
		return pathHotelPic;
	}



	public void setPathHotelPic(String pathHotelPic) {
		this.pathHotelPic = pathHotelPic;
	}



	public Date getCheckInDate() {
		return checkInDate;
	}



	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}



	public Date getCheckOutDate() {
		return checkOutDate;
	}



	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public LocalDateTime getBookingDate() {
		return bookingDate;
	}



	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}



	public List<BookingRules> getBookingRules() {
		return bookingRules;
	}



	public void setBookingRules(List<BookingRules> bookingRules) {
		this.bookingRules = bookingRules;
	}
	
	
	
	

    
}