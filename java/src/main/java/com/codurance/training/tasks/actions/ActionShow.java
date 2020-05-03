package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;
import java.util.Map;

public class ActionShow extends Action {
    @Override
    public void execute(CommandContext ctx) {
        for (Map.Entry<String, Project> project : ctx.getProjects().entrySet()) {
            ctx.getConsole().print(project.getValue().getName());

            for (Task task : project.getValue().getAllTasks()) {
                ctx.getConsole().printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }

            ctx.getConsole().print("");
        }
    }
}
