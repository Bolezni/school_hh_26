package com.example.rememberCode;

public class Practice3 {
    public static void main(String[] args) {
        SortedLinkedList sortedList = new SortedLinkedList();
        sortedList.insert(30);
        sortedList.insert(10);
        sortedList.insert(20);
        sortedList.insert(5);
        sortedList.insert(25);

        System.out.println("Содержимое отсортированного списка:");
        sortedList.display();
    }
}

class SortedLinkedList {
    private LinkedList list;

    public SortedLinkedList() {
        list = new LinkedList();
    }

    public void insert(int value) {
        if (list.getFirst() == null || list.getFirst().data > value) {
            list.insertFirst(value);
            return;
        }

        LinkedList.Node current = list.getFirst();
        while (current != null) {
            if (current.data == value) {
                return;
            }
            if (current.next == null || current.next.data > value) {
                LinkedList.Node newNode = new LinkedList.Node(value);
                newNode.next = current.next;
                newNode.prev = current;

                if (current.next != null) {
                    current.next.prev = newNode;
                } else {
                    list.setLast(newNode);
                }

                current.next = newNode;
                return;
            }
            current = current.next;
        }
    }
    public void display() {
        LinkedList.Node current = list.getFirst();
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

}
