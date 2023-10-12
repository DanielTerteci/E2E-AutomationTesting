package com.cegeka.academy.qa.json.model;

import com.cegeka.academy.qa.models.Book;

import java.util.List;

public class GetBooksRequestBody {

    private String isbn;
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
