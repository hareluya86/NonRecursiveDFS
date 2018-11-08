/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webappguy.nonrecursivedfs;

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
        while(!parents.isEmpty()) {
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
    
    public static String printDFS(final List<DFSTreeNode> nodes) {
        
        // If empty list, return empty list
        if(nodes == null || nodes.size() <= 0)
            return "";
        
        String print = "";
        
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
        while(!parents.isEmpty()) {
            DFSTreeNode currNode = parents.pop();
            if(!visited.contains(currNode)) {
                visited.add(currNode);
                print += currNode.printStart(nodes,visited);
                print += currNode.printBeforeChildren(nodes,visited);
            }
            
            // Find all children
            List<DFSTreeNode> children = sorted.stream().filter(n -> n.isChildOf(currNode))
                    .collect(Collectors.toList());
            // To indicate if we have already visited all children of the current node
            boolean noMoreChildren = true;
            for(DFSTreeNode child : children){
                // If child was already visited, skip it
                if(visited.contains(child)) continue;
                // If not visited, push it to parents stack together with currNode
                parents.push(currNode);
                parents.push(child);
                // Set noMoreChildren to false if we are going deeper
                noMoreChildren = false;
                // Break as we don't want to visit any siblings until we've completed the current line of descendants
                break;
            }
            
            // If no more children for the current node, close the node
            if(noMoreChildren) {
                print += currNode.printAfterChildren(nodes,visited);
                print += currNode.printEnd(nodes,visited);
            }
        }
        return print;
    }
}
