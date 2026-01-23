package com.example.book;


public class CustomPriorityQueue {
    private int[] queue;
    private int nItems;


    public CustomPriorityQueue(int size) {
        queue = new int[size];
        nItems = 0;
    }

    public void insert(int item) {
        int i;
        if(isFull()){
            System.out.println("Queue is full");
            return;
        }


        if (nItems == 0)
            queue[nItems++] = item;
        else {
            for (i = nItems - 1; i >= 0; i--) {
                if (item > queue[i])
                    queue[i + 1] = queue[i];
                else
                    break;
            }
            queue[i + 1] = item;
            nItems++;
        }
    }

    public int remove() {
        return queue[--nItems];
    }

    public int peek() {
        return queue[nItems - 1];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    private boolean isFull(){
        return nItems == queue.length;
    }
}

class ThisMainPriorityQueue {
    public static void main(String[] args) {
        CustomPriorityQueue queue = new CustomPriorityQueue(5);
        queue.insert(1);
        queue.insert(5);
        queue.insert(2);
        queue.insert(4);
        queue.insert(3);
        queue.insert(6);

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}
