package com.codurance.training.tasks;

import com.codurance.training.tasks.CommandContext;
import com.codurance.training.tasks.Console;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.actions.*;
import com.codurance.training.tasks.exceptions.QuitException;

import java.time.LocalDate;
import java.util.Map;

public class ActionManager {
    private final Console console;

    public ActionManager(Console console) {
        this.console = console;
    }

    public void runCommands(String commandline, Map<String, Project> tasks) throws QuitException {
        CommandContext ctx = new CommandContext(this.console, tasks, commandline);

        String[] commandRest = commandline.split(" ", 2);
        String commandName = commandRest[0];

        try {
            switch (commandName) {
                case "show":
                    new ActionShow().execute(ctx);
                    break;
                case "add":
                    String[] subcommandRest = commandRest[1].split(" ", 2);
                    String subcommand = subcommandRest[0];

                    if (subcommand.equals("project")) {
                        new ActionAddProject(subcommandRest[1]).execute(ctx);
                    } else if (subcommand.equals("task")) {
                        String[] projectTask = subcommandRest[1].split(" ", 2);
                        new ActionAddTask(projectTask[0], projectTask[1]).execute(ctx);
                    }
                    break;
                case "check":
                    new ActionCheck(Long.parseLong(commandRest[1])).execute(ctx);
                    break;
                case "uncheck":
                    new ActionUncheck(Long.parseLong(commandRest[1])).execute(ctx);
                    break;
                case "help":
                    new ActionHelp().execute(ctx);
                    break;
                case "delete":
                    String taskId = commandRest[1].split(" ", 2)[0];
                    new ActionDeleteTask(Long.parseLong(taskId)).execute(ctx);
                    break;
                case "today":
                    new ActionToday().execute(ctx);
                    break;
                case "deadline":
                    String[] subcommands = commandRest[1].split(" ", 2);
                    long deadlineTaskId = Long.parseLong(subcommands[0]);
                    LocalDate date = LocalDate.parse(subcommands[1]);
                    new ActionSetDeadline(deadlineTaskId, date).execute(ctx);
                    break;
                case "viewby":
                    new ActionView().execute(ctx);
                    break;
                case "quit":
                    throw new QuitException();
                default:
                    error(commandline);
                    break;
            }
        }
        catch (QuitException e) {
            throw e;
        }
        catch (Exception e) {
            ctx.getConsole().print("Unknown command");
        }
    }

    private void error(String command) {
        this.console.printf("I don't know what the command \"%s\" is.", command);
        this.console.print("");
    }
}
