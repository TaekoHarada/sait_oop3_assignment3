package utilities;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class TreeIterator<E> implements Iterator<E> {
    private Stack<BSTreeNode<E>> stack;
    private ArrayList<E> elements;
    private int currentIndex;

    public TreeIterator(BSTreeNode<E> root, TreeTraversalOrder order) {
        stack = new Stack<>();
        elements = new ArrayList<>();
        currentIndex = 0;
        traverse(root, order);
    }

    private void traverse(BSTreeNode<E> node, TreeTraversalOrder order) {
        if (node == null) return;
        if (order == TreeTraversalOrder.INORDER) {
            traverse(node.getLeft(), order);
            elements.add(node.getElement());
            traverse(node.getRight(), order);
        } else if (order == TreeTraversalOrder.PREORDER) {
            elements.add(node.getElement());
            traverse(node.getLeft(), order);
            traverse(node.getRight(), order);
        } else if (order == TreeTraversalOrder.POSTORDER) {
            traverse(node.getLeft(), order);
            traverse(node.getRight(), order);
            elements.add(node.getElement());
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < elements.size();
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements.");
        }
        return elements.get(currentIndex++);
    }
}

enum TreeTraversalOrder {
    INORDER,
    PREORDER,
    POSTORDER
}
