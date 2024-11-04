package Assignment3;

public class UndoRedoManager<T> {
    private class Node {
        private T state;
        private Node prev;
        private Node next;

        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState;

    // Undo operation
    public T undo() {
        if (currentState == null) {
            System.out.println("No state to undo");
            return null;
        }

        Node previousState = currentState.prev;
        if (previousState == null) {
            System.out.println("Reached the initial state");
            return null;
        } else {
            // Update current state to the previous state
            currentState = previousState;
        }
        return currentState.state;
    }

    // Perform an operation to add a new state
    public void addState(T newState) {
        Node newNode = new Node(newState);

        // Set the links for the new node
        newNode.prev = currentState;
        newNode.next = null;

        // Update the next link for the current state
        if (currentState != null) {
            currentState.next = newNode;
        }

        // Update currentState to the new state
        currentState = newNode;
    }

    // Redo operation
    public T redo() {
        if (currentState == null || currentState.next == null) {
            System.out.println("No state to redo");
            return null;
        } else {
            // Move to the next state
            currentState = currentState.next;
        }
        return currentState.state;
    }

    public static void main(String[] args) {
        UndoRedoManager<String> undoRedoManager = new UndoRedoManager<>();
        undoRedoManager.addState("State 1");
        undoRedoManager.addState("State 2");
        undoRedoManager.addState("State 3");
        undoRedoManager.addState("State 4");
        undoRedoManager.addState("State 5");

        System.out.println("Current State: " + undoRedoManager.currentState.state); // State 5
        undoRedoManager.undo();
        System.out.println("Current State after undo: " + undoRedoManager.currentState.state); // State 4
        undoRedoManager.undo();
        System.out.println("Current State after undo: " + undoRedoManager.currentState.state); // State 3
        undoRedoManager.redo();
        System.out.println("Current State after redo: " + undoRedoManager.currentState.state); // State 4
        undoRedoManager.redo();
        System.out.println("Current State after redo: " + undoRedoManager.currentState.state); // State 5
        undoRedoManager.redo(); // No state to redo
    }
}
