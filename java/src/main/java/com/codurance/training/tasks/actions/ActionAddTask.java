package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskRepository;

public class ActionAddTask extends Action {
    private final String projectName;
    private final String description;

    public ActionAddTask(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }

    @Override
    public void execute(CommandContext ctx) {
        Project project = ctx.getProjects().get(this.projectName);

        if (project == null) {
            ctx.getConsole().printf("Could not find a project with the name \"%s\".", project);
            ctx.getConsole().print("");
            return;
        }

        Task task = new Task(description, false);
        project.addTask(task);
    }
}
