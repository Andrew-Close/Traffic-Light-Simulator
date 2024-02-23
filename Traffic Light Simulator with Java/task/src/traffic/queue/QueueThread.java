package traffic.queue;

import traffic.main.Main;

import java.io.IOException;

public class QueueThread extends Thread {
    private int timer = 0;
    private RoadQueue queue;
    private boolean shouldContinue = true;
    public QueueThread() {
        super();
        this.setName("QueueThread");
    }

    public void initQueue() {
        queue = new RoadQueue(Main.getNumOfRoads());
    }

    @Override
    public void run() {
        while (shouldContinue) {
            try {
                waitOneSecond();
            } catch (InterruptedException ignored) {}
            ++timer;
            if (Main.getState() == 3) {
                updateAndPrintSystemInformation();
            }
        }
    }

    private void waitOneSecond() throws InterruptedException {
        Thread.sleep(1000L);
    }

    private void waitOneSecondAndUpdateRoadTimers() throws InterruptedException {
        waitOneSecond();
        for (Road road : queue.getQueue()) {
            if (road != null) {
                road.setTimeUntilSwitch(road.getTimeUntilSwitch() - 1);
            }
        }
    }

    private void updateAndPrintSystemInformation() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException ignored) {}
        printSystemInformation();
    }

    public void printSystemInformation() {
        System.out.println("! " + timer + "s. have passed since system startup !");
        System.out.println("Number of roads: " + Main.getNumOfRoads());
        System.out.println("! Interval: " + Main.getInterval() + " !");
        printQueue();
        System.out.println("! Press \"Enter\" to open menu !");
    }

    private void printQueue() {
        int index = this.queue.getFront();
        int rear = this.queue.getRear();
        Road[] queue = this.queue.getQueue();
        if (!(index == rear)) {
            boolean shouldPrintPadding = false;
            while (true) {
                if ((queue[index] == null || index == rear)) {
                    if (shouldPrintPadding) {
                        System.out.println();
                    }
                    break;
                } else {
                    if (!shouldPrintPadding) {
                        shouldPrintPadding = true;
                        System.out.println();
                    }
                    System.out.println(queue[index]);
                    ++index;
                    if (index > queue.length - 1) {
                        index = 0;
                    }
                }
            }
        } else if (!(queue[index] == null)) {
            System.out.println();
            for (int i = 0; i < queue.length; i++) {
                System.out.println(queue[index]);
                ++index;
                if (index > queue.length - 1) {
                    index = 0;
                }
            }
            System.out.println();
        }
    }

    public void terminate() { shouldContinue = false; }

    public RoadQueue getQueue() {
        return queue;
    }
}
