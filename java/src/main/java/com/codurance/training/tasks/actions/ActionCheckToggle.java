package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Task;

import java.util.List;
import java.util.Map;

public abstract class ActionCheckToggle extends Action {
    private final Map<String, List<Task>> tasks;
    private int id;

    public ActionCheckToggle(Map<String, List<Task>> tasks,Console console, String id) {
        super(console);
        this.id = Integer.parseInt(id);
        this.tasks = tasks;
    }

    protected void setDone(boolean done) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == this.id) {
                    task.setDone(done);
                    return;
                }
            }
        }

        this.console.printf("Could not find a task with an ID of %d.", id);
        this.console.print("");
    }
}
