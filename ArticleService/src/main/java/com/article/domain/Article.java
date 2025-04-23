package com.article.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class) // Enable JPA auditing
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long articleId;
    private String contents;
    private int likes;


    public Article() {
        super();
    }


	public Article(long id, String contents, int likes) {
		super();
		this.articleId = id;
		this.contents = contents;
		this.likes = likes;
	}

	// Getters and setters for all fields


	public String getContents() {
		return contents;
	}

	public long getArticleId() {
		return articleId;
	}


	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

    
}