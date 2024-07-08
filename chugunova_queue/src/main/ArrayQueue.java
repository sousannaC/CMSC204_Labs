package main;

import java.util.NoSuchElementException;

/**
   An array-based implementation of the QueueInterface.
   This class provides a queue implementation using a circular array.
   @param <T> The type of elements held in this collection
*/
public class ArrayQueue<T> implements QueueInterface<T> {
    private T[] queue;   // Array of queue entries
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    /** Default constructor that initializes the queue with the default capacity. */
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /** Constructor that initializes the queue with a specified capacity.
        @param initialCapacity The initial capacity of the queue */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        if (initialCapacity <= MAX_CAPACITY) {
            T[] tempQueue = (T[]) new Object[initialCapacity + 1]; // The cast is safe because the new array contains null entries
            queue = tempQueue;
            frontIndex = 0;
            backIndex = initialCapacity;
            initialized = true;
        } else {
            throw new IllegalStateException("Attempt to create a queue whose capacity exceeds allowed maximum.");
        }
    }

    /** Adds a new entry to the back of this queue.
        @param newEntry  An object to be added. */
    @Override
    public void enqueue(T newEntry) {
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }

    /** Removes and returns the entry at the front of this queue.
        @return  The object at the front of the queue. 
        @throws  NoSuchElementException if the queue is empty before the operation. */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty, cannot dequeue.");
        } else {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        }
    }

    /** Retrieves the entry at the front of this queue.
        @return  The object at the front of the queue.
        @throws  NoSuchElementException if the queue is empty. */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty.");
        } else {
            return queue[frontIndex];
        }
    }

    /** Detects whether this queue is empty.
        @return  True if the queue is empty, or false otherwise. */
    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % queue.length);
    }

    /** Removes all entries from this queue. */
    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    /** Ensures the queue has enough capacity to accommodate new entries.
        If the array is full, it doubles its size. */
    private void ensureCapacity() {
        if (frontIndex == ((backIndex + 2) % queue.length)) { // If array is full, double its size
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize);

            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;
            for (int index = 0; index < oldSize - 1; index++) {
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }

            frontIndex = 0;
            backIndex = oldSize - 2;
        }
    }

    /** Checks if the requested capacity exceeds the maximum allowed capacity.
        @param capacity The requested capacity
        @throws IllegalStateException if the requested capacity exceeds the maximum allowed capacity */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a queue whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
        }
    }
} // end ArrayQueue
