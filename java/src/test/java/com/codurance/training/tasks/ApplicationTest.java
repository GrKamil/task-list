package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ApplicationTest {
    public static final String PROMPT = "> ";
    private final PipedOutputStream inStream = new PipedOutputStream();
    private final PrintWriter inWriter = new PrintWriter(inStream, true);

    private final PipedInputStream outStream = new PipedInputStream();
    private final BufferedReader outReader = new BufferedReader(new InputStreamReader(outStream));

    private Thread applicationThread;

    public ApplicationTest() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);
        TaskList taskList = new TaskList(in, out);
        applicationThread = new Thread(taskList);
    }

    @Before
    public void
    start_the_application() {
        applicationThread.start();
    }

    @After
    public void
    kill_the_application() throws IOException, InterruptedException {
        if (!stillRunning()) {
            return;
        }

        Thread.sleep(1000);
        if (!stillRunning()) {
            return;
        }

        applicationThread.interrupt();
        throw new IllegalStateException("The application is still running.");
    }

    @Test(timeout = 1000)
    public void
    add_project() throws IOException {
        execute("show");
        execute("add project secrets");
        execute("show");
        readLines("secrets", "");
        execute("quit");
    }

    @Test(timeout = 1000)
    public void
    add_task() throws IOException {
        execute("show");
        execute("add project secrets");
        execute("add task secrets Eat more donuts.");
        execute("add task secrets Destroy all humans.");
        execute("show");
        readLines(
                "secrets",
                "    [ ] 2: Eat more donuts.",
                "    [ ] 3: Destroy all humans.",
                ""
        );
        execute("quit");
    }

    @Test(timeout = 1000) public void
    remove_task() throws IOException {
        execute("show");
        execute("add project secrets");
        execute("add task secrets Eat more donuts.");
        execute("add task secrets Destroy all humans.");
        execute("show");
        readLines(
                "secrets",
                "    [ ] 6: Eat more donuts.",
                "    [ ] 7: Destroy all humans.",
                ""
        );
        execute("delete 7");
        execute("show");
        readLines(
                "secrets",
                "    [ ] 6: Eat more donuts.",
                ""
        );
        execute("quit");
    }

    @Test(timeout = 1000)
    public void
    check_task() throws IOException {
        execute("show");

        execute("add project secrets");
        execute("add task secrets Eat more donuts.");
        execute("add task secrets Destroy all humans.");

        execute("show");
        readLines(
                "secrets",
                "    [ ] 4: Eat more donuts.",
                "    [ ] 5: Destroy all humans.",
                ""
        );

        execute("check 5");
        execute("show");
        readLines(
                "secrets",
                "    [ ] 4: Eat more donuts.",
                "    [x] 5: Destroy all humans.",
                ""
        );

        execute("uncheck 5");
        execute("show");
        readLines(
                "secrets",
                "    [ ] 4: Eat more donuts.",
                "    [ ] 5: Destroy all humans.",
                ""
        );

        execute("quit");
    }

    @Test(timeout = 1000)
    public void
    uncheck_task() throws IOException {
        execute("show");

        execute("add project secrets");
        execute("add task secrets Eat more donuts.");
        execute("add task secrets Destroy all humans.");

        execute("show");
        readLines(
                "secrets",
                "    [ ] 10: Eat more donuts.",
                "    [ ] 11: Destroy all humans.",
                ""
        );

        execute("check 11");
        execute("show");
        readLines(
                "secrets",
                "    [ ] 10: Eat more donuts.",
                "    [x] 11: Destroy all humans.",
                ""
        );

        execute("uncheck 11");
        execute("show");
        readLines(
                "secrets",
                "    [ ] 10: Eat more donuts.",
                "    [ ] 11: Destroy all humans.",
                ""
        );

        execute("quit");
    }

    @Test(timeout = 1000)
    public void
    deadline_and_viewby_deadline() throws IOException {
        execute("show");
        execute("add project secrets");
        execute("add task secrets Eat more donuts.");

        execute("show");
        readLines(
                "secrets",
                "    [ ] 12: Eat more donuts.",
                ""
        );

        execute("deadline 12 2020-05-10");
        execute("viewby deadline 2020-05-10");
        readLines("    [ ] 12: Eat more donuts.");

        execute("viewby deadline 2020-05-11");
        readLines("    [ ] 12: Eat more donuts.");

        execute("quit");
    }

    @Test(timeout = 1000)
    public void
    viewby_date() throws IOException {
        execute("show");

        execute("add project secrets");
        execute("add task secrets Eat more donuts.");

        execute("show");
        readLines(
                "secrets",
                "    [ ] 1: Eat more donuts.",
                ""
        );

        execute("deadline 1 2020-05-10");
        execute("viewby deadline 2020-05-10");

        readLines("    [ ] 1: Eat more donuts.");

        execute("viewby deadline 2020-05-11");
        readLines("    [ ] 1: Eat more donuts.");

        execute("quit");
    }

    @Test(timeout = 1000)
    public void
    viewby_project() throws IOException {
        execute("show");

        execute("add project secrets");
        execute("add task secrets Eat more donuts.");
        execute("add project please");
        execute("add task please Do not make pain with tests.");

        execute("show");
        readLines(
                "secrets",
                "    [ ] 8: Eat more donuts.",
                "",
                "please",
                "    [ ] 9: Do not make pain with tests.",
                ""
        );

        execute("quit");
    }

    private void execute(String command) throws IOException {
        read(PROMPT);
        write(command);
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), is(expectedOutput));
    }

    private void readLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void write(String input) {
        inWriter.println(input);
    }

    private boolean stillRunning() {
        return applicationThread != null && applicationThread.isAlive();
    }
}
