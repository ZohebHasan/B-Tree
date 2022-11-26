package cse214hw3;

public interface AbstractBTree<E extends Comparable<E>> {
    NodeIndexPair<E> contains (E element);
    void add(E element);
}
