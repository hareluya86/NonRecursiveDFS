/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webappguy.nonrecursivedfs;

import com.webappguy.nonrecursivedfs.impl.TreeNodeImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 *
 * @author vincent.lee
 */
public class DFSUtil {
    
    
    public static List<DFSTreeNode> sortDFS(final List<DFSTreeNode> nodes) {
        
        // If empty list, return empty list
        if(nodes == null || nodes.size() <= 0)
            return new ArrayList<>();
        
        List<DFSTreeNode> sorted = new ArrayList<>(nodes);
        List<DFSTreeNode> visited = new ArrayList<>();
        Stack<DFSTreeNode> parents = new Stack<>();
        
        // Find the root node and push it in parents
        sorted.sort(new Comparator<DFSTreeNode>(){
            @Override
            public int compare(DFSTreeNode o1, DFSTreeNode o2) {
                return o1.compareTo(o2);
            }
        });
        DFSTreeNode root = sorted.get(0);
        parents.push(root);
        while(visited.size() < nodes.size()) {
            DFSTreeNode currNode = parents.pop();
            if(!visited.contains(currNode)) visited.add(currNode);
            
            // Find all children
            List<DFSTreeNode> children = sorted.stream().filter(n -> n.isChildOf(currNode))
                    .collect(Collectors.toList());
            for(DFSTreeNode child : children){
                // If child was already visited, skip it
                if(visited.contains(child)) continue;
                // If not visited, push it to parents stack together with currNode
                parents.push(currNode);
                parents.push(child);
                // Break as we don't want to visit any siblings until we've completed the current line of descendants
                break;
            }
        }
        
        return visited;
    }
    
    public static void main(String[] args) {
        // test data
        List<DFSTreeNode> rawNodes = new ArrayList<>();
        
        rawNodes.add(new TreeNodeImpl(11,10));
        rawNodes.add(new TreeNodeImpl(10,9));
        rawNodes.add(new TreeNodeImpl(3,1));
        rawNodes.add(new TreeNodeImpl(5,3));
        rawNodes.add(new TreeNodeImpl(7,4));
        rawNodes.add(new TreeNodeImpl(12,9));
        rawNodes.add(new TreeNodeImpl(2,0));
        rawNodes.add(new TreeNodeImpl(8,4));
        rawNodes.add(new TreeNodeImpl(4,1));
        rawNodes.add(new TreeNodeImpl(6,5));
        rawNodes.add(new TreeNodeImpl(1,0));
        rawNodes.add(new TreeNodeImpl(9,7));
        rawNodes.add(new TreeNodeImpl(0,-1));
        
        
        List<DFSTreeNode> results = sortDFS(rawNodes);
        
        for(DFSTreeNode node : results){
            System.out.println(node.getObject());
        }
    }
}
