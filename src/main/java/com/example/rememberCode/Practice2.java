package com.example.rememberCode;

import java.util.NoSuchElementException;

public class Practice2 {
    public static void main(String[] args) {
        LinkedList theList = new LinkedList();
        LinkedList.ListIterator iter1 = theList.listIterator();
        iter1.insertAfter(21);
        iter1.insertAfter(40);
        iter1.insertAfter(30);
        iter1.insertAfter(7);
        iter1.insertAfter(45);

        iter1.display();
        System.out.println(iter1.find(30));

        iter1.replace(7, 20);
        iter1.display();

    }

}

class LinkedList {
    private Node first;
    private Node last;

    public void insertFirst(int data) {
        final Node newNode = new Node(data);
        if (first == null)
            last = newNode;
        else
            first.prev = newNode;
        newNode.next = first;
        first = newNode;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getFirst() {
        return first;
    }

    public void insertLast(int data) {
        final Node newNode = new Node(data);
        if (first == null)
            first = newNode;
        else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
    }

    public void display() {
        Node temp = first;
        while (temp != null) {
            temp.print();
            temp = temp.next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public ListIterator listIterator() {
        return new ListIterator(this);
    }

    public static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }

        public void print() {
            System.out.println("data: " + data);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public static class ListIterator {
        private Node current;
        private Node prev;
        private LinkedList list;

        public ListIterator(LinkedList list) {
            this.list = list;
            reset();
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list.");
            }
            Node temp = current;
            prev = current;
            current = current.next;
            return temp.data;
        }

        public void reset() {
            current = list.first;
            prev = null;
        }

        public boolean hasNext() {
            return current != null;
        }

        public boolean hasPrev() {
            return prev != null;
        }

        public Node getCurrent() {
            return current;
        }

        public void insertAfter(int dd) {
            Node newLink = new Node(dd);
            if (list.isEmpty()) {
                list.setFirst(newLink);
                current = newLink;
            } else {
                newLink.next = current.next;
                current.next = newLink;
                nextLink();
            }
        }

        public void insertBefore(int dd) {
            Node newLink = new Node(dd);
            if (prev == null) {
                newLink.next = list.first;
                list.setFirst(newLink);
                reset();
            } else {
                newLink.next = prev.next;
                prev.next = newLink;
                current = newLink;
            }
        }

        private void nextLink() {
            prev = current;
            current = current.next;
        }

        public void display() {
            reset();
            Node temp = current;
            while (temp != null) {
                temp.print();
                temp = temp.next;
            }
        }

        public Node find(int data) {
            Node temp = list.first;
            while (temp != null) {
                if (temp.data == data) {
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }

        public void replace(int data, int newData) {
            Node rep = find(data);
            if (rep == null)
                throw new IllegalArgumentException("Element not found");
            rep.data = newData;
        }
    }
}