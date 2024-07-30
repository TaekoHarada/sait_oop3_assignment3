package utilities;

import java.util.NoSuchElementException;
import java.util.Stack;

public class PreOrderIterator<E> implements Iterator<E> {
    private Stack<BSTreeNode<E>> stack;

    public PreOrderIterator(BSTreeNode<E> root) {
        stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements.");
        }
        BSTreeNode<E> node = stack.pop();
        E result = node.getElement();
        if (node.getRight() != null) {
            stack.push(node.getRight());
        }
        if (node.getLeft() != null) {
            stack.push(node.getLeft());
        }
        return result;
    }
}
