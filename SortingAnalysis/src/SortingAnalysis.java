import java.util.Arrays;
import java.util.Random;

public class SortingAnalysis {

    // Main method to execute the sorting analysis
    public static void main(String[] args) {
        int arraySize = 10000; // Adjust size as needed
        int[] array = generateRandomArray(arraySize);

        // Copy of the array for each sorting technique
        int[] arrayForBubbleSort = Arrays.copyOf(array, array.length);
        int[] arrayForSelectionSort = Arrays.copyOf(array, array.length);
        int[] arrayForInsertionSort = Arrays.copyOf(array, array.length);
        int[] arrayForMergeSort = Arrays.copyOf(array, array.length);
        int[] arrayForQuickSort = Arrays.copyOf(array, array.length);
        int[] arrayForHeapSort = Arrays.copyOf(array, array.length);

        // Measure time taken by Bubble Sort
        long startTime = System.nanoTime();
        bubbleSort(arrayForBubbleSort);
        long endTime = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (endTime - startTime) + " ns");

        // Measure time taken by Selection Sort
        startTime = System.nanoTime();
        selectionSort(arrayForSelectionSort);
        endTime = System.nanoTime();
        System.out.println("Selection Sort Time: " + (endTime - startTime) + " ns");

        // Measure time taken by Insertion Sort
        startTime = System.nanoTime();
        insertionSort(arrayForInsertionSort);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort Time: " + (endTime - startTime) + " ns");

        // Measure time taken by Merge Sort
        startTime = System.nanoTime();
        mergeSort(arrayForMergeSort, 0, arrayForMergeSort.length - 1);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Time: " + (endTime - startTime) + " ns");

        // Measure time taken by Quick Sort
        startTime = System.nanoTime();
        quickSort(arrayForQuickSort, 0, arrayForQuickSort.length - 1);
        endTime = System.nanoTime();
        System.out.println("Quick Sort Time: " + (endTime - startTime) + " ns");

        // Measure time taken by Heap Sort
        startTime = System.nanoTime();
        heapSort(arrayForHeapSort);
        endTime = System.nanoTime();
        System.out.println("Heap Sort Time: " + (endTime - startTime) + " ns");
    }

    // Generate a random array of integers
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000); // Random numbers between 0 and 9999
        }
        return array;
    }

    // Bubble Sort Algorithm
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort Algorithm
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    // Insertion Sort Algorithm
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Merge Sort Algorithm
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k++] = L[i++];
            } else {
                array[k++] = R[j++];
            }
        }

        while (i < n1) {
            array[k++] = L[i++];
        }

        while (j < n2) {
            array[k++] = R[j++];
        }
    }

    // Quick Sort Algorithm
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Heap Sort Algorithm
    public static void heapSort(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            heapify(array, n, largest);
        }
    }
}