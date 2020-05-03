package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Console {
    final String COMMAND_START_SYMBOL = "> ";

    private final BufferedReader in;
    private final PrintWriter out;

    public Console(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public String readCommand() {
        try {
            return this.in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printCommandStart() {
        this.out.print(COMMAND_START_SYMBOL);
        this.out.flush();
    }

    public void print(String text) {
        this.out.println(text);
    }

    public void printf(String text, Object ... args) {
        this.out.printf(text, args);
    }

}
