package com.codurance.training.tasks;

import com.codurance.training.tasks.actions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, Project> tasks = new LinkedHashMap<>();
    private final Console console;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.console = new Console(reader, writer);
    }

    public void run() {
        while (true) {
            this.console.printCommandStart();
            String command = this.console.readCommand();
            CommandContext ctx = new CommandContext(this.console, this.tasks, command);

            String[] commandRest = command.split(" ", 2);
            String commandName = commandRest[0];

            if (commandName.equals(QUIT)) {
                break;
            }

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
                    default:
                        error(command);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
                ctx.getConsole().print("Unknown command");
            }
        }
    }

    private void error(String command) {
        this.console.printf("I don't know what the command \"%s\" is.", command);
        this.console.print("");
    }
}
