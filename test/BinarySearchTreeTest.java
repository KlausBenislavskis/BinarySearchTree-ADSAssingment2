import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BinarySearchTreeTest {

    private BinarySearchTreeNode<Integer> treeRoot;
    private BinarySearchTree<Integer> tree;
    private BinaryTreePrint print;

    @BeforeEach
    void setUp() {
        treeRoot = new BinarySearchTreeNode<>(10);

        treeRoot.addLeftChild(new BinarySearchTreeNode<>(6));
        treeRoot.addRightChild(new BinarySearchTreeNode<>(14));

        treeRoot.getLeftChild().addLeftChild(new BinarySearchTreeNode<>(4));
        treeRoot.getLeftChild().addRightChild(new BinarySearchTreeNode<>(7));

        treeRoot.getLeftChild().getLeftChild().addLeftChild(new BinarySearchTreeNode<>(2));
        treeRoot.getLeftChild().getLeftChild().addRightChild(new BinarySearchTreeNode<>(5));

        tree = new BinarySearchTree<>(treeRoot);

        print = new BinaryTreePrint();
    }

    @Test
    void findMin() {
        Assertions.assertEquals(2, tree.findMin());
    }

    @Test
    void findMax() {
        Assertions.assertEquals(14, tree.findMax());
    }

    @Test
    void removeElement() {
        print.printTree(treeRoot);
        tree.removeElement(4);
        print.printTree(treeRoot);
        tree.removeElement(73);
        print.printTree(treeRoot);
        Assertions.assertFalse(tree.contains(4));
    }

    @Test
    void rebalance() {
        print.printTree(tree.getRoot());
        tree.removeElement(4);
        System.out.println();
        print.printTree(tree.getRoot());
        System.out.println();
        tree.insert(11);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);
        tree.insert(15);
        tree.insert(16);
        tree.insert(17);
        tree.insert(18);
        tree.insert(19);
        tree.insert(20);
        tree.insert(21);
        tree.reBalance();
        print.printTree(tree.getRoot());

        List<Integer> preOrder = Arrays.asList(14, 10, 5, 2, 6, 7, 12, 11, 13, 18, 15, 16, 17, 20, 19, 21);
        Assertions.assertEquals(preOrder, tree.preOrder());

    }

    @Test
    void insert() {
        print.printTree(treeRoot);
        System.out.println();

        tree.insert(13);
        print.printTree(treeRoot);
        System.out.println();
        tree.insert(12);
        print.printTree(treeRoot);
        System.out.println();
        tree.insert(15);
        print.printTree(treeRoot);
        System.out.println();
        tree.insert(6);
        print.printTree(treeRoot);
        System.out.println();
        tree.insert(10);
        print.printTree(treeRoot);

        ArrayList<Integer> preOrder = new ArrayList<>();
        preOrder.add(10);
        preOrder.add(6);
        preOrder.add(4);
        preOrder.add(2);
        preOrder.add(5);
        preOrder.add(7);
        preOrder.add(14);
        preOrder.add(13);
        preOrder.add(12);
        preOrder.add(15);
        Assertions.assertEquals(preOrder, tree.preOrder());
    }


}