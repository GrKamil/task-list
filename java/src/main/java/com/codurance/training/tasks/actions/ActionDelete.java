package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;

import java.util.Map;

public class ActionDelete extends Action {
    public ActionDelete(Console console, String command) {
        super(console);
    }

    @Override
    public void execute() {
        int id = Integer.parseInt(command);
        for (Map.Entry<String, Project> tasksEntry : tasks.entrySet()) {
            Project project = tasksEntry.getValue();
            Task task = project.getTaskById(id);
            if (task != null) {
                project.deleteTaskById(id);
            }
        }

        this.console.printf("Could not find a task with an ID of %d.", id);
    }
}
