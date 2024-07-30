package utilities;

import java.util.NoSuchElementException;
import java.util.Stack;

public class InOrderIterator<E> implements Iterator<E> {
    private Stack<BSTreeNode<E>> stack;

    public InOrderIterator(BSTreeNode<E> root) {
        stack = new Stack<>();
        pushLeftNodes(root);
    }

    private void pushLeftNodes(BSTreeNode<E> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeft();
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
            pushLeftNodes(node.getRight());
        }
        return result;
    }
}
