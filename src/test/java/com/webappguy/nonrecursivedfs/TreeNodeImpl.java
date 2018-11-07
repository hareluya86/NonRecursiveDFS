/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webappguy.nonrecursivedfs;

import com.webappguy.nonrecursivedfs.DFSTreeNode;

/**
 *
 * @author vincent.lee
 */
public class TreeNodeImpl implements DFSTreeNode {
    int index;
    int parent;
    
    public TreeNodeImpl(int index, int parent) {
        this.index = index;
        this.parent = parent;
    }

    @Override
    public Object getParent() {
        return parent;
    }

    @Override
    public Object getObject() {
        return index;
    }

    @Override
    public int compareTo(DFSTreeNode anotherNode) {
        return index - (int)anotherNode.getObject();
    }

    @Override
    public boolean equals(DFSTreeNode anotherNode) {
        return index == (int)anotherNode.getObject();
    }

    @Override
    public boolean isChildOf(DFSTreeNode testParent) {
        return getParent().equals(testParent.getObject());
    }
    
}
