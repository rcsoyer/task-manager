package org.acme.taskmanager.app;

import java.util.Scanner;

import org.acme.taskmanager.domain.ProcessManagerDefault;
import org.acme.taskmanager.domain.ProcessManagerOlderDiesFirst;
import org.acme.taskmanager.domain.ProcessManagerPriorityBased;

public final class TaskManagerControl {

    private final Scanner input = new Scanner(System.in);

    /**
     * Only accepts as input from console int numbers on range 0 to 3.
     *
     * @throws java.util.InputMismatchException if typed input is not an int
     * @throws IllegalArgumentException         if the int value informed is not on range 1 to 3
     */
    public void start() {
        System.out.println("\nSelect the kind of process feature the manager must have \n\tYour Options: " + managerOptions());
        final int userHand = input.nextInt();

    /*    if (userHand == END_GAME) {
            printScore();
        } else {
            matchShowdown(userHand);*/
            start();
      //  }
    }

    private String managerOptions() {
        return "1. " + ProcessManagerDefault.class.getSimpleName() + "\t\t"
                 + "2. " + ProcessManagerOlderDiesFirst.class.getSimpleName() + "\t\t"
                 + "3. " + ProcessManagerPriorityBased.class.getSimpleName() + "\t\t"
                 + "0. End\t\t";
    }
}
