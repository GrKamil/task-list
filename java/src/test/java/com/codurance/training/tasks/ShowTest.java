//package com.codurance.training.tasks;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PipedInputStream;
//import java.io.PipedOutputStream;
//import java.io.PrintWriter;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static java.lang.System.lineSeparator;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//public final class ShowTest {
//    public static final String PROMPT = "> ";
//    private final PipedOutputStream inStream = new PipedOutputStream();
//    private final PrintWriter inWriter = new PrintWriter(inStream, true);
//
//    private final PipedInputStream outStream = new PipedInputStream();
//    private final BufferedReader outReader = new BufferedReader(new InputStreamReader(outStream));
//
//    private Thread applicationThread;
//
//    public ShowTest() throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
//        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);
//        TaskList taskList = new TaskList(in, out);
//        applicationThread = new Thread(taskList);
//    }
//
//    @Before public void
//    start_the_application() {
//        applicationThread.start();
//    }
//
//    @After public void
//    kill_the_application() throws IOException, InterruptedException {
//        if (!stillRunning()) {
//            return;
//        }
//
//        Thread.sleep(1000);
//        if (!stillRunning()) {
//            return;
//        }
//
//        applicationThread.interrupt();
//        throw new IllegalStateException("The application is still running.");
//    }
//
//    @Test(timeout = 1000) public void
//    show_test() throws IOException {
//        execute("show");
//
//        execute("add project secrets");
//        execute("add task secrets Eat more donuts.");
//        execute("add task secrets Destroy all humans.");
//
//        execute("show");
//        readLines(
//                "secrets",
//                "    [ ] 9: Eat more donuts.",
//                "    [ ] 10: Destroy all humans.",
//                ""
//        );
//
//        execute("quit");
//    }
//
//    private void execute(String command) throws IOException {
//        read(PROMPT);
//        write(command);
//    }
//
//    private void read(String expectedOutput) throws IOException {
//        int length = expectedOutput.length();
//        char[] buffer = new char[length];
//        outReader.read(buffer, 0, length);
//        assertThat(String.valueOf(buffer), is(expectedOutput));
//    }
//
//    private void readLines(String... expectedOutput) throws IOException {
//        for (String line : expectedOutput) {
//            read(line + lineSeparator());
//        }
//    }
//
//    private void write(String input) {
//        inWriter.println(input);
//    }
//
//    private boolean stillRunning() {
//        return applicationThread != null && applicationThread.isAlive();
//    }
//}
