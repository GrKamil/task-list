package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;

import java.util.Map;
import static java.time.Clock.systemDefaultZone;
import static java.time.LocalDate.now;

public class ActionToday extends Action {

    @Override
    public void execute(CommandContext ctx) {
        boolean tasksExist = false;

        for (Map.Entry<String, Project> project : ctx.getProjects().entrySet()) {
            for (Task task : project.getValue().getAllTasks()) {
                if (now(systemDefaultZone()).equals(task.getDeadline())) {
                    tasksExist = true;
                    ctx.getConsole().printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                }
            }
        }

        if (!tasksExist) {
            ctx.getConsole().print("No tasks for today");
        }
    }
}
