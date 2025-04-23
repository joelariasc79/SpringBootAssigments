package com.article.controller;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.article.domain.Article;
import com.article.service.ArticleService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;




@RestController

@RequestMapping(value = "/articles")
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@Autowired
    private ObjectMapper objectMapper; // Inject Jackson ObjectMapper
	
	
	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)
	public JsonNode createArticle(@RequestBody Article article) {
		Article savedArticle = articleService.saveArticle(article);
		
		// Create a JsonNode
        ObjectNode responseNode = objectMapper.createObjectNode();
        responseNode.put("ArticleId", savedArticle.getArticleId());
        responseNode.put("contents", savedArticle.getContents());
        responseNode.put("likes", savedArticle.getLikes());     
		
		return responseNode;
	}
	
	
	@GetMapping("/{ArticleId}")
	public ResponseEntity<Article> getArticleById(@PathVariable int ArticleId) {
		Optional<Article> article = articleService.getArticleById(ArticleId);
		return article.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	@PutMapping("/incrementLikes/{articleId}")
	public String incrementLikes(@PathVariable long articleId) {
		articleService.incrementLikes(articleId);
		return "Likes updated";
	}
	
	@DeleteMapping("/{articleId}")
	public String deleteArticle(@PathVariable long articleId) {
		articleService.deleteArticle(articleId);
		return "Deleted";
	}
	

}

