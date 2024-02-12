package traffic.queue;

public class RoadQueue {
    private String[] queue;
    private int front = 0;
    private int rear = 0;

    public RoadQueue(int size) {
        this.queue = new String[size];
    }

    public void enqueue(String road) {
        if (incrementRear()) {
            queue[rear] = road;
            System.out.println(road + " added!");
        } else {
            System.out.println("Queue is full!");
        }
    }

    public void dequeue() {
        if (incrementFront()) {
            String dequeued = queue[front];
            queue[front] = null;
            System.out.println(dequeued + " deleted!");
        } else {
            System.out.println("Queue is empty!");
        }
    }

    private boolean incrementFront() {
        if (front == rear) {
            return false;
        }
        ++front;
        if (front > queue.length - 1) {
            front = 0;
        }
        return true;
    }

    private boolean incrementRear() {
        ++rear;
        if (rear > queue.length - 1) {
            rear = 0;
        }
        if (rear == front) {
            --rear;
            return false;
        }
        return true;
    }
}
