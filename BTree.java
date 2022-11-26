package cse214hw3;

public class BTree<E extends Comparable<E>> implements AbstractBTree<E> { //fully functional and comparable.
    private static int minimumDegree; //TODO Static or no?
    private Node<E> root;

    public BTree(int min) {
        minimumDegree = min;
        this.root = new Node<>(minimumDegree);
    }

    private void insertNonFull(E element, Node<E> node) { //TODO
        int i = node.num - 1;
        //if it's leaf node
        if (node.isLeaf()) {
            if (node.dataList.size() < i + 1) {
                int j = 0;
                if (element.compareTo(node.dataList.get(j)) < 0) { //TODO need to check corner case with smaller value.
                    E temp = node.dataList.get(j);
                    node.dataList.set(j, element);
                    node.dataList.add(temp);
                    node.num++;
                    j++;
                }
            } else {
                while (i >= 0 && element.compareTo(node.dataList.get(i)) < 0) {
                    node.dataList.set(i, node.dataList.get(i)); //TODO not sure; might throw an exception.
                    i--;
                }
                node.dataList.add(i + 1, element);
                node.num++;
            }
        }
        //if it's not a leaf node which means an internal node holding the leaves
        else {
            while (i >= 0 && element.compareTo(node.dataList.get(i)) < 0) { //TODO Breakpoint
                i--;
            }
            i++;
            if (node.childrenAddressList.get(i).num == (2 * minimumDegree) - 1) {
                splitChild(node, node.childrenAddressList.get(i));
                if (element.compareTo(node.dataList.get(i)) > 0)
                    i++;

            }
            insertNonFull(element, node.childrenAddressList.get(i));
        }
    }


    private void splitChild(Node<E> parentNode, Node<E> childNode) { //TODO I will have to modify  an added one extra parameter index for the other search method.

        //if the child node has no children
        if (childNode.isLeaf()) {
            E medium = childNode.dataList.get((childNode.dataList.size() / 2));

            childNode.dataList.remove(medium);
            childNode.num = childNode.dataList.size();


            Node<E> left = new Node<>(minimumDegree);
            Node<E> right = new Node<>(minimumDegree);

            int i = 0;
            while (i < childNode.dataList.size() / 2) {
                left.dataList.add(childNode.dataList.get(i));
                i++;
            }

            left.num = left.dataList.size();

            int j = childNode.dataList.size() / 2;
            while (j < (childNode.dataList.size())) {
                right.dataList.add(childNode.dataList.get(j));
                j++;
            }
            right.num = right.dataList.size();

            // todo if medium is bigger
            if (medium.compareTo(parentNode.dataList.get(0)) > 0) { //todo functional
                int count = 0;
                while (count < parentNode.num && medium.compareTo(parentNode.dataList.get(0)) > 0) {
                    count++;
                }
                parentNode.dataList.add(count, medium);
                parentNode.num = parentNode.dataList.size();


                parentNode.childrenAddressList.set(count, left);
                parentNode.childrenAddressList.add(count + 1, right);
                parentNode.num = parentNode.dataList.size();

            }
            // todo medium is smaller
            else { // todo need to modify the logic
                int count = parentNode.num - 1;
                while (count > 0 && medium.compareTo(parentNode.dataList.get(0)) < 0) {
                    count--;
                }
                parentNode.dataList.add(count, medium); // todo need to compare maybe?
                parentNode.num = parentNode.dataList.size();


                parentNode.childrenAddressList.set(count, left);
                parentNode.childrenAddressList.add(count + 1, right);
                parentNode.num = parentNode.dataList.size();

            }
        }
        //todo if it's an internal node
        else { //todo need to add logic
            E medium = childNode.dataList.get((childNode.dataList.size() / 2));


            childNode.dataList.remove(medium);
            childNode.num = childNode.dataList.size();

            Node<E> left = new Node<>(minimumDegree);
            Node<E> right = new Node<>(minimumDegree);

            int i = 0;
            while (i < childNode.dataList.size() / 2) {
                left.dataList.add(childNode.dataList.get(i));
                i++;
            }

            left.num = left.dataList.size();

            int j = childNode.dataList.size() / 2;
            while (j < (childNode.dataList.size())) {
                right.dataList.add(childNode.dataList.get(j));
                j++;
            }

            right.num = right.dataList.size();

            // todo if medium is bigger
            if (medium.compareTo(parentNode.dataList.get(0)) > 0) {

                int count = 0;
                while (count < parentNode.num && medium.compareTo(parentNode.dataList.get(0)) > 0) {
                    count++;
                }
                parentNode.dataList.add(count, medium);
                parentNode.num = parentNode.dataList.size();



                //tranferring The addresses //todo needs crosscheck
                int x = 0;
                while (x < childNode.childrenAddressList.size() / 2) {
                    left.childrenAddressList.add(childNode.childrenAddressList.get(x));
                    x++;
                }

                int y = childNode.childrenAddressList.size() / 2;
                while (y < childNode.childrenAddressList.size()) {
                    right.childrenAddressList.add(childNode.childrenAddressList.get(y));
                    y++;
                }

                parentNode.childrenAddressList.set(count, left);
                parentNode.childrenAddressList.add(count + 1, right);
                parentNode.num = parentNode.dataList.size();
            }
            // todo if medium is bigger
            else {

                int count = parentNode.num - 1;
                while (count > 0 && medium.compareTo(parentNode.dataList.get(0)) < 0) {
                    count--;
                }
                parentNode.dataList.add(count, medium); // todo need to compare maybe?
                parentNode.num = parentNode.dataList.size();


                //tranferring The addresses
                int x = 0;
                while (x < childNode.childrenAddressList.size() / 2) {
                    left.childrenAddressList.add(childNode.childrenAddressList.get(x));
                    x++;
                }
                int y = childNode.childrenAddressList.size() / 2;
                while (y < childNode.childrenAddressList.size()) {
                    right.childrenAddressList.add(childNode.childrenAddressList.get(y));
                    y++;
                }

                parentNode.childrenAddressList.set(count, left);
                parentNode.childrenAddressList.add(count + 1, right);
                parentNode.num = parentNode.dataList.size();

            }
        }
    }


