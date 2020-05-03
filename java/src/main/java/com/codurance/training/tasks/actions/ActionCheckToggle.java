package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;

import java.util.List;
import java.util.Map;

public abstract class ActionCheckToggle extends Action {
    private final long id;

    public ActionCheckToggle(long id) {
        this.id = id;
    }

    protected void setDone(CommandContext ctx, boolean done) {
        for (Map.Entry<String, Project> project : ctx.getProjects().entrySet()) {
            for (Task task : project.getValue().getAllTasks()) {
                if (task.getId() == this.id) {
                    task.setDone(done);
                    return;
                }
            }
        }

        ctx.getConsole().printf("Could not find a task with an ID of %d.", id);
        ctx.getConsole().print("");
    }
}
