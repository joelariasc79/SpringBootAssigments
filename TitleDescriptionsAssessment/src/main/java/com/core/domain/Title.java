// Domain (Title.java)
package com.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Title {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String title;
 private String description;

 @Enumerated(EnumType.STRING)
 private Status status;

 // Default constructor (required by JPA)
 public Title() {
     this.status = Status.APPROVED; // Default status
 }

 public Title(String title, String description) {
     this.title = title;
     this.description = description;
     this.status = Status.APPROVED; // Default status
 }

 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public String getTitle() {
     return title;
 }

 public void setTitle(String title) {
     this.title = title;
 }

 public String getDescription() {
     return description;
 }

 public void setDescription(String description) {
     this.description = description;
 }

 public Status getStatus() {
     return status;
 }

 public void setStatus(Status status) {
     this.status = status;
 }

 @Override
 public String toString() {
     return "Title{" +
             "id=" + id +
             ", title='" + title + '\'' +
             ", description='" + description + '\'' +
             ", status=" + status +
             '}';
 }
}