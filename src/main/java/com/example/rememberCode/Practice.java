package com.example.rememberCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Practice {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(5);
        priorityQueue.insert(1,5);
        priorityQueue.insert(2,5);
        priorityQueue.insert(3,2);
        priorityQueue.insert(4,1);
        priorityQueue.insert(5,2);
        System.out.println(priorityQueue);

        priorityQueue.remove();
        priorityQueue.remove();
        priorityQueue.remove();
        System.out.println(priorityQueue);
    }
}

class PriorityQueue {
    private HashMap<Integer, LinkedList<Integer>> map;
    private int maxPriority;

    public PriorityQueue(int maxPriority) {
        this.maxPriority = maxPriority;
        map = new HashMap<>();
        for (int i = 0; i <= maxPriority; i++) {
            map.put(i, new LinkedList<>());
        }
    }

    public void insert(int value, int priority) {
        if (priority < 0 || priority > maxPriority) {
            System.out.println("Invalid priority");
            return;
        }
        map.get(priority).add(value); // O(1) вставка
    }

    public Integer remove() {
        for (int i = maxPriority; i >= 0; i--) {
            if (!map.get(i).isEmpty()) {
                return map.get(i).removeFirst(); // Удаление первого элемента
            }
        }
        return null; // Очередь пуста
    }

    public boolean isEmpty() {
        for (int i = 0; i <= maxPriority; i++) {
            if (!map.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "map=" + map +
                ", maxPriority=" + maxPriority +
                '}';
    }
}

class Deque {
    private int[] dequeArray;
    private final int size;
    private int front;
    private int rear;
    private int count;

    public Deque(int size) {
        this.size = size;
        dequeArray = new int[size];
        front = 0; // Points to the front of the deque
        rear = -1; // Points to the last element in the deque
        count = 0; // Number of elements in the deque
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }

    public void insertLeft(int value) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot insert " + value);
            return;
        }

        front = (front - 1 + size) % size;
        System.out.println("front = " + front);
        dequeArray[front] = value;
        System.out.println(Arrays.toString(dequeArray));
        count++;
    }

    public void insertRight(int value) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot insert " + value);
            return;
        }

        rear = (rear + 1) % size;
        System.out.println("rear = " + rear);
        dequeArray[rear] = value;
        System.out.println(Arrays.toString(dequeArray));
        count++;
    }

    public int removeLeft() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot remove from left.");
            return -1;
        }
        int value = dequeArray[front];
        front = (front + 1) % size;
        count--;
        return value;
    }

    public int removeRight() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot remove from right.");
            return -1;
        }
        int value = dequeArray[rear];
        rear = (rear - 1 + size) % size;
        count--;
        return value;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return;
        }
        System.out.print("Deque elements: ");
        for (int i = 0; i < count; i++) {
            System.out.print(dequeArray[(front + i) % size] + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Deque{" +
                "dequeArray=" + Arrays.toString(dequeArray) +
                ", front=" + front +
                ", rear=" + rear +
                ", count=" + count +
                '}';
    }
}


class Queue {
    private final int[] queue;
    private final int size;
    private int front;
    private int rear;


    public Queue(int size) {
        this.size = size;
        queue = new int[size];
        front = 0;
        rear = -1;
    }

    public void insert(int value) {
        if (rear == size - 1)
            rear = -1;
        queue[++rear] = value;
    }

    public int remove() {
        int temp = queue[front++];
        if (front == size)
            front = 0;
        return temp;
    }

    public int peek() {
        return queue[front];
    }

    public boolean isEmpty() {
        return (rear + 1 == front || (front + size - 1 == rear));
    }

    public int size() {
        if (rear >= front)
            return rear - front + 1;
        else
            return (size - front) + (rear + 1);
    }

    public boolean isFull() {
        return (rear + 2 == front || (front + size - 2 == rear));
    }

    public void display() {
        while (front != size) {
            System.out.print(queue[front++] + " ");
        }
    }
}