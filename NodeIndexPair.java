package cse214hw3;

public class NodeIndexPair<E> {
    public final Node<E> nodeLocation;
    public final int index;

    public NodeIndexPair(Node<E> foundNode, int index){
        this.nodeLocation = foundNode;
        this.index = index;
    }
}