    private void splitRoot(int index) { //Todo need to deal with the corner case when root needs to have more than left and right.
        if (root.isLeaf()) { //if root has no children
            Node<E> newRoot = new Node<>(minimumDegree);
            //adding the elements and fixing the sizes in new root
            newRoot.dataList.add(root.dataList.get(index)); //adding the medium element
            newRoot.num = newRoot.dataList.size(); //num of new root

            //adding the elements and fixing the sizes in the current root
            root.dataList.remove(index); //removing the medium
            root.num = root.dataList.size(); //num of current root
            Node<E> left = root;
            Node<E> right = new Node<>(minimumDegree);
            int i = 0;
            while (i < left.dataList.size()) {
                E temp = left.dataList.get(index);
                left.dataList.remove(left.dataList.get(index));
                right.dataList.add(temp);
                i++;
            }

            left.num = left.dataList.size();//updating num of left
            right.num = right.dataList.size(); //updating num of right

            //adding new addresses
            newRoot.childrenAddressList.add(left);
            newRoot.childrenAddressList.add(right);
            root = newRoot;
        } else { //todo  if root has children

            Node<E> newRoot = new Node<>(minimumDegree);
            //adding the elements and fixing the sizes in new root
            newRoot.dataList.add(root.dataList.get(index)); //adding the medium element
            newRoot.num = newRoot.dataList.size(); //num of new root

            //adding the elements and fixing the sizes in the current root
            root.dataList.remove(index); //removing the medium
            root.num = root.dataList.size(); //num of current root
            Node<E> left = new Node<>(minimumDegree);
            Node<E> right = new Node<>(minimumDegree);
            int i = 0;
            while (i < root.dataList.size() / 2) {
                left.dataList.add(root.dataList.get(i));
                i++;
            }
            left.num = left.dataList.size();

            int j = root.dataList.size() / 2;
            while (j < (root.dataList.size())) {
                right.dataList.add(root.dataList.get(j));
                j++;
            }
            right.num = right.dataList.size();

            newRoot.childrenAddressList.add(left);
            newRoot.childrenAddressList.add(right);
            newRoot.num = newRoot.dataList.size();

            //tranferring The addresses
            int x = 0;
            while (x < root.childrenAddressList.size() / 2) {
                left.childrenAddressList.add(root.childrenAddressList.get(x));
                x++;
            }
            int y = root.childrenAddressList.size() / 2;
            while (y < root.childrenAddressList.size()) {
                right.childrenAddressList.add(root.childrenAddressList.get(y));
                y++;
            }
            root = newRoot;
        }
    }


    @Override
    public void add(E element) {
        if (root.dataList.contains(element)) //if the element is already in the node. //TODO not sure if I should call the childrenAddressList or the datalist
            return;
        else if (!root.isLeaf() && contains(element) != null)
            return;
        else if (root.dataList.isEmpty()) {
            root.dataList.add(element);
            root.num++;
        } else if (root.isFull()) { //&& root.isLeaf()
            splitRoot((root.dataList.size() / 2));
            insertNonFull(element, root); //TODO adding at wrong place in corner case.
        } else
            insertNonFull(element, root); //TODO breakpoint

    }

    @Override
    public NodeIndexPair<E> contains(E element) {
        return search(element, this.root);
    }

    private NodeIndexPair<E> search(E element, Node<E> node) {
        int i = 0;
        while (i < node.num && element.compareTo(node.dataList.get(i)) > 0) //TODO different from Lecture Slide
            ++i;
        if (i < node.num && element.compareTo(node.dataList.get(i)) == 0)
            return new NodeIndexPair<>(node, i);
        if (node.isLeaf()) //TODO check isLeaf
            return null;
        return search(element, node.childrenAddressList.get(i));
    }


    @Override
    public String toString() {
        return root.toString();
    }

}
