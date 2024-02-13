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
        queue = new RoadQueue(Main.numOfRoads);
    }

    @Override
    public void run() {
        while (shouldContinue) {
            try {
                waitOneSecond();
            } catch (InterruptedException ignored) {}
            ++timer;
            if (Main.state == 3) {
                updateAndPrintSystemInformation();
            }
        }
    }

    private void waitOneSecond() throws InterruptedException {
        Thread.sleep(1000L);
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
        System.out.println("Number of roads: " + Main.numOfRoads);
        System.out.println("! Interval: " + Main.interval + " !");
        System.out.println("! Press \"Enter\" to open menu !");
        printQueue();
    }

    private void printQueue() {
        int index = this.queue.front;
        int rear = this.queue.rear;
        String[] queue = this.queue.queue;
        boolean paddingLock = true;
        do {
            if (queue[index] == null) {
                break;
            } else {
                if (paddingLock) {
                    System.out.println();
                    paddingLock = false;
                }
                System.out.println(queue[index]);
                ++index;
                if (index > queue.length - 1) {
                    index = 0;
                }
            }
        } while (!(index == rear || queue[index] == null));
    }

    public void terminate() { shouldContinue = false; }

    public RoadQueue getQueue() {
        return queue;
    }
}
