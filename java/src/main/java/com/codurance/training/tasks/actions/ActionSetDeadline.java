package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;

import java.time.LocalDate;
import java.util.Map;

public class ActionSetDeadline extends Action {
    private final long id;
    private final LocalDate deadline;

    public ActionSetDeadline(long taskId, LocalDate deadline) {
        this.id = taskId;
        this.deadline = deadline;
    }

    @Override
    public void execute(CommandContext ctx) {
        boolean taskExists = false;

        for (Map.Entry<String, Project> project : ctx.getProjects().entrySet()) {
            for (Task task : project.getValue().getAllTasks()) {
                if (task.getId() == this.id) {
                    taskExists = true;
                    task.setDeadline(deadline);
                }
            }
        }

        if (!taskExists) {
            ctx.getConsole().print("Task is not exist");
        }
    }
}
