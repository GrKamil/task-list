package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

public class CommandContext {
    final Console console;
    final String command;
    final Map<String, Project> tasks;

    public CommandContext(Console console, Map<String, Project> tasks, String command) {
        this.console = console;
        this.command = command;
        this.tasks   = tasks;
    }

    public Console getConsole() {
        return this.console;
    }

    public Map<String, Project> getProjects() {
        return this.tasks;
    }

    public String getCommand() {
        return this.command;
    }
}
