package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Console;

public abstract class Action {
    abstract public void execute(CommandContext ctx);
}
