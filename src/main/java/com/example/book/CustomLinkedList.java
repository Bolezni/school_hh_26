package com.example.book;

public class CustomLinkedList {
    private Link first;

    private Link last;

    public void insertFirst(int value) {
        final Link newLink = new Link(value);
        if (first == null)
            last = newLink;
        else
            first.prev = newLink;
        newLink.next = first;
        first = newLink;
    }

    public Link find(int value) {
        Link current = first;
        while (current != null) {
            if (current.data == value)
                return current;
            else
                current = current.next;
        }
        return null;
    }

    public void insertLast(int value) {
        final Link l = new Link(value);
        if (last == null)
            first = l;
        else {
            last.next = l;
            l.prev = last;
        }
        last = l;

    }

    public Link deleteFirst() {
        if (first == null)
            return null;
        Link temp = first;
        first = first.next;
        if (first != null)
            first.prev = null;
        else
            last = null;
        return temp;
    }

    public Link deleteLast() {
        if (last == null)
            return null;
        Link temp = last;
        last = last.prev;
        if (last != null)
            last.next = null;
        else
            first = null;
        return temp;
    }

    public void display() {
        System.out.println("List (first --> last): ");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    public Link deleteDoubleList(int key) {
        Link current = first;
        Link prev = null;

        while (current != null) {
            if (current.data == key) {
                if (current == first) {
                    first = current.next;
                    if (first != null) {
                        first.prev = null;
                    }
                } else
                    prev.next = current.next;

                if (current == last) {
                    last = prev;
                } else if (current.next != null) {
                    current.next.prev = prev;
                }
                return current;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }


    public Link delete(int key) {
        Link current = first;
        Link prev = null;
        while (current != null) {
            if (current.data == key) {
                if (current == first)
                    first = first.next;
                else
                    prev.next = current.next;
                return current;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public void insertAfter(int key, int value) {
        final Link newLink = new Link(value);
        Link current = first;
        while (current != null) {
            if (current.data == key) {
                newLink.next = current.next;
                newLink.prev = current;
                if (current.next != null)
                    current.next.prev = newLink;
                else
                    last = newLink;
                current.next = newLink;
                return;
            }
            current = current.next;
        }
    }


    public void reverse() {
        Link current = first;
        Link temp = null;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;

            current = current.prev;
        }
        temp = first;
        first = last;
        last = temp;
    }

    public void reverseDisplay() {
        Link current = last;
        while (current != null) {
            current.displayLink();
            current = current.prev;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Link getFirst() {
        return first;
    }

    private static class ListIterator {
        private Link current;
        private Link prev;
        private CustomLinkedList ourList;

        public void reset() {
            current = ourList.first;
            prev = null;
        }

        public void nextLink() {
            prev = current;
            current = current.next;
        }

    }

    private static class Link {
        private final int data;
        private Link next;
        private Link prev;

        public Link(int data) {
            this.data = data;
        }

        public void displayLink() {
            System.out.println("Data: " + data);
        }
    }
}

class LinkedStack {
    private CustomLinkedList stack;

    public LinkedStack() {
        stack = new CustomLinkedList();
    }

    public void push(int value) {
        stack.insertFirst(value);
    }

    public void pop() {
        stack.deleteFirst();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void display() {
        System.out.print("Stack (top-->bottom): ");
        stack.display();
    }

}

class LinkedQueue {
    private CustomLinkedList queue;

    public LinkedQueue() {
        queue = new CustomLinkedList();
    }

    public void push(int value) {
        queue.insertLast(value);
    }

    public void pop() {
        queue.deleteFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void displayQueue() {
        System.out.print("Queue (front-->rear): ");
        queue.display();
    }
}

class SortedLinkedList {
    private Link first;


    public SortedLinkedList(Link[] linkArr) {
        for (int i = 0; i < linkArr.length; i++) {
            insert(linkArr[i]);
        }
    }

    public void insert(Link link) {
        Link current = first;
        Link prev = null;

        while (current != null && link.data > current.data) {
            prev = current;
            current = current.next;
        }
        if (prev == null)
            first = link;
        else
            prev.next = link;
        link.next = current;
    }

    public void insert(int value) {
        final Link newLink = new Link(value);
        Link current = first;
        Link prev = null;

        while (current != null && value > current.data) {
            prev = current;
            current = current.next;
        }
        if (prev == null)
            first = newLink;
        else
            prev.next = newLink;
        newLink.next = current;
    }

    public void display() {
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    public static class Link {
        private final int data;
        private Link next;

        public Link(int data) {
            this.data = data;
        }

        public void displayLink() {
            System.out.println("Data: " + data);
        }
    }
}

class Test {
    public static void main(String[] args) {

    }
}
