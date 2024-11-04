package Assignment2;

public class SingleLinkedList {
    private Node head; // Head of the list

    // Node inner class
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Method to delete a node with a specific value
    public void delete(int value) {
        // 0. Check if the list is empty
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        // 1. Deleting at the beginning (if head holds the value)
        if (head.data == value) {
            head = head.next; // Move head to the next node
            System.out.println("Node with value " + value + " deleted from the beginning.");
            return;
        }

        // 2. Deleting at the end or 3. Anywhere in the list
        Node current = head;
        Node previous = null;

        // Traverse the list to find the node with the given value
        while (current != null && current.data != value) {
            previous = current;
            current = current.next;
        }

        // Check if node with the given value was found
        if (current == null) {
            System.out.println("Node with value " + value + " not found in the list.");
            return;
        }

        // If the node to delete is the last node
        if (current.next == null) {
            previous.next = null;
            System.out.println("Node with value " + value + " deleted from the end.");
        } else {
            // Deleting anywhere else in the list
            previous.next = current.next;
            System.out.println("Node with value " + value + " deleted from the list.");
        }
    }

    // Helper method to add nodes to the list for testing purposes
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Helper method to display the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Main method for testing
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.display();

        list.delete(10); // Deleting at the beginning
        list.display();

        list.delete(40); // Deleting at the end
        list.display();

        list.delete(20); // Deleting in the middle
        list.display();

        list.delete(50); // Node not found
        list.display();
    }
}
