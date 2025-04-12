package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synex.model.Hotel;


@Controller
public class HomeController {
	@Autowired
    private RestTemplate restTemplate;
	
	private final String travelGigAddHotelUrl = "http://localhost:8383/addHotel"; 

	@GetMapping("/hotelForm")
    public String showForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotelForm"; // refers to hotelForm.jsp
    }

    @PostMapping("/submitHotel")
    public String submitForm(@ModelAttribute("hotel") Hotel hotel, Model model) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode hotelJsonNode = objectMapper.valueToTree(hotel);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<JsonNode> requestEntity = new HttpEntity<>(hotelJsonNode, headers);

            JsonNode response = restTemplate.postForObject(travelGigAddHotelUrl, requestEntity, JsonNode.class);

            if (response != null) {
                System.out.println("Response from /travelGig/addHotel: " + response);
            }

            model.addAttribute("message", "Hotel submitted successfully!");

        } catch (Exception e) {
            model.addAttribute("message", "Failed to submit hotel: " + e.getMessage());

            e.printStackTrace();
        }
        return "hotelResult"; // refers to hotelResult.jsp
    }

}
