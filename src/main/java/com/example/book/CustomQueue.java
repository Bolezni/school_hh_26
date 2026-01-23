package com.example.book;

public class CustomQueue {
    private final int queueSize;
    private final int[] queue;
    private int front;
    private int rear;


    public CustomQueue(int size) {
        queueSize = size + 1;
        queue = new int[queueSize];
        front = 0;
        rear = -1;
    }

    public void insert(int data) {
        if (rear == queueSize - 1)
            rear = -1;
        queue[++rear] = data;
    }

    public int remove() {
        int item = queue[front++];
        if(front == queueSize)
            front = 0;
        return item;
    }
    public int peek() {
        return queue[front];
    }

    public boolean isEmpty() {
        return (rear+1 == front || (front + queueSize - 1 == rear));
    }
    public int size() {
        if(rear >= front)
            return rear - front + 1;
        else
            return (queueSize - front) + (rear + 1);
    }
    public boolean isFull(){
        return (rear+2 == front || (front + queueSize - 2 == rear));
    }
}
class ThisMainQueue{
    public static void main(String[] args) {
        CustomQueue queue = new CustomQueue(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);

        queue.remove();
        queue.remove();
        queue.remove();

        queue.insert(6);
        queue.insert(7);
        queue.insert(8);

        System.out.println();
        while(!queue.isEmpty()){;
            System.out.println(queue.remove());
        }

    }
}
