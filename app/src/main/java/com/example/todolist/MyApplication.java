package com.example.todolist;

import android.app.Application;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private List<Task> task=new ArrayList<>();

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
