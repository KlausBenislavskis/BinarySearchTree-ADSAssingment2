public class BinarySearchTreeNode<E extends Comparable<E>> extends BinaryTreeNode<E> implements Comparable<E> {
    public BinarySearchTreeNode(E element) {
        super(element);
    }

    @Override
    public int compareTo(E o) {
        return getElement().compareTo(o);
    }
}
