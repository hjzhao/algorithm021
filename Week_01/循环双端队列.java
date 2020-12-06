package Week_01;

class MyCircularDeque {
    private int capacity;
    private int elementCount;
    private int[] elementData;

    private int first;
    private int last;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        if (k > 0) {
            capacity = k;
            elementData = new int[k];
        }
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (capacity == 0 || isFull()) {
            return false;
        }

        first = first == 0 ? capacity - 1 : first - 1;
        elementData[first] = value;
        if (isEmpty()) {
            last = first;
        }
        elementCount += 1;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (capacity == 0 || isFull()) {
            return false;
        }

        last = last == capacity - 1 ? 0 : last + 1;
        elementData[last] = value;
        if (isEmpty()) {
            first = last;
        }
        elementCount += 1;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (capacity == 0 || isEmpty()) {
            return false;
        }

        elementCount -= 1;
        if (!isEmpty()) {
            first = first == capacity - 1 ? 0 : first + 1;
        }
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (capacity == 0 || isEmpty()) {
            return false;
        }

        elementCount -= 1;
        if (!isEmpty()) {
            last = last == 0 ? capacity - 1 : last - 1;
        }
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return capacity == 0 || isEmpty() ? -1 : elementData[first];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return capacity == 0 || isEmpty() ? -1 : elementData[last];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return elementCount == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return elementCount == capacity;
    }
}