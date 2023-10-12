package com.cegeka.academy.qa.json.model;

public class DeleteBookRequestBody {
    private String userId;
    private String isbn;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
