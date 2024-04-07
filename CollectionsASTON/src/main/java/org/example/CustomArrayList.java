package org.example;

import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] data;
    private int size = 0;

    public static <E extends Comparable<? super E>> void bubbleSort(CustomArrayList<E> list) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 0; i < list.size-1; i++) {
                if (list.get(i).compareTo(list.get(i+1)) > 0) {
                    needIteration = true;
                    E tmp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, tmp);
                }
            }
        }
    }

    public static <E> void bubbleSort(CustomArrayList<E> list, Comparator<? super E> comparator) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 0; i < list.size-1; i++) {
                if (comparator.compare(list.get(i), list.get(i+1)) > 0) {
                    needIteration = true;
                    E tmp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, tmp);
                }
            }
        }
    }

    public CustomArrayList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    public CustomArrayList(int capacity) {
        this.data = new Object[capacity];
    }

    public CustomArrayList(Collection<? extends T> collection) {
        Object[] newData = collection.toArray();
        this.data = new Object[calculateNewLength(newData.length)];
        for (Object o: newData) {
            data[size] = o;
            size++;
        }
    }

    public void add(T element) {
        if (checkCapacity()) {
            increaseLength();
        }
        data[size] = element;
        size++;
    }

    public void add(int index, T element) {
        if (checkCapacity()) {
            increaseLength();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        set(index, element);
        size++;
    }

    public T get(int index) {
        return (T) data[index];
    }

    public void set(int index, T value) {
        data[index] = value;
    }

    public void remove(int index) {
        for (int i = index; i < size; i++) {
            data[i] = data[i+1];
        }
        size--;
    }

    public int size() {
        return size;
    }

    public void addAll(Collection<? extends T> collection) {
        Object[] newData = collection.toArray();
        if (newData.length > data.length - size) {
            increaseLengthToValue(calculateNewLength(newData.length + size));
        }
        for (Object o: newData) {
            data[size] = o;
            size++;
        }
    }

    private boolean checkCapacity() {
        return data.length == size;
    }

    private void increaseLength() {
        Object[] newData = new Object[(int) (data.length * 1.5f)];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void increaseLengthToValue(int value) {
        Object[] newData = new Object[value];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private int calculateNewLength(int minCapacity) {
        int newSize = DEFAULT_SIZE;
        while (newSize < minCapacity) {
            newSize = (int) (newSize * 1.5f);
        }
        return newSize;
    }

    // для теста сортировки
    public <T> T[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return (T[]) result;
    }

}
