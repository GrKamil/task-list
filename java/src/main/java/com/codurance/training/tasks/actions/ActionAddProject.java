package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Task;

import java.util.ArrayList;

public class ActionAddProject extends Action {
    private final String name;

    public ActionAddProject(String projectName) {
        this.name = projectName;
    }

    @Override
    public void execute(CommandContext ctx) {
        ctx.getProjects().put(name, new Project(name));
    }
}
