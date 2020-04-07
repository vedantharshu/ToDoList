package com.example.todolist;

import android.widget.CheckBox;

public class Task {
    String date,description;


    public Task(String date, String description) {
        this.date = date;

        this.description = description;
    }



    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
