package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Task;

import java.util.List;
import java.util.Map;

public class ActionCheck extends ActionCheckToggle {
    public ActionCheck(long id) {
        super(id);
    }

    @Override
    public void execute(CommandContext ctx) {
        this.setDone(ctx, true);
    }
}
