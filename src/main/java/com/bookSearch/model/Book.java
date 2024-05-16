package com.bookSearch.model;

public class Book {
    private Integer book_id;

    private String title;

    private String author;

    private Integer total_copies;

    private Integer available_copies;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getTotal_copies() {
        return total_copies;
    }

    public void setTotal_copies(Integer total_copies) {
        this.total_copies = total_copies;
    }

    public Integer getAvailable_copies() {
        return available_copies;
    }

    public void setAvailable_copies(Integer available_copies) {
        this.available_copies = available_copies;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", total_copies=" + total_copies +
                ", available_copies=" + available_copies +
                '}';
    }
}