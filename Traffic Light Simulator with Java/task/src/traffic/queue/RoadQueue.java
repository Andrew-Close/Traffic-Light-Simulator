package traffic.queue;

import java.util.Arrays;

public class RoadQueue {
    private String[] queue;
    private int front = 0;
    private int rear = 0;

    public RoadQueue(int size) {
        this.queue = new String[size];
    }

    public void enqueue(String road) {
        if (incrementRear()) {
            queue[getPreviousIndex(rear)] = road;
            System.out.println(road + " added!");
        } else {
            System.out.println("Queue is full!");
        }
    }

    public void dequeue() {
        if (incrementFront()) {
            String dequeued = queue[getPreviousIndex(front)];
            queue[getPreviousIndex(front)] = null;
            System.out.println(dequeued + " deleted!");
        } else {
            System.out.println("Queue is empty!");
        }
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

    private int getPreviousIndex(int i) {
        --i;
        if (i < 0) {
            i = queue.length - 1;
        }
        return i;
    }
}
