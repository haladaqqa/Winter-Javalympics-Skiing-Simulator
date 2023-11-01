
public class Ski {                                                             // This class represents the program in which the skier will go down the hill one step at a time
	
	// Private variable that stores a reference to the tree's root
	private BinaryTreeNode<SkiSegment> root;
	
	// public class constructor
	public Ski(String[] data)                                                  // Data is an array form of tree to be built
	{
		int size = data.length;
		SkiSegment[] parsedData = new SkiSegment[size];
		TreeBuilder<SkiSegment> treeBuilder = new TreeBuilder();
		
		for (int k = 0; k < size; k++) {                                     // Loops over the array of data to parse it into SkiSegment objects
			String intVal = String.valueOf(k);
			if (data[k] != null) {
				
				if (data[k].length() == 0) {                                   // Checks if the node is a normal segment of the mountain
					SkiSegment seg = new SkiSegment(intVal, data[k]);
					parsedData[k] = seg;
				}
				
				else if (data[k].contains("slalom")) {                         // Checks for slaloms
					SlalomSegment seg = new SlalomSegment(intVal, data[k]);
					parsedData[k] = seg;
				}
				
				else if (data[k].contains("jump")) {                           // Checks for jumps
					JumpSegment seg = new JumpSegment(intVal, data[k]);
					parsedData[k] = seg;
				}
			}
			else {
				// This is for leaf nodes
				parsedData[k] = null;
			}
		}
		
		root = treeBuilder.buildTree(parsedData).getRoot();                    // Sets the root to reference the tree that gets built using the treeBuilder class
	}
	
	// Calculates the path down the mountain using various helper methods
	public void skiNextSegment(BinaryTreeNode<SkiSegment> node, ArrayUnorderedList<SkiSegment> sequence){
		
		if (node != null) {                                                    // If the node is null, we have reached the end of our path, exits recursion
			sequence.addToRear(node.getData());
			// Calculates best next node
			BinaryTreeNode<SkiSegment> next = calculateNext(node.getRight(), node.getLeft());
			// Calls next iteration of recursion
			skiNextSegment(next, sequence);
		}
	}

	// helper method checks if a given node is a jump, slalom, normal, or null that returns boolean, represents if segmentType, and segment match
	private boolean checkSegmentStatus(BinaryTreeNode<SkiSegment> segment, String segmentType) {
		if (segment != null) {
			// Checks for slaloms and jumps, doesnt check for normal, because this function is not used to check for normal segments
			if (segmentType.equals("jump")) {
				return (segment.getData().toString().contains("jump"));
			}
			else {
				return (segment.getData().toString().contains("slalom"));
			}
		}
		// If segment is null, return false
		else {
			return false;
		}
	}
	
	// helper method calculates the next best node to take
	private BinaryTreeNode<SkiSegment> calculateNext(BinaryTreeNode<SkiSegment> rightSegment, BinaryTreeNode<SkiSegment> leftSegment) {
		
		// Checks if both children are jumps
		if ((checkSegmentStatus(leftSegment, "jump")) && (checkSegmentStatus(rightSegment, "jump")))
		{
			// Does height calculations to decide best jump
			int rightHeight = ((JumpSegment)rightSegment.getData()).getHeight();
			int leftHeight = ((JumpSegment)leftSegment.getData()).getHeight();
			
			if (rightHeight > leftHeight) {
				return rightSegment;
			}
			else if (leftHeight > rightHeight) {
				return leftSegment;
			}
			// Default case is right child if heights are equal
			else {
				return rightSegment;
			}
		}
		// Checks if neither are jumps
		else if (getJump(rightSegment, leftSegment) == null) {
			// Checks if left is slalom
			if (checkSegmentStatus(leftSegment, "slalom") == true) {
				
				// If left child is leeward, take it, otherwise take right, unless right is null
				if (((SlalomSegment)leftSegment.getData()).getDirection().equals("L") || rightSegment == null) {
					return leftSegment;
				}
				else {
					return rightSegment;
				}
			}
			// Checks if right child is slalom
			else if (checkSegmentStatus(rightSegment, "slalom") == true) {
				
				// If right child is leeward, take it, otherwise take left, unless left is null
				if (((SlalomSegment)rightSegment.getData()).getDirection().equals("L") || leftSegment == null) {
					return rightSegment;
				}
				else {
					return leftSegment;
				}
			}
			// Default case if other conditions not met, take right child unless it is null
			else {
				if (rightSegment == null) {
					return leftSegment;
				}
				else {
					return rightSegment;
				}
			}
		}
		// If only one of the children is a jump, take that path
		else {
			return getJump(rightSegment, leftSegment);	
		}
	}
	
	// helper method checks if a jump is available that returns a BinaryTreeNode that is a jump
	private BinaryTreeNode<SkiSegment> getJump(BinaryTreeNode<SkiSegment> rightSegment, BinaryTreeNode<SkiSegment> leftSegment)
	{
		// Checks for left child jump
		if (checkSegmentStatus(leftSegment, "jump")) {
			return leftSegment;
		}
		// Checks for right child jump
		else if (checkSegmentStatus(rightSegment, "jump")) {
			return rightSegment;
		}
		// Null otherwise
		return null;
	}

	public BinaryTreeNode<SkiSegment> getRoot()	{                                 // gets the root node of the tree
		return root;                                                              // returns the root node of tree
	}
}