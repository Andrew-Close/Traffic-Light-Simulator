package traffic;

import java.io.IOException;

public class QueueThread extends Thread {
    private int timer = 0;
    private boolean shouldContinue = true;
    public QueueThread() {
        super();
        this.setName("QueueThread");
    }

    @Override
    public void run() {
        while (shouldContinue) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ignored) {}
            ++timer;
            if (Main.state == 3) {
                updateAndPrintSystemInformation();
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
        System.out.println("Number of roads: " + Main.numOfRoads);
        System.out.println("! Interval: " + Main.interval + " !");
        System.out.println("! Press \"Enter\" to open menu !");
    }

    public void terminate() { shouldContinue = false; }

    public int getTimer() {
        return timer;
    }
}
