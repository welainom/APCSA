/**
 * Binary Tree of Comparable values.
 * The tree only has unique values. It does not add duplicate values.
 * 
 * @author	William Liu
 * @since	5/16/25
 */
import java.util.List;
import java.util.ArrayList;

public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root; // the root of the tree
	
	private final int PRINT_SPACES = 3; // print spaces between tree levels
	
	/** constructor for BinaryTree */
	public BinaryTree() { }

	/** Add a node to the tree (both iterative and recursive versions) */
	public void add(E value) {
		// Iterative implementation
		if (root == null) {
			root = new TreeNode<>(value);
			return;
		}
		TreeNode<E> current = root;
		while (true) {
			int cmp = value.compareTo(current.getValue());
			if (cmp < 0) {
				if (current.getLeft() == null) {
					current.setLeft(new TreeNode<>(value));
					return;
				}
				current = current.getLeft();
			} else if (cmp > 0) {
				if (current.getRight() == null) {
					current.setRight(new TreeNode<>(value));
					return;
				}
				current = current.getRight();
			} else {
				return; // duplicate value, do nothing
			}
		}
	}

	/** Recursive version of add */
	public void addRecursive(E value) {
		root = addRecursive(root, value);
	}

	private TreeNode<E> addRecursive(TreeNode<E> node, E value) {
		if (node == null) return new TreeNode<>(value);
		int cmp = value.compareTo(node.getValue());
		if (cmp < 0) node.setLeft(addRecursive(node.getLeft(), value));
		else if (cmp > 0) node.setRight(addRecursive(node.getRight(), value));
		return node;
	}

	/** Print Binary Tree Inorder */
	public void printInorder() {
		printInorder(root);
		System.out.println();
	}

	private void printInorder(TreeNode<E> node) {
		if (node == null) return;
		printInorder(node.getLeft());
		System.out.print(node.getValue() + " ");
		printInorder(node.getRight());
	}

	/** Print Binary Tree Preorder */
	public void printPreorder() {
		printPreorder(root);
		System.out.println();
	}

	private void printPreorder(TreeNode<E> node) {
		if (node == null) return;
		System.out.print(node.getValue() + " ");
		printPreorder(node.getLeft());
		printPreorder(node.getRight());
	}

	/** Print Binary Tree Postorder */
	public void printPostorder() {
		printPostorder(root);
		System.out.println();
	}

	private void printPostorder(TreeNode<E> node) {
		if (node == null) return;
		printPostorder(node.getLeft());
		printPostorder(node.getRight());
		System.out.print(node.getValue() + " ");
	}

	/** Return a balanced version of this binary tree */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<>();
		List<E> sortedList = new ArrayList<>();
		fillInorderList(root, sortedList);
		balancedTree.root = buildBalancedTree(sortedList, 0, sortedList.size() - 1);
		return balancedTree;
	}

	private void fillInorderList(TreeNode<E> node, List<E> list) {
		if (node == null) return;
		fillInorderList(node.getLeft(), list);
		list.add(node.getValue());
		fillInorderList(node.getRight(), list);
	}

	private TreeNode<E> buildBalancedTree(List<E> list, int start, int end) {
		if (start > end) return null;
		int mid = (start + end) / 2;
		TreeNode<E> node = new TreeNode<>(list.get(mid));
		node.setLeft(buildBalancedTree(list, start, mid - 1));
		node.setRight(buildBalancedTree(list, mid + 1, end));
		return node;
	}

	/** Remove value from Binary Tree */
	public void remove(E value) {
		root = remove(root, value);
	}

	/** Remove value from Binary Tree */
	public TreeNode<E> remove(TreeNode<E> node, E value) {
		if (node == null) return null;
		int cmp = value.compareTo(node.getValue());
		if (cmp < 0) node.setLeft(remove(node.getLeft(), value));
		else if (cmp > 0) node.setRight(remove(node.getRight(), value));
		else {
			if (node.getLeft() == null) return node.getRight();
			if (node.getRight() == null) return node.getLeft();
			TreeNode<E> successor = node.getRight();
			while (successor.getLeft() != null) successor = successor.getLeft();
			node = new TreeNode<>(successor.getValue(), node.getLeft(), remove(node.getRight(), successor.getValue()));
		}
		return node;
	}

	/********************************* Utilities ***********************************/
	public void printTree() {
		printLevel(root, 0);
	}

	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		printLevel(node.getRight(), level + 1);
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		printLevel(node.getLeft(), level + 1);
	}
}
