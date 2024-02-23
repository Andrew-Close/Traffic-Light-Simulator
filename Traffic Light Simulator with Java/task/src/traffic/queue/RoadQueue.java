package traffic.queue;

import traffic.main.Main;

public class RoadQueue {
    private Road[] queue;
    private int front = 0;
    private int rear = 0;
    private int openRoadIndex = -1;

    public RoadQueue(int size) {
        this.queue = new Road[size];
    }

    public void enqueue(String name) {
        if (incrementRear()) {
            if (isEmpty()) {
                openRoadIndex = getPreviousIndex(rear);
                queue[getPreviousIndex(rear)] = new Road(name, Main.getInterval(), true);
            } else {
                queue[getPreviousIndex(rear)] = new Road(name, Main.getInterval() * getDistanceFromOpenedIndex(getPreviousIndex(rear)), false);
            }
            System.out.println(name + " added!");
        } else {
            System.out.println("Queue is full!");
        }
    }

    public void dequeue() {
        if (incrementFront()) {
            Road dequeued = queue[getPreviousIndex(front)];
            queue[getPreviousIndex(front)] = null;
            System.out.println(dequeued + " deleted!");
        } else {
            System.out.println("Queue is empty!");
        }
    }

    private int getPreviousIndex(int i) {
        --i;
        if (i < 0) {
            i = queue.length - 1;
        }
        return i;
    }

    private int getNextIndex(int i) {
        ++i;
        if (i > 0) {
            i = 0;
        }
        return i;
    }

    private int getDistanceFromOpenedIndex(int i) {
        int length = getLargestOccupiedIndex() - getSmallestOccupiedIndex();
        if (i < openRoadIndex) {
            i += length;
        }
        return i - openRoadIndex;
    }

    private boolean incrementFront() {
        if (queue[front] == null) {
            return false;
        }
        ++front;
        if (front > queue.length - 1) {
            front = 0;
        }
        return true;
    }

    private boolean incrementRear() {
        if (!(queue[rear] == null)) {
            return false;
        }
        ++rear;
        if (rear > queue.length - 1) {
            rear = 0;
        }
        return true;
    }

    private int getSmallestOccupiedIndex() {
        int index = -1;
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] != null) {
                index = i;
                break;
            }
        }
        return index;
    }

    private int getLargestOccupiedIndex() {
        int index = -1;
        for (int i = queue.length - 1; i >= 0; i--) {
            if (queue[i] != null) {
                index = i;
                break;
            }
        }
        return index;
    }

    void decrementTimers() {
        for (Road road : queue) {
            if (road != null) {
                road.setTimeUntilSwitch(road.getTimeUntilSwitch() - 1);
                if (road.getTimeUntilSwitch() == 0) {
                    openRoadIndex = getNextIndex(openRoadIndex);

                    break;
                }
            }
        }
    }

    private void refreshTimers() {
        for (int i = 0; i < queue.length; i++) {
            Road road = queue[i];
            if (road != null) {
                int distance = getDistanceFromOpenedIndex(i);
                if (distance == 0) {
                    road.setTimeUntilSwitch(Main.getInterval());
                } else {
                    road.setTimeUntilSwitch(distance * Main.getInterval());
                }
            }
        }
    }

    public boolean isEmpty() {
        for (Road road : queue) {
            if (road != null) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public int getNumOfElements() {
        int counter  = 0;
        for (Road road : queue) {
            if (road != null) {
                ++counter;
            }
        }
        return counter;
    }

    public Road[] getQueue() {
        return queue;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }
}
