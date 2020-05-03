package com.codurance.training.tasks;

import com.codurance.training.tasks.actions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    Console console;

    private long lastId = 0;

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

            if (command.equals(QUIT)) {
                break;
            }

            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                new ActionShow(this.tasks, this.console).execute();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                new ActionCheck(this.tasks, this.console, commandRest[1]).execute();
                break;
            case "uncheck":
                new ActionUncheck(this.tasks, this.console, commandRest[1]).execute();
                break;
            case "help":
                new ActionHelp(this.console).execute();
                break;
            default:
                error(command);
                break;
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            new ActionAddProject(this.tasks, this.console, subcommandRest[1]).execute();
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            new ActionAddTask(this.tasks, projectTask[1], projectTask[0], this.nextId(), this.console).execute();
        }
    }

    private void error(String command) {
        this.console.printf("I don't know what the command \"%s\" is.", command);
        this.console.print("");
    }

    private long nextId() {
        return ++lastId;
    }
}
