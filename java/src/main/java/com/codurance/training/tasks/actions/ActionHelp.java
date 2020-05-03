package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.Console;

public class ActionHelp extends Action {
    public ActionHelp(Console console) {
        super(console);
    }

    @Override
    public void execute() {
        this.console.print("Commands:");
        this.console.print("  show");
        this.console.print("  add project <project name>");
        this.console.print("  add task <project name> <task description>");
        this.console.print("  check <task ID>");
        this.console.print("  uncheck <task ID>");
        this.console.print("");
    }
}
