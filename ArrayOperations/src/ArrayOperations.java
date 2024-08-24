class ArrayOperation {
    private int[] array;
    private int size;

    // Constructor to initialize the array with a given capacity
    public ArrayOperation(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        array = new int[capacity];
        size = 0;
    }

    // Insert an element at the end of the array
    public void insert(int value) {
        if (size >= array.length) {
            throw new IllegalStateException("Array is full. Cannot insert new elements.");
        }
        array[size++] = value;
    }

    // Remove the first occurrence of a value from the array
    public void remove(int value) {
        int index = indexOf(value);
        if (index == -1) {
            throw new IllegalArgumentException("Element not found in the array.");
        }
        removeAt(index);
    }

    // Insert an element at a specific index
    public void insertAt(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if (size >= array.length) {
            throw new IllegalStateException("Array is full. Cannot insert new elements.");
        }
        // Shift elements to the right
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    // Remove an element at a specific index
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        // Shift elements to the left
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    // Print the array elements
    public void print() {
        if (size == 0) {
            System.out.println("Array is empty.");
        } else {
            System.out.print("Array elements: ");
            for (int i = 0; i < size; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }

    // Get the index of the first occurrence of a value
    private int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}

// Main class to test the ArrayOperations class
public class ArrayOperations{
    public static void main(String[] args) {
        try {
            // Create an ArrayOperations object with capacity of 10
            ArrayOperation ao = new ArrayOperation(10);

            // Insert elements
            ao.insert(5);
            ao.insert(10);
            ao.insert(15);
            ao.print(); // Expected: 5 10 15

            // Insert at a specific index
            ao.insertAt(1, 20);
            ao.print(); // Expected: 5 20 10 15

            // Remove an element
            ao.remove(10);
            ao.print(); // Expected: 5 20 15

            // Remove at a specific index
            ao.removeAt(1);
            ao.print(); // Expected: 5 15

            // Test edge cases
            // Remove at invalid index
            try {
                ao.removeAt(5); // Should throw IndexOutOfBoundsException
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Test inserting into a full array
            try {
                for (int i = 0; i < 7; i++) {
                    ao.insert(i * 10);
                }
                ao.print(); // Expected: 5 15 0 10 20 30 40 50 60 70

                // Attempt to insert into a full array
                ao.insert(100); // Should throw IllegalStateException
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Remove a non-existent element
            try {
                ao.remove(999); // Should throw IllegalArgumentException
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // Remove an element from an empty array
            ArrayOperation emptyArray = new ArrayOperation(5);
            try {
                emptyArray.remove(10); // Should throw IllegalArgumentException
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}