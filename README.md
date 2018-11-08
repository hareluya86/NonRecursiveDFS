# NonRecursiveDFS sorting and printing
There are a set of common use cases in web applications where you want to use the Depth-First Search algorithm:
- Generating complex and long SQL SELECT statements with lots of nesting that resembles a tree.
- Printing a JSON object.
- Printing HTML objects.

This package sorts a set of uni-linked nodes (node->parent) using the Depth-First Search algorithm and returns the list in the sequence which the nodes will be visited, without using recursion (as they often cause stack overflow exceptions). It can also help you print out a Tree structure linearly like a list by implementing a set of methods.

## Assumptions:
- All nodes are part of a Tree - an acyclical graph.
- Nodes are comparable and sorted so that the root node can be found by `List.sort()` method.
- Nodes are linked to their parent nodes - parents can be found by implementing `DFSTreeNode.getParent()` method.

## Usage
Implement `DFSTreeNode` with your node class and all its methods:

    public class TreeNodeImpl implements DFSTreeNode
    
Then just use `DFSUtil` to sort the list of unsorted nodes:

    List<DFSTreeNode> results = DFSUtil.sortDFS(rawNodes);

Or implement the `DFSTreeNode.print...()` methods to do DFS printing:

    String dfsOutput = DFSUtil.printDFS(rawNodes);
    
That's it!


## Example test case

https://s3-us-west-2.amazonaws.com/webapplicationguy/DFSTree.png

After sorting, the expected result should be: 
    0,1,3,5,6,4,7,9,10,11,12,8,2

This is written in the unit test class 

        NonRecursiveDFS/src/test/java/com/webappguy/nonrecursivedfs/DFSUtilTest.java
 
Feel free to contribute more test caseses!
