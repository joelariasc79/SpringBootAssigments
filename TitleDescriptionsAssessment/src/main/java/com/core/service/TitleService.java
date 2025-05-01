package com.core.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.core.domain.Title;
import com.core.domain.Status;
import com.core.domain.TitleUpdateDTO;
import com.core.repository.TitleRepository;

@Service
public class TitleService {

	
	@Autowired
	TitleRepository titleRepository;
	
	public Title createTitle(Title title) {
		return titleRepository.save(title);
	}
	
	
	public Optional<Title> getTitlesById(Long id) {
    	// This don't need to be implemented in the repository
        return titleRepository.findById(Math.toIntExact(id));
    }
	
	
	public ResponseEntity<Title> updateTitle(Long id, TitleUpdateDTO updatedTitleDTO) {
        try{
            // 1. Validate the status string
            Status status = Status.valueOf(updatedTitleDTO.getStatus()); // This can throw IllegalArgumentException
            Optional<Title> existingTitle = titleRepository.findById(Math.toIntExact(id));
            
            if (existingTitle.isPresent()) {
                Title title = existingTitle.get();
                title.setTitle(updatedTitleDTO.getTitle());
                title.setDescription(updatedTitleDTO.getDescription());
                title.setStatus(status); // Use the validated status
                Title savedTitle = titleRepository.save(title);
                return new ResponseEntity<>(savedTitle, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.OK).body(createInvalidStatusResponse());
        }

    }
    private Title createInvalidStatusResponse() {
        Title errorResponse = new Title();
        errorResponse.setTitle("Error");
        errorResponse.setDescription("Invalid status provided. Available statuses are: " + Arrays.toString(Status.values()));
        return errorResponse;
    }
	
    
    public boolean deleteTitle(Long id) {
        if (titleRepository.existsById((Math.toIntExact(id)))) {
            titleRepository.deleteById((Math.toIntExact(id)));
            return true;
        }
        return false;
    }
    
    public Optional<String> getDescriptionById(Long id) {
        return titleRepository.findById((Math.toIntExact(id)))
                .map(Title::getDescription);
    }
    
    public List<String> getAllDescriptions() {
    	
    	
        return titleRepository.findAll().stream()
                .map(title -> String.format("Description of Task [%d: %s] is %s",
                        title.getId(), title.getTitle(), title.getDescription()))
                .collect(Collectors.toList());
    }
    

}
