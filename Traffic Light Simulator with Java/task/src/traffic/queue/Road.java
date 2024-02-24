package traffic.queue;

public class Road {
    private String name;
    private int timeUntilSwitch;
    private boolean isOpen;
    public Road(String name, int timeUntilSwitch, boolean isOpen) {
        this.name = name;
        this.timeUntilSwitch = timeUntilSwitch;
        this.isOpen = isOpen;
    }

    public String getName() {
        return name;
    }

    public int getTimeUntilSwitch() {
        return timeUntilSwitch;
    }

    public void setTimeUntilSwitch(int timeUntilSwitch) {
        this.timeUntilSwitch = timeUntilSwitch;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
