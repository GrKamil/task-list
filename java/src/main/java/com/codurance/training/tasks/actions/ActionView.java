package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;

import java.time.LocalDate;
import java.util.Map;

public class ActionView extends Action {
    private LocalDate date;
    private String projectName;

    @Override
    public void execute(CommandContext ctx) {
        String[] subcommands = ctx.getCommand().split(" ");
        String viewType = subcommands[1];

        switch (viewType) {
            case "date":
                this.date = LocalDate.parse(subcommands[2]);
                this.viewByDate(ctx);
                break;
            case "deadline":
                this.date = LocalDate.parse(subcommands[2]);
                this.viewByDeadline(ctx);
                break;
            case "project":
                this.projectName = subcommands[2];
                this.viewByProject(ctx);
                break;
            default:
                ctx.getConsole().print("Unknown view type");
                break;
        }
    }

    private void viewByDate(CommandContext ctx) {
        for (Map.Entry<String, Project> project : ctx.getProjects().entrySet()) {
            for (Task task : project.getValue().getAllTasks()) {
                if (this.date.equals(task.getDeadline())) {
                    this.printTask(ctx, task);
                }
            }
        }
    }

    private void viewByDeadline(CommandContext ctx) {
        for (Map.Entry<String, Project> project : ctx.getProjects().entrySet()) {
            for (Task task : project.getValue().getAllTasks()) {
                if (this.date.compareTo(task.getDeadline()) >= 0) {
                    this.printTask(ctx, task);
                }
            }
        }
    }

    private void viewByProject(CommandContext ctx) {
        Project project = ctx.getProjects().get(this.projectName);
        if (project == null) {
            ctx.getConsole().print("No such project");
            return;
        }

        for (Task task : project.getAllTasks()) {
            this.printTask(ctx, task);
        }
    }

    private void printTask(CommandContext ctx, Task task) {
        ctx.getConsole().printf(
                "    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '),
                task.getId(),
                task.getDescription()
        );
    }
}
