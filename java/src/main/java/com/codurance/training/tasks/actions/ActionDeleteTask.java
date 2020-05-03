package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;

import java.util.Map;

public class ActionDeleteTask extends Action {
    private final long id;

    public ActionDeleteTask(long taskId) {
        this.id = taskId;
    }

    @Override
    public void execute(CommandContext ctx) {
        Project p = null;

        for (Map.Entry<String, Project> project : ctx.getProjects().entrySet()) {
            for (Task task : project.getValue().getAllTasks()) {
                if (task.getId() == this.id) {
                    p = project.getValue();
                }
            }
        }

        // fix ConcurrentModificationException
        if (p != null) {
            p.deleteTaskById(this.id);
        }
    }
}
