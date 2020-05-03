package com.codurance.training.tasks;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Project {
    private final Map<Long, Task> tasks = new LinkedHashMap<>();
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public Task getTaskById(long id) {
        return tasks.get(id);
    }

    public Task deleteTaskById(long id) {
        return tasks.remove(id);
    }

    public Collection<Task> getAllTasks() {
        return tasks.values();
    }
}
