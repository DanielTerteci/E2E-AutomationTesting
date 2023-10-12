package com.cegeka.academy.qa.json.model;

import java.util.List;

public class AddBooksRequestBody {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<IsbnModel> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<IsbnModel> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }
}
