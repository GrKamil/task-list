package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActionAddProject extends Action {
    private final Map<String, List<Task>> tasks;

    public ActionAddProject(Map<String, List<Task>> tasks, Console console) {
        super(console);
        this.tasks = tasks;
    }

    @Override
    public void execute() {
//        String[] subcommandRest = commandLine.split(" ", 2);
//        String subcommand = subcommandRest[0];

        this.tasks.put("", new ArrayList<Task>());
    }
}
