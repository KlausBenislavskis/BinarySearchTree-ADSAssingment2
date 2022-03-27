import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> {
    private BinaryTreeNode<E> root;


    public BinaryTree(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return getSize() <= 0;
    }

    //I know this is lazy and not good for performance and you should not do this
    public int getSize() {
        return inOrder().size();
    }

    public boolean contains(E element) {
        return inOrder().contains(element);
    }

    public ArrayList<E> inOrder() {
        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        ArrayList<E> nodes = new ArrayList<>();
        BinaryTreeNode<E> node = root;
        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.getLeftChild();
            }
            if (stack.isEmpty()) {
                return nodes;
            }
            node = stack.pop();
            nodes.add(node.getElement());
            node = node.getRightChild();
        }
    }

    public ArrayList<E> preOrder() {
        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        ArrayList<E> nodes = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<E> n = stack.pop();
            nodes.add(n.getElement());
            checkIfNullAndPushNode(stack, n.getRightChild());
            checkIfNullAndPushNode(stack, n.getLeftChild());
        }
        return nodes;
    }

    public ArrayList<E> postOrder() {
        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        ArrayList<E> nodes = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<E> n = stack.pop();
            checkIfNullAndPushNode(stack, n.getLeftChild());
            checkIfNullAndPushNode(stack, n.getRightChild());
            nodes.add(n.getElement());
        }
        ArrayList<E> nodes2 = new ArrayList<>();
        for (int i = nodes.size() - 1; i >= 0; i--) {
            nodes2.add(nodes.get(i));
        }
        return nodes2;
    }

    public ArrayList<E> levelOrder() {
        ArrayList<E> nodes = new ArrayList<>();
        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> n = queue.poll();
            nodes.add(n.getElement());
            checkIfNullAndQNode(queue, n.getLeftChild());
            checkIfNullAndQNode(queue, n.getRightChild());
        }
        return nodes;
    }

    private void checkIfNullAndQNode(Queue<BinaryTreeNode<E>> queue,
                                     BinaryTreeNode<E> node) {
        if (node != null) {
            queue.add(node);
        }
    }

    private void checkIfNullAndPushNode(Stack<BinaryTreeNode<E>> stack,
                                        BinaryTreeNode<E> node) {
        if (node != null) {
            stack.push(node);
        }
    }

    public int height() {
        return height(root) - 1;
    }

    private int height(BinaryTreeNode<E> root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.getLeftChild());
        int rightHeight = height(root.getRightChild());
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
