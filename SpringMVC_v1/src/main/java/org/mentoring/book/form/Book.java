package org.mentoring.book.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="Book")
public class Book {
     
    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
     
    @Column(name="Author")
    private String author;
 
    @Column(name="Title")
    private String title;
 
    @Column(name="Email")
    private String email;
     
    @Column(name="Pages")
    private Integer pages;
     
     
    public String getEmail() {
        return email;
    }
    public Integer getPages() {
        return pages;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}