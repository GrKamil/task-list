package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;

import java.time.LocalDate;
import java.util.Map;

public class ActionDeadline extends Action {
    public ActionDeadline(Console console, String command) {
        super(console);
    }

    @Override
    public void execute(Map<String, Project> tasks) {
        String[] subcommandRest = command.split(" ", 2);

        int id = Integer.parseInt(subcommandRest[0]);
        LocalDate deadline = LocalDate.parse(subcommandRest[1]);

        for (Map.Entry<String, Project> tasksEntry : tasks.entrySet()) {
            Project project = tasksEntry.getValue();
            Task task = project.getTaskById(id);

            if (task != null) {
                task.setDeadline(deadline);
            }
        }

        this.console.printf("Could not find a task with an ID of %d.", id);
    }
}