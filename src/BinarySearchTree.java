import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

    public BinarySearchTree(BinarySearchTreeNode<E> root) {
        super(root);
    }

    public boolean insert(E element) {

        if (super.getRoot() == null) {
            super.setRoot(new BinarySearchTreeNode<>(element));
            return true;
        } else
            return insert(element, (BinarySearchTreeNode<E>) super.getRoot());

    }

    private boolean insert(E element, BinarySearchTreeNode<E> root) {
        if (root.compareTo(element) > 0) {
            if (root.getLeftChild() == null) {
                root.addLeftChild(new BinarySearchTreeNode<>(element));
                return true;
            }
            insert(element, (BinarySearchTreeNode<E>) root.getLeftChild());
        } else if (root.compareTo(element) < 0) {
            if (root.getRightChild() == null) {
                root.addRightChild(new BinarySearchTreeNode<>(element));
                return true;
            }
            insert(element, (BinarySearchTreeNode<E>) root.getRightChild());
        }
        return false;
    }

    public E findMin() {
        return super.inOrder().get(0);
    }

    public E findMax() {
        ArrayList<E> inOrder = super.inOrder();
        return inOrder.get(inOrder.size() - 1);
    }

    public boolean removeElement(E element) {
        if (!contains(element)) {
            return false;
        }
        removeElement(element, (BinarySearchTreeNode<E>) getRoot());
        return true;
    }

    private void removeElement(E element, BinarySearchTreeNode<E> removableNode) {
        if (removableNode.compareTo(element) > 0) {
            removeElement(element, (BinarySearchTreeNode<E>) removableNode.getLeftChild());
            return;
        } else if (removableNode.compareTo(element) < 0) {
            removeElement(element, (BinarySearchTreeNode<E>) removableNode.getRightChild());
            return;
        }
        if (removableNode.getRightChild() == null) {
            if (removableNode.getLeftChild() == null) {
                removableNode = null;
                return;
            }
            removableNode = (BinarySearchTreeNode<E>) removableNode.getLeftChild();

        }
        BinarySearchTree<E> tempTree = new BinarySearchTree<>((BinarySearchTreeNode<E>) removableNode.getRightChild());
        BinarySearchTreeNode<E> successor = findElement(tempTree.findMin(), removableNode);
        removableNode.setElement(successor.getElement());
        removableNode.addRightChild(successor.getRightChild());

    }

    private BinarySearchTreeNode<E> findElement(E element, BinarySearchTreeNode<E> root) {
        if (root.compareTo(element) > 0) {
            return findElement(element, (BinarySearchTreeNode<E>) root.getLeftChild());
        } else if (root.compareTo(element) < 0) {
            return findElement(element, (BinarySearchTreeNode<E>) root.getRightChild());
        }
        return root;
    }

    public boolean contains(E element) {
        ArrayList<E> inOrder = super.inOrder();
        for (E node : inOrder) {
            if (node == element) {
                return true;
            }
        }
        return false;
    }

    public void reBalance() {
        ArrayList<E> inOrder = super.inOrder();
        BinarySearchTree<E> tempTree = new BinarySearchTree<>(new BinarySearchTreeNode<>(inOrder.get(inOrder.size() / 2)));
        reBalance(new ArrayList<>(inOrder.subList(0, inOrder.size() / 2)), tempTree);
        reBalance(new ArrayList<>(inOrder.subList(inOrder.size() / 2, inOrder.size())), tempTree);
        setRoot(tempTree.getRoot());

    }

    private void reBalance(List<E> inOrder, BinarySearchTree<E> tempTree) {
        tempTree.insert(inOrder.get(inOrder.size() / 2));
        inOrder.remove(inOrder.size() / 2);
        if (inOrder.size() >= 3) {
            reBalance(new ArrayList<>(inOrder.subList(0, inOrder.size() / 2)), tempTree);
            reBalance(new ArrayList<>(inOrder.subList(inOrder.size() / 2, inOrder.size())), tempTree);
        } else {
            for (E element : inOrder) {
                tempTree.insert(element);
            }
        }
    }
}
