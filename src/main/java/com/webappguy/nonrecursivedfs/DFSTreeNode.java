/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webappguy.nonrecursivedfs;

import java.util.List;

/**
 *
 * @author vincent.lee
 */
public interface DFSTreeNode {
    
    /**
     * 
     * @return parent object
     */
    public Object getParent();
    
    /**
     * 
     * @return this object
     */
    public Object getObject();
    
    public int compareTo(DFSTreeNode anotherNode);
    
    public boolean equals(DFSTreeNode anotherNode);
    
    public boolean isChildOf(DFSTreeNode testParent);
    
    public String printStart(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited);
    
    public String printBeforeChildren(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited);
    
    public String printAfterChildren(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited);
    
    public String printEnd(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited);
    
}
