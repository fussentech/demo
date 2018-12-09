package com.fussentech.infra.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class TreeGraphUtil {

	// -----------------------------------------------------------
	// Binary Tree
	public static class TreeNode {
		private int value;
		public TreeNode left;
		public TreeNode right;
		public TreeNode parent;
		public TreeNode(int i) {value = i;}
		@Override
		public String toString() {return new String(Integer.toString(value));}
	}
	
	public static ArrayList<TreeNode> traverseBFS(TreeNode root) {
		if (root == null) {
			return null;
		}
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.remove(0);
			nodes.add(node);
			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);
		}
		return nodes;
	}
	
	// root -> left -> right
	public static void traversePreOrderRecursive(TreeNode root, ArrayList<TreeNode> nodes) {
		if (root == null || nodes == null) {
			return;
		}
		nodes.add(root);
		traversePreOrderRecursive(root.left, nodes);
		traversePreOrderRecursive(root.right, nodes);
	}
	public static ArrayList<TreeNode> traversePreOrder(TreeNode root) {
		if (root == null) {
			return null;
		}
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			nodes.add(node);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		return nodes;
	}
	// left -> root -> right
	public static void traverseInOrderRecursive(TreeNode root, ArrayList<TreeNode> nodes) {
		if (root == null || nodes == null) {
			return;
		}
		traverseInOrderRecursive(root.left, nodes);
		nodes.add(root);
		traverseInOrderRecursive(root.right, nodes);
	}
	public static ArrayList<TreeNode> traverseInOrder(TreeNode root) {
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.push(root);
				root = root.left;
			}
			else {
				TreeNode node = stack.pop();
				nodes.add(node);
				root = node.right;
			}
		}
		return nodes;
	}
	// left -> right -> root	
	public static void traversePostOrderRecursive(TreeNode root, ArrayList<TreeNode> nodes) {
		if (root == null || nodes == null) {
			return;
		}
		traversePostOrderRecursive(root.left, nodes);
		traversePostOrderRecursive(root.right, nodes);
		nodes.add(root);
	}
	public static ArrayList<TreeNode> traversePostOrder(TreeNode root) {
		if (root == null) {
			return null;
		}
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();	
		TreeNode preVisited = null;
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.push(root);
				root = root.left;
			}
			else {
				TreeNode node = stack.peek();
				if(node.right != null && node.right != preVisited) {
					root = node.right;
				}
				else {
					nodes.add(node);
					preVisited = stack.pop();
				}
			}
		}
		return nodes;
	}
	public static ArrayList<TreeNode> traversePostOrder2Stack(TreeNode root) {
		if (root == null) {
			return null;
		}
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		stack1.push(root);
		while (!stack1.isEmpty()) {
			TreeNode node = stack1.pop();
			stack2.push(node);
			if (node.left != null) {
				stack1.push(node.left);
			}
			if (node.right != null) {
				stack1.push(node.right);
			}
		}
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		while (!stack2.isEmpty()) {
			TreeNode node = stack2.pop();
			nodes.add(node);
		}
		return nodes;
	}
	
	// Use sorted array to create Binary Tree
	public static TreeNode createBTreeWithMinHeight(int[] sortedArray) {
		if (sortedArray == null || sortedArray.length == 0) {
			return null;
		}
		if (sortedArray.length == 1) {
			return new TreeNode(sortedArray[0]);
		}
		int mid = sortedArray.length / 2;
		TreeNode parent = new TreeNode(sortedArray[mid]);
		int[] leftArray = Arrays.copyOfRange(sortedArray, 0, mid);
		parent.left = createBTreeWithMinHeight(leftArray);
		if (parent.left != null) {
			parent.left.parent = parent;
		}
		int[] rightArray = Arrays.copyOfRange(sortedArray, mid +1, sortedArray.length);
		parent.right = createBTreeWithMinHeight(rightArray);
		if (parent.right != null) {
			parent.right.parent = parent;
		}
		return parent;
	}
	
	public static int maxHeight(TreeNode root) {
		int height = 0;
		if (root != null) {
			int leftHeight = maxHeight(root.left);
			int rightHeight = maxHeight(root.right);
			int max = leftHeight > rightHeight ? leftHeight : rightHeight;
			height = max + 1;
		}
		return height;
	}
	
	public static int minHeight(TreeNode root) {
		int height = 0;
		if (root != null) {
			int leftHeight = maxHeight(root.left);
			int rightHeight = maxHeight(root.right);
			int min = leftHeight < rightHeight ? leftHeight : rightHeight;
			height = min + 1;			
		}
		return height;
	}
	
	public static boolean isBalanced(TreeNode root) {
		return maxHeight(root) - minHeight(root) <= 1;
	}
	
	public static LinkedList<TreeNode> getNodesAtDepth(TreeNode root, int depth) {
		if (root == null || depth < 0) {
			return null;
		}
		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		nodes.push(root);
		while (depth > 0) {
			LinkedList<TreeNode> newnodes = new LinkedList<TreeNode>();
			for (TreeNode n : nodes) {
				if (n.left != null) {
					newnodes.add(n.left);
				}
				if (n.right != null) {
					newnodes.add(n.right);
				}
			}
			if (newnodes.isEmpty()) {
				return null;
			}
			nodes = newnodes;
			depth--;
		}
		return nodes;
	}
	
	public static ArrayList<LinkedList<TreeNode>> getNodesAtAllLevels(TreeNode root) {
		if (root == null) {
			return null;
		}
		ArrayList<LinkedList<TreeNode>> allnodes = new ArrayList<LinkedList<TreeNode>>();
		LinkedList<TreeNode> levelnodes = new LinkedList<TreeNode>();
		levelnodes.add(root);
		allnodes.add(levelnodes);
		int level = 0;
		while (true) {
			levelnodes = new LinkedList<TreeNode>();
			for (TreeNode n : allnodes.get(level)) {
				if (n.left != null) {
					levelnodes.add(n.left);
				}
				if (n.right != null) {
					levelnodes.add(n.right);
				}
			}
			if (levelnodes.isEmpty()) {
				break;
			}
			else {
				allnodes.add(levelnodes);
			}
			level++;
		}
		return allnodes;
	}
	
	public static TreeNode getNextNodePreOrder(TreeNode node) {
		if (node == null) {
			return null;
		}
		TreeNode next = null;
		if (node.left != null) {
			// node has left
			next = node.left;
		}
		else if (node.right != null) {
			// node has no left but right
			next = node.right;
		}
		else if (node.parent != null && node.parent.left == node) {
			// node is left leaf 
			TreeNode lr = getParentHavingRight(node);
			if (lr != null) {
				next = lr.right;
			}
		}
		else if (node.parent != null && node.parent.right == node) {
			//  node is right leaf 
			TreeNode lp = getLeastLeftParent(node.parent);
			if (lp != null) {
				TreeNode lr = getParentHavingRight(lp);
				if (lr != null) {
					next = lr.right;
				}
			}
		}
		return next;
	}
	
	public static TreeNode getLeastLeftParent(TreeNode node) {
		if (node == null || node.parent == null) {
			return null;
		}
		while (node.parent != null && node.parent.right == node) {
			node = node.parent;
		}
		return node;
	}
	
	public static TreeNode getParentHavingRight(TreeNode node) {
		if (node == null || node.parent == null) {
			return null;
		}
		if (node.parent.right != null) {
			return node.parent;
		}
		node = node.parent;
		while (node.parent != null && node.parent.right == null) {
			node = node.parent;
		}
		return node;
	}
	
	public static TreeNode getMostRightNode(TreeNode node) {
		if (node == null) {
			return null;
		}
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	public static TreeNode getNextNodeInOrder(TreeNode node) {
		TreeNode next = null;
		if (node != null) {
			if (node.right != null) {
				// node has right child so get the most left of child
				next = getMostLeftNode(node.right);
			}
			else {
				TreeNode parent = node.parent;
				if (parent != null) {
					if (parent.left == node) {
						// node is the parent's left, then get parent
						next = parent;
					}
					else {
						// node is the parent's right, then go further
						while ((next = parent.parent) != null) {
							if (parent == next.left) {
								break;
							}
							parent = next;
						}
					}
				}
			}
		}
		return next;
	}
	
	public static TreeNode getMostLeftNode(TreeNode node) {
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	// If n1 and n2 at root's different side, root will be the node
	public static TreeNode getFirstCommonAncestor(TreeNode root, 
												 TreeNode n1, TreeNode n2) {
		if (root == null) {
			return null;
		}
		if (root == n1 && (findNode(root.left, n2) || findNode(root.right, n2))) {
			return root;
		}
		if (root == n2 && (findNode(root.left, n1) || findNode(root.right, n1))) {
			return root;
		}
		TreeNode result = null;
		if (findNode(root.left, n1) && findNode(root.left, n2)) {
		//if (findNodes(root.left, n1, n2) == 2) {
			// both at the left side
			result = getFirstCommonAncestor(root.left, n1, n2);
		}
		else if (findNode(root.right, n1) && findNode(root.right, n2)) {
		//else if (findNodes(root.right, n1, n2) == 2) {
			// both at the right side
			result = getFirstCommonAncestor(root.right, n1, n2);
		}
		else if (findNode(root, n1) && findNode(root, n2)) {
			result = root;
		}
		return result;
	}
	public static boolean findNode(TreeNode root, TreeNode node) {
		if (root == null) {
			return false;
		}
		System.out.println("visiting node: "+root.value);
		if (root == node) {
			return true;
		}
		return findNode(root.left, node) || findNode(root.right, node);
	}
	
	public static int findNodes(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) {
			return 0;
		}
		System.out.println("processing node: "+root.value);
		int count = 0;
		if (root == n1 || root == n2) {
			count++;
		}
		count += findNodes(root.left, n1, n2);
		if (count == 2) {
			return 2;
		}
		count += findNodes(root.right, n1, n2);
		return count;
	}
	
	public static boolean isSubTree(TreeNode small, TreeNode big) {
		if (big == null) {
			return false;
		}
		if (big.value == small.value) {
			if (compareTrees(big, small)) {
				return true;
			}
		}
		if (isSubTree(small, big.left) || isSubTree(small, big.right)) {
			return true;
		}
		return false;
	}
	
	public static boolean compareTrees(TreeNode big, TreeNode small) {
		if (small == null) {
			// end of recursive comparison
			return true;
		}
		if (big == null) {
			return false;
		}
		if (big.value != small.value) {
			return false;
		}
		if (compareTrees(big.left, small.left) && compareTrees(big.right, small.right)) {
			return true;
		}
		return false;
	}
	
	// ---------------------------------------------------------------
	// Graph
	public static class GraphNode {
		boolean visited = false;
		public ArrayList<GraphNode> adjs;
		
	}
	
	public static class Graph {
		private ArrayList<GraphNode> nodes;
		public void resetNodes() {
			for (GraphNode n : nodes) {
				n.visited = false;
			}
		}
		public void addNode() {
			GraphNode n = new GraphNode();
			nodes.add(n);
		}
		// recursive
		public boolean hasPath(GraphNode src, GraphNode dst) {
			resetNodes();
			src.visited = true;
			boolean found = false;
			if (src == dst) {
				found = false;
			}
			ArrayList<GraphNode> nodes = src.adjs;
			for (GraphNode n : nodes) {
				if (n.visited != true) {
					n.visited = true;
					if (n == dst || hasPath(n, dst)) {
						found = true;
						break;
					}
				}
			}
			return found;
		}
		// iterative
		public boolean foundPath(GraphNode src, GraphNode dst) {
			boolean found = false;
			resetNodes();
			Stack<GraphNode> nodes = new Stack<GraphNode>();
			src.visited = true;
			nodes.push(src);
			while (!nodes.isEmpty() && !found) {
				GraphNode node = nodes.pop();
				if (node != null) {
					for (GraphNode n : node.adjs) {
						if (n.visited == false) {
							n.visited = true;
							if (n == dst) {
								found = true;
								break;
							}
							else {
								nodes.push(n);
							}
						}
					}
				}
			}
			return found;
		}
	}
}
