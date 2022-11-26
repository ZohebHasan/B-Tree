package cse214hw3;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Node<E> {
    protected int num; //number of elements
    protected int min; //minimum degree
    ArrayList<E> dataList; //TODO it will maintain upper and lower bound.
    ArrayList<Node<E>> childrenAddressList; //TODO it's length will maintain upper and lower bound.


    public Node(int min){
        if(min < 2) throw new UnsupportedOperationException("Minimum Degree must be at least 2, Source: Introduction to algorithms, Thomas H. Cormen; 4rth edition");
        this.min = min; //minimum degree
        this.num = 0;
        this.dataList = new ArrayList<>();
        this.childrenAddressList = new ArrayList<>();
    }

    public boolean isLeaf() { //TODO MUST HAVE!
        return childrenAddressList.isEmpty(); //returns true if it has children.
    }
    public boolean isFull() { //TODO MUST HAVE!
        return num == (2 * min) - 1;// returns true if the numbers of elements  are equal to the max values
    }
    @Override
    public String toString() {
        return toString(0);
    } // based on what toString() does, think about what ‘elements’ and ‘children’ can be
    private String toString(int depth) {
        StringBuilder builder = new StringBuilder();
        String blankPrefix = new String(new char[depth]).replace("\0", "\t");
        List<String> printedElements = new LinkedList<>();
        for (E e : dataList) printedElements.add(e.toString());
        String eString = String.join(" :: ", printedElements);
        builder.append(blankPrefix).append(eString).append("\n");
        childrenAddressList.forEach(c -> builder.append(c.toString(depth + 1)));
        return builder.toString();
    }
}
