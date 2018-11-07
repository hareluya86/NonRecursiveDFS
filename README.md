# NonRecursiveDFS
Sorts a set of uni-linked nodes (node->parent) using the Depth-First Search algorithm and returns the list in the sequence which the nodes will be visited.

## Usage
Implement `DFSTreeNode` with your node class and all its methods:

    public class TreeNodeImpl implements DFSTreeNode
    
Then just use `DFSUtil` to sort the list of unsorted nodes:

    List<DFSTreeNode> results = DFSUtil.sortDFS(rawNodes);
    
That's it!

## Example test case

https://s3-us-west-2.amazonaws.com/webapplicationguy/DFSTree.png

After sorting, the expected result should be: 
    0,1,3,5,6,4,7,9,10,11,12,8,2

This is written in the unit test class 

        NonRecursiveDFS/src/test/java/com/webappguy/nonrecursivedfs/DFSUtilTest.java
 
Feel free to contribute more test caseses!
