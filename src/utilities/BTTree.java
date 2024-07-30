package utilities;

import java.io.Serializable;

import exceptions.TreeException;

public class BTTree<E extends Comparable<? super E>> implements BSTreeADT<E>, Serializable{
	
	private BSTreeNode<E> root;
    private int size;

	public BTTree() {
		root = null;
        size = 0;
	}

	/**
	 * The node at the root of the Binary Search Tree will be returned.
	 * @return node stored at the root of tree is returned
	 * @throws TreeException if the root is empty.
	 */
	@Override
	public BSTreeNode<E> getRoot() throws TreeException {
		if (isEmpty()) {
            throw new TreeException("Tree is empty.");
        }
        return root;
     }
	
	/**
	 * Determines the row height of the tree and returns that value as an
	 * integer value.
	 * @return the height of the tree.
	 */
	@Override
	public int getHeight() {
		return findHeight(root);
	}
	
	private int findHeight(BSTreeNode<E> node) {
	    if (node == null) {
	        return 0; 
	    }

	    int leftHeight = findHeight(node.getLeft()); 
	    int rightHeight = findHeight(node.getRight());

	    return Math.max(leftHeight, rightHeight) + 1;
	}

	/**
	 * The number of elements currently stored in the tree is counted and
	 * the value is returned.
	 * @return number of elements currently stored in tree.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the tree is currently empty.
	 * @return returns boolean true if the tree is empty otherwise false.
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Clears all elements currently stored in tree and makes the tree empty.
	 */
	@Override
	public void clear() {
		root = null;
        size = 0;
    }

	/**
	 * Checks the current tree to see if the element passed in is stored in
	 * the tree. If the element is found in the tree the method returns true
	 * and if the element is not in the tree the method returns false.
	 * @param entry the element to find in the tree
	 * @return returns boolean true if element is currently in the tree and
	 * false if the element is not found in the tree
	 * @throws TreeException if the tree is empty.
	 */
	@Override
	public boolean contains(E entry) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Tree is empty.");
        }
        return search(entry) != null;
    }
	
	/**
	 * Retrieves a node from the tree given the object to search for.
	 * @param entry element object being searched
	 * @return the node with the element located in tree, null if not found
	 * @throws TreeException if the tree is empty
	 */
	@Override
	public BSTreeNode<E> search(E entry) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Tree is empty.");
        }
        return search(root, entry);
    }
	
	private BSTreeNode<E> search(BSTreeNode<E> node, E entry) {
        if (node == null) {
            return null;
        }
        int comparison = entry.compareTo(node.getElement());
        if (comparison == 0) {
            return node;
        } else if (comparison < 0) {
            return search(node.getLeft(), entry);
        } else {
            return search(node.getRight(), entry);
        }
    }

	/**
	 * Adds a new element to the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * @param newEntry the element being added to the tree
	 * @return a boolean true if the element is added successfully else false
	 * @throws NullPointerException if the element being added is null
	 */
	@Override
	public boolean add(E newEntry) throws NullPointerException {
		if (newEntry == null) {
            throw new NullPointerException("Cannot add null element.");
        }
        root = add(root, newEntry);
        size++;
        return true;
     }
	
	private BSTreeNode<E> add(BSTreeNode<E> node, E newEntry) {
        if (node == null) {
            return new BSTreeNode<>(newEntry);
        }
        int comparison = newEntry.compareTo(node.getElement());
        if (comparison < 0) {
            node.setLeft(add(node.getLeft(), newEntry));
        } else if (comparison > 0) {
            node.setRight(add(node.getRight(), newEntry));
        }
        return node;
    }
	
	/**
	 * Generates an in-order iteration over the contents of the tree. Elements
	 * are in their natural order.
	 * @return an iterator with the elements in the natural order
	 */
	@Override
	public Iterator<E> inorderIterator() {
		return new InOrderIterator<>(root);
	}

	/**
	 * Generates a pre-order iteration over the contents of the tree. Elements
	 * are order in such a way as the root element is first.
	 * @return an iterator with the elements in a root element first order
	 */
	@Override
    public Iterator<E> preorderIterator() {
		return new PreOrderIterator<>(root);
    }

	/**
	 * Generates a post-order iteration over the contents of the tree. Elements
	 * are order in such a way as the root element is last.
	 * @return an iterator with the elements in a root element last order
	 */
	@Override
    public Iterator<E> postorderIterator() {
		return new PostOrderIterator<>(root);
    }
    
}
