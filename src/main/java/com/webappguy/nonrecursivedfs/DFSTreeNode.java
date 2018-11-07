/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webappguy.nonrecursivedfs;

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
    
}
