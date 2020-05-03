package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;

public abstract class Action {
    protected final Console console;

    public Action(Console console) {
        this.console = console;
    }

    abstract public void execute();
}
