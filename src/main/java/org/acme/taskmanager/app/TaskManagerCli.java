package org.acme.taskmanager.app;

public class TaskManagerCli {

    public static void main(final String[] args) {
        System.out.println("\nTask Manager app");
        new TaskManagerControl().start();
    }
}
