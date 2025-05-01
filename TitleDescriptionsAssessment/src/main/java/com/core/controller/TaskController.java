package com.core.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.domain.Title;
import com.core.domain.TitleUpdateDTO;
import com.core.service.TitleService;


@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

	@Autowired
	TitleService titleService;
	
	
	@PostMapping
	public ResponseEntity<Title> createTitle(@RequestBody Title title) {
		Title createdTitle = titleService.createTitle(title);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTitle);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Title> getTitleById(@PathVariable Long id) {
		Optional<Title> title = titleService.getTitlesById(id);
		return title.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	
	@PutMapping("/{id}")
    public ResponseEntity<Title> updateTitle(@PathVariable Long id, @RequestBody TitleUpdateDTO updatedTitleDTO) { // Receive the DTO
            // 2. Update the title using the service
            return titleService.updateTitle(id, updatedTitleDTO);
    }
	
		
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long id) {
        if (titleService.deleteTitle(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content for successful deletion
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found if title doesn't exist
        }
    }
	
	@GetMapping("/describe/{id}")
    public ResponseEntity<String> getDescriptionById(@PathVariable Long id) {
        Optional<String> description = titleService.getDescriptionById(id);
        if (description.isPresent()) {
            String message = String.format("Description of Task [%d: %s] is %s",
                    id, titleService.getTitlesById(id).map(t -> t.getTitle()).orElse("N/A"), description.get());
            return ResponseEntity.ok(message);
        } else {
            String message = String.format("Task with id = %d doesn't exist", id);
            return ResponseEntity.ok(message); // Returning 200 as per requirement
        }
    }
	
	@GetMapping("/describe")
    public ResponseEntity<List<String>> getAllDescriptions() {
        List<String> descriptions = titleService.getAllDescriptions();
        if (!descriptions.isEmpty()) {
            return ResponseEntity.ok(descriptions);
        } else {
            return ResponseEntity.noContent().build(); // 204 No Content if no titles exist
        }
    }
	
	
}
