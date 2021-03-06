package com.thoughtworks.lhli.lovelive_rock.model;

import com.google.gson.annotations.SerializedName;

public class MultipleCards {

    @SerializedName("count")
    private Integer count;

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    private CardModel[] results;

    public MultipleCards(Integer count, String next, String previous, CardModel[] results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public MultipleCards() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public CardModel[] getResults() {
        return results;
    }

    public void setResults(CardModel[] results) {
        this.results = results;
    }
}
