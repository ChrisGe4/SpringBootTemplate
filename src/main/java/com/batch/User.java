package com.batch;

import com.google.common.base.MoreObjects;

/**
 * @author Chris.Ge
 */
public class User {

    private String first, last, email;

    public User() {
    }

    public User(String first, String last, String email) {
        this.first = first;
        this.last = last;
        this.email = email;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("first", first).add("last", last)
            .add("email", email).toString();
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
