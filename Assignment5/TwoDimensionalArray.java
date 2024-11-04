package Assignment5;

public class TwoDimensionalArray {
    private final int[][] arr;

    // Constructor: Initializes a 2D array with given dimensions and fills it with Integer.MIN_VALUE
    public TwoDimensionalArray(int numberOfRows, int numberOfCols) {
        this.arr = new int[numberOfRows][numberOfCols];
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                arr[row][col] = Integer.MIN_VALUE;
            }
        }
    }

    // Insert a value into the array at a specific row and column
    public void insertValueInTheArray(int row, int col, int value) {
        try {
            if (arr[row][col] == Integer.MIN_VALUE) {
                arr[row][col] = value;
                System.out.println("The value " + value + " was successfully inserted at position (" + row + "," + col + ")");
            } else {
                System.out.println("This cell is already occupied.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index for 2D array.");
        }
    }

    // Traverse the entire array and print each element
    public void traverse() {
        System.out.println("Traversing the 2D array:");
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Search for a specific value in the array and print its location if found
    public void searchingValue(int value) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                if (arr[row][col] == value) {
                    System.out.println("Value " + value + " found at row: " + row + ", col: " + col);
                    return;
                }
            }
        }
        System.out.println("Value " + value + " not found in the array.");
    }

    // Delete a value from a specific location by setting it to Integer.MIN_VALUE
    public void deleteValueFromArray(int row, int col) {
        try {
            System.out.println("Successfully deleted value at position (" + row + "," + col + "): " + arr[row][col]);
            arr[row][col] = Integer.MIN_VALUE;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index for 2D array.");
        }
    }

    // Main method for testing all methods in TwoDimensionalArray class
    public static void main(String[] args) {
        TwoDimensionalArray twoDimensionalArray = new TwoDimensionalArray(5, 5);
        
        // Test insertions
        twoDimensionalArray.insertValueInTheArray(0, 0, 10);
        twoDimensionalArray.insertValueInTheArray(2, 3, 25);
        twoDimensionalArray.insertValueInTheArray(4, 4, 50);
        
        // Test traversal
        twoDimensionalArray.traverse();
        
        // Test searching for an existing and a non-existing value
        twoDimensionalArray.searchingValue(25); // Should find the value
        twoDimensionalArray.searchingValue(100); // Should not find the value
        
        // Test deletion
        twoDimensionalArray.deleteValueFromArray(2, 3); // Should delete the value
        twoDimensionalArray.traverse(); // Check if the value was deleted
        
        // Test invalid deletion
        twoDimensionalArray.deleteValueFromArray(10, 10); // Should handle invalid index
    }
}
