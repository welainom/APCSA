import java.util.Scanner;

/**
 *	The driver program for creating and manipulating
 *	a binary tree of state information.
 *
 *	@author		William Liu
 *	@since		5/23/25
 */
public class StateTree
{
	// Fields
	private BinaryTree<State> bTree;
	private final String IN_FIlE = "states2.txt";	// input file
	
	public StateTree() {
		bTree = new BinaryTree<State>();
	}
	
	public static void main ( String [] args )
	{
		StateTree treeOrder = new StateTree();
		treeOrder.mainMenu();
	}
 
	public void mainMenu ()
	{
		String choice;

		do
		{
			System.out.println("Binary Tree algorithm menu\n");
			System.out.println("(1) Read Data from a file");
			System.out.println("(2) Print the list");
			System.out.println("(3) Search the list");
			System.out.println("(4) Delete node");
			System.out.println("(5) Count nodes");
			System.out.println("(6) Clear the list");
			System.out.println("(7) Print the level");
			System.out.println("(8) Print depth of tree");
			System.out.println("(Q) Quit\n");
			choice = Prompt.getString("Choice");
			System.out.println();

			if ('1' <= choice.charAt(0) && choice.charAt(0) <= '8')
			{
				switch (choice.charAt(0))
				{
					case '1' :	
						loadData();		
						break;
					case '2' :
						System.out.println();
						System.out.println("The tree printed inorder\n");
						bTree.printInorder();
						System.out.println();
						break;
					case '3' :
						find();
						break;
					case '4' :
						delete();
						break;
					case '5' :
						System.out.println("Number of nodes = " + size(bTree.getRoot()));
						System.out.println();
						break;
					case '6' :
						clear();
						break;
					case '7' :
						printLevel();
						break;
					case '8' :
						if (depth(bTree.getRoot(), -1) > -1)
							System.out.println("Depth of tree = " + depth(bTree.getRoot(), -1));
						else
							System.out.println("Tree empty");
						System.out.println();
						break;
				}
			}
		}
		while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
	}
	
	/**	Load the data into the binary tree */
	public void loadData() {
		Scanner in = FileUtils.openToRead(IN_FIlE);
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] parts = line.split("\\s+");
			
			State s = new State(parts[0], parts[1], Integer.valueOf(parts[2]), Integer.valueOf(parts[3]),
								Integer.valueOf(parts[4]), parts[5], Integer.valueOf(parts[6]), 
								Integer.valueOf(parts[7]), Integer.valueOf(parts[8]));
			bTree.add(s);
		}
	}
	
	public void printList() {
		bTree.printInorder();
	}

	/**	Find the node in the tree */
	public void find() {
		String name = Prompt.getString("Name of state:");
		find(name, bTree.getRoot());
	}

	public void find(String name, TreeNode<State> node) {
		if (node == null) return;
		if (node.getValue().getName().equalsIgnoreCase(name)) {
			System.out.println(node.getValue());
			return;
		}
		find(name, node.getLeft());
		find(name, node.getRight());
	}
	
	/** Delete a node */
	public void delete() {
		String name = Prompt.getString("Name of state:");
		State s = new State(name, " ", 0, 0, 0, " ", 0, 0, 0);
		bTree.remove(s);
	}
	
	/**	Returns the number of nodes in the subtree - recursive
	 *	@param node		the root of the subtree
	 *	@return			the number of nodes in the subtree
	 */
	public int size(TreeNode<State> node) {
		// ALmost done 
		if (node == null) return 0;
		// if (node.getLeft() == null && node.getRight() == null) {
		// 	return 1;
		// }
		return size(node.getLeft()) + size(node.getRight()) + 1;
	}
	
	/**	Clear out the binary tree */
	public void clear() {
		bTree = new BinaryTree<>();
	}
	
	/**	Print the level requested */
	public void printLevel() {
		int level = Integer.parseInt(Prompt.getString("Enter level number:"));
		System.out.println("\nNodes at level " + level + ":");
		printLevel(bTree.getRoot(), level, 0);
		System.out.println("\n");
	}

	public void printLevel(TreeNode<State> node, int tgtlevel, int curLevel) {
		if (node == null) return;
		if (curLevel == tgtlevel) System.out.print(node.getValue().getName() + " ");
		printLevel(node.getLeft(), tgtlevel, curLevel + 1);
		printLevel(node.getRight(), tgtlevel, curLevel + 1);
	}
	
	/**	Returns the depth of the subtree - recursive
	 *	@param node		the root of the subtree
	 *	@return			the depth of the subtree
	 */
	public int depth(TreeNode<State> node, int depth) {
		if (node == null) return depth;
		return Math.max(depth(node.getLeft(), depth + 1), depth(node.getRight(), depth + 1));
	}
	
}
