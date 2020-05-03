package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActionAddProject extends Action {
    private final Map<String, List<Task>> tasks;
    private String name;

    public ActionAddProject(Map<String, List<Task>> tasks, Console console, String name) {
        super(console);
        this.tasks = tasks;
        this.name = name;
    }

    @Override
    public void execute() {
        this.tasks.put(this.name, new ArrayList<Task>());
    }
}
