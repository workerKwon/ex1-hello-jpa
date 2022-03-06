package hellojpa.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item {
    
    private String author;
    private String isbn;

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
