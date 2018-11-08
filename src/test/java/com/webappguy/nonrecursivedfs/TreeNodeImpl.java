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
public class TreeNodeImpl implements DFSTreeNode {
    int index;
    int parent;
    String operator;
    
    public TreeNodeImpl(int index, int parent, String operator) {
        this.index = index;
        this.parent = parent;
        this.operator = operator;
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

    @Override
    public String printStart(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited) {
        return "";
    }

    @Override
    public String printBeforeChildren(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited) {
        String sql = "";
        TreeNodeImpl parent = (TreeNodeImpl) originalList.stream().filter(p -> this.isChildOf(p)).findFirst().orElse(null);
        TreeNodeImpl previousSibling = (TreeNodeImpl) visited.stream().filter(v -> v.getParent() == this.getParent() && v.getObject() != this.getObject()).reduce((first,second) -> second).orElse(null);
        // If this node is under a AND node and it is not the 1st child, add "JOIN previous_table p ON t.Id = p.Id "
        if(parent != null && "AND".equalsIgnoreCase(parent.operator) && previousSibling != null) {
            sql += "JOIN ";
        }
        // If this node is under an OR node and it is not the 1st child, add "UNION "
        else if(parent != null && "OR".equalsIgnoreCase(parent.operator) && previousSibling != null) {
            sql += "UNION ";
        }
        
        if ("SELECT".equalsIgnoreCase(operator)) {
            sql += "SELECT Id FROM table t_" + this.getObject();
        } else {
            // For AND and OR nodes, don't print any body statements
            sql += "SELECT Id FROM (";
        }
        
        return sql;
    }

    @Override
    public String printAfterChildren(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited) {
        String sql = "";
        // Select nodes do not have any children
        if ("SELECT".equalsIgnoreCase(operator))
            return sql;
        
        sql += ") t_" + this.getObject() + " ";
        
        TreeNodeImpl parent = (TreeNodeImpl) originalList.stream().filter(p -> this.isChildOf(p)).findFirst().orElse(null);
        TreeNodeImpl previousSibling = (TreeNodeImpl) visited.stream().filter(v -> v.getParent() == this.getParent() && v.getObject() != this.getObject()).reduce((first,second) -> second).orElse(null);
        // If this node is under a AND node and it is not the 1st child, add "JOIN previous_table p ON t.Id = p.Id "
        if(parent != null && "AND".equalsIgnoreCase(parent.operator) && previousSibling != null) {
            sql += "ON t_" + this.getObject() + ".Id =  t_" + previousSibling.getObject() + ".Id ";
        }
        // If this node is under an OR node and it is not the 1st child, add "UNION "
        else if(parent != null && "OR".equalsIgnoreCase(parent.operator) && previousSibling != null) {
            sql += "";
        }
        return sql;
    }

    @Override
    public String printEnd(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited) {
        return " ";
    }
    
}
