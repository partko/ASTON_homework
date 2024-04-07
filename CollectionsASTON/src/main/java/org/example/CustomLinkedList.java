package org.example;

import java.util.Collection;
import java.util.Comparator;

public class CustomLinkedList<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    public static <E extends Comparable<? super E>> void bubbleSort(CustomLinkedList<E> list) {
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

    public static <E> void bubbleSort(CustomLinkedList<E> list, Comparator<? super E> comparator) {
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

    public CustomLinkedList() {
    }

    public CustomLinkedList(Collection<? extends T> collection) {
        addAll(collection);
    }

    public void add(T element) {
        final Node<T> oldLast = last;
        final Node<T> newNode = new Node<>(oldLast, element, null);
        last = newNode;
        if (oldLast == null)
            first = newNode;
        else
            oldLast.next = newNode;
        size++;
    }

    public void add(int index, T element) {
        if (index == size) {
            final Node<T> oldLastNode = last;
            final Node<T> newNode = new Node<>(oldLastNode, element, null);
            last = newNode;
            if (oldLastNode == null)
                first = newNode;
            else
                oldLastNode.next = newNode;
        } else {
            Node<T> oldNode = getNode(index);
            final Node<T> previous = oldNode.prev;
            final Node<T> newNode = new Node<>(previous, element, oldNode);
            oldNode.prev = newNode;
            if (previous == null)
                first = newNode;
            else
                previous.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public Node<T> getNode(int index) {
        Node<T> node = first;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    public void set(int index, T element) {
        Node<T> node = getNode(index);
        node.data = element;
    }

    public void remove(int index) {
        Node<T> node = getNode(index);
        final T element = node.data;
        final Node<T> next = node.next;
        final Node<T> prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.data = null;
        size--;
    }

    public void addAll(Collection<? extends T> collection) {
        Object[] newCollection = collection.toArray();
        int numNew = newCollection.length;
        if (numNew == 0)
            return;
        Node<T> previous = last;

        for (Object o : newCollection) {
            T element = (T) o;
            Node<T> newNode = new Node<>(previous, element, null);
            if (previous == null)
                first = newNode;
            else
                previous.next = newNode;
            previous = newNode;
        }
        last = previous;
        size += numNew;
    }

    public int size() {
        return size;
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.data = element;
            this.next = next;
            this.prev = prev;
        }
    }

    // для теста сортировки
    public <E> E[] toArray() {
        int i = 0;
        Object[] result = new Object[size];
        for (Node<T> x = first; x != null; x = x.next)
            result[i++] = x.data;
        return (E[]) result;
    }
}
