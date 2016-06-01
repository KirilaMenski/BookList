package by.ansgar.android.booklist.entity;

import java.util.UUID;

/**
 * Created by kirila on 27.5.16.
 */
public class Book {

    private UUID id;
    private String book;
    private String author;

    public Book(){

    }

    public Book(UUID id){
        this.id = id;
    }

    public Book(UUID id, String book, String author){
        this.id = id;
        this.book = book;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
