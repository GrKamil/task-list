package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Task;

import java.util.List;
import java.util.Map;

public class ActionUncheck extends ActionCheckToggle {

    public ActionUncheck(Map<String, List<Task>> tasks, Console console, String id) {
        super(tasks, console, id);
    }

    @Override
    public void execute() {
        this.setDone(false);
    }
}
