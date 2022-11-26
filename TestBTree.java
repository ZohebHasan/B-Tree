package cse214hw3;


public class TestBTree {
    @SafeVarargs
    private static <T extends Comparable<T>> void addAllInThisOrder(BTree<T> theTree, T... items) {
        for (T item : items)
            theTree.add(item);
    }
    public static void main(String[] args) {
        BTree<Integer> integerBTree = new BTree<>(3);
        addAllInThisOrder(integerBTree, 10, 20, 30, 40, 50);
        System.out.println("Initial Test: ");
        System.out.println(integerBTree);
        integerBTree.add(60);
        integerBTree.add(70);
        integerBTree.add(80);
        integerBTree.add(90);
        integerBTree.add(100);
        integerBTree.add(110);
        integerBTree.add(120);
        integerBTree.add(130);
        integerBTree.add(140);
        integerBTree.add(150);
        integerBTree.add(160);
        integerBTree.add(170);
        integerBTree.add(180);
        integerBTree.add(190);
        integerBTree.add(200);
        integerBTree.add(210); //todo corner case 1 : test 1 sorted : pass | test 2 not sorted : pass
        integerBTree.add(220);
        integerBTree.add(230);
        integerBTree.add(240);
        integerBTree.add(250);
        integerBTree.add(260);
        integerBTree.add(270);
        integerBTree.add(280);//todo corner case 2: test 1 sorted : pass | test 2 not sorted : pass
        integerBTree.add(290);
        integerBTree.add(300);
        integerBTree.add(310);
        integerBTree.add(320);
        integerBTree.add(330);
        integerBTree.add(340);
        integerBTree.add(350);
        integerBTree.add(360);
        integerBTree.add(380);
        integerBTree.add(390);
        integerBTree.add(400);
        integerBTree.add(410);
        integerBTree.add(420);
        integerBTree.add(430);
        integerBTree.add(440);
        integerBTree.add(450);
        integerBTree.add(460);
        integerBTree.add(470);

        //todo fully functional.
        BTree<String> bTree = new BTree<>(3);
        bTree.add("z");
        bTree.add("y");
        bTree.add("x");
        bTree.add("w");
        bTree.add("v");
        bTree.add("u");
        bTree.add("t");
        bTree.add("s");
        bTree.add("r");
        bTree.add("q");
        bTree.add("p");
        bTree.add("o");
        bTree.add("n");
        bTree.add("m");
        bTree.add("l");
        bTree.add("k");
        bTree.add("j");
        bTree.add("i");
        bTree.add("h");
        bTree.add("g");
        bTree.add("f");
        bTree.add("e");
        bTree.add("d");
        bTree.add("c");
        bTree.add("b");
        bTree.add("a");
        System.out.println("Integer BTree Including test case 1 and 2 : pass ");
        System.out.println(integerBTree);
        System.out.println("String BTree Including test case 1 and 2 : pass ");
        System.out.println(bTree);

        NodeIndexPair<Integer> foundNumber = integerBTree.contains(90);
        NodeIndexPair<String> foundString = bTree.contains("a");

        System.out.printf("Element %d found at index %d of the following node:\n%s%n", 90, foundNumber.index, foundNumber.nodeLocation);
        System.out.println( "Element  'a' " + " found at index " +  foundString.index + " of the following node: ");
        System.out.println(foundString.nodeLocation);

    }
}
