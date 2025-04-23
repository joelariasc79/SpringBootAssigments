package com.article.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.article.domain.Article;
import com.article.repository.ArticleRepository;



@Service
public class ArticleService {

	@Autowired
	ArticleRepository articleRepository;
	
	
	public Article saveArticle(Article article) {	
		return articleRepository.save(article);
	}
	
	@Cacheable("articles")
	public Optional<Article> getArticleById(int articleId) {
        return articleRepository.findById(Math.toIntExact(articleId));
    }

	
	@CacheEvict(value="articles", key="#article.articleId")
	public void deleteArticle(long articleId) {
		System.out.println("Deleting from DB & Cache" + articleId);
		articleRepository.deleteById(Math.toIntExact(articleId));
	}
	
	
	@CachePut(value="articles", key = "#article.articleId")
	public void incrementLikes(long articleId) {
		System.out.println("Updating DB and Cache"+ articleId);
		articleRepository.incrementLikes(articleId);
	}
}
