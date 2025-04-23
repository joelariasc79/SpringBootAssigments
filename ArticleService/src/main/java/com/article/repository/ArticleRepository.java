package com.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.article.domain.Article;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{
		
		@Transactional
	    @Modifying
	    @Query("UPDATE Article a SET a.likes = a.likes + 1 WHERE a.articleId = :articleId")
	    public void incrementLikes(@Param("articleId") long articleId);
}
