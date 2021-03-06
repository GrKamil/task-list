package com.codurance.training.tasks;

public class TaskRepository {
    private long lastId;
    public static TaskRepository instance = new TaskRepository();

    public static synchronized TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }

        return instance;
    }

    public TaskRepository() {
        this.lastId = 0;
    }

    public long getNextId() {
        return ++this.lastId;
    }
}
