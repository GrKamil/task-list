package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Task;

import java.util.List;
import java.util.Map;

public class ActionAddTask extends Action {
    private final Map<String, List<Task>> tasks;
    private String project;
    private String description;
    private final long id;

    public ActionAddTask(Map<String, List<Task>> tasks, String description, String project, long id, Console console) {
        super(console);
        this.tasks = tasks;
        this.project = project;
        this.description = description;
        this.id = id;
    }

    @Override
    public void execute() {
        List<Task> projectTasks = tasks.get(project);

        if (projectTasks == null) {
            this.console.printf("Could not find a project with the name \"%s\".", project);
            this.console.print("");
            return;
        }

        projectTasks.add(new Task(this.id, this.description, false));
    }
}
