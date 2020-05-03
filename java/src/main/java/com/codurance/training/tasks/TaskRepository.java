package com.codurance.training.tasks;

public class TaskRepository {
    private long lastId;

    public TaskRepository() {
        this.lastId = 0;
    }

    public long getNextId() {
        return this.getLastId() + 1;
    }

    public long getLastId() {
        return this.lastId;
    }
}
