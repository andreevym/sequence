package com.example.user.myapplication;

import java.util.Date;

public class User {
    private String name;
    private Date date;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(String name, Date date, Integer id) {
        super();
        this.name = name;
        this.date = date;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(! (o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.getId());
    }
}