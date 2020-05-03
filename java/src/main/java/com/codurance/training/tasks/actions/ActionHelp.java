package com.codurance.training.tasks.actions;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Console;

public class ActionHelp extends Action {
    @Override
    public void execute(CommandContext ctx) {
        ctx.getConsole().print("Commands:");
        ctx.getConsole().print("  show");
        ctx.getConsole().print("  add project <project name>");
        ctx.getConsole().print("  add task <project name> <task description>");
        ctx.getConsole().print("  check <task ID>");
        ctx.getConsole().print("  uncheck <task ID>");
        ctx.getConsole().print("  deadline <task ID> <date>");
        ctx.getConsole().print("  delete <task ID>");
        ctx.getConsole().print("  viewby date <date>");
        ctx.getConsole().print("  viewby deadline <date>");
        ctx.getConsole().print("  viewby project <project name>");
        ctx.getConsole().print("");
    }
}
