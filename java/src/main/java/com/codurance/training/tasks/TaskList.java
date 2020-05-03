package com.codurance.training.tasks;

import com.codurance.training.tasks.exceptions.QuitException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
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
        ActionManager actionManager = new ActionManager(this.console);

        while (true) {
            this.console.printCommandStart();
            String command = this.console.readCommand();

            try {
                actionManager.runCommands(command, this.tasks);
            } catch (QuitException e) {
                return;
            }
        }
    }
}
