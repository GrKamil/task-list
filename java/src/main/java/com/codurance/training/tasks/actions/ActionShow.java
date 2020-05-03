package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActionShow extends Action {
    private final Map<String, List<Task>> tasks;

    public ActionShow(Map<String, List<Task>> tasks, Console console) {
        super(console);
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            this.console.print(project.getKey());
            for (Task task : project.getValue()) {
                this.console.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            this.console.print("");
        }
    }
}
