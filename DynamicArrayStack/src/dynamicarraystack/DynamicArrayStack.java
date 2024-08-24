package dynamicarraystack;

import java.util.Arrays;

public class DynamicArrayStack {
    private int[] array;
    private int top;

    public DynamicArrayStack() {
        array = new int[5]; // Initial capacity
        top = -1;
    }

    public void push(int element) {
        if (top == array.length - 1) {
            // Array is full, resize
            int[] newArray = new int[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[++top] = element;
    }

    public int pop() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty.");
        }
        return array[top--];
    }

    public int peek() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty.");
        }
        return array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        DynamicArrayStack stack = new DynamicArrayStack();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Peek: " + stack.peek());

        stack.pop();
        stack.pop();

        System.out.println("Peek: " + stack.peek());

        stack.push(40);
        stack.push(50);

        System.out.println("Is empty: " + stack.isEmpty());
    }
}