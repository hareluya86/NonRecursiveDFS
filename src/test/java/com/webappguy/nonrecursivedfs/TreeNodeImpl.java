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
        String sql = "";  
        
        return sql;
    }

    @Override
    public String printBeforeChildren(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited) {
        String sql = "";
        TreeNodeImpl parent = (TreeNodeImpl) originalList.stream().filter(p -> this.isChildOf(p)).findFirst().orElse(null);
        TreeNodeImpl elder = (TreeNodeImpl) visited.stream().filter(v -> v.getParent() == this.getParent() && v.getObject() != this.getObject()).reduce((first,second) -> second).orElse(null);
        
        // If select nodes
        if ("SELECT".equalsIgnoreCase(operator)) {
            // 1. If this node has no elder sibling, print as a select statement
            if (elder == null) {
                sql += "SELECT Id FROM table t_" + this.getObject() + " ";
            } 
            // 2. If this node has an elder sibling and parent was an AND, print as join
            else if ("AND".equalsIgnoreCase(parent.operator)) {
                sql += "JOIN table t_" + this.getObject() + " ON t_" + this.getObject() + ".Id = t_" + elder.getObject() + ".Id ";
            }
            // 3. If this node has an elder sibling and parent was a OR, print as a select statement
            else if ("OR".equalsIgnoreCase(parent.operator)) {
                sql += "UNION SELECT Id from table t_" + this.getObject() + " ";
            }
            // Throw exceptions for any unknown scenarios
            else throw new RuntimeException("Unknown case");
        
        // If operator nodes
        } else {
            // 1. If root node, don't print any extra statements
            if(parent == null) {
                sql += "(";
            }
            // 2. If no elder siblings, don't print any extra statements
            else if(elder == null) {
                sql += "(";
            }
            // 3. If has elder siblings and parent was an AND, print as join
            else if("AND".equalsIgnoreCase(parent.operator)) {
                sql += "JOIN (";
            }
            // 4. If has elder siblings and parent was an OR, print as UNION
            else if("OR".equalsIgnoreCase(parent.operator)){
                sql += "UNION (";
            }
            else throw new RuntimeException("Unknown case");
        }
        
        return sql;
    }

    @Override
    public String printAfterChildren(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited) {
        String sql = "";
        TreeNodeImpl parent = (TreeNodeImpl) originalList.stream().filter(p -> this.isChildOf(p)).findFirst().orElse(null);
        TreeNodeImpl elder = (TreeNodeImpl) visited.stream().filter(v -> v.getParent() == this.getParent() && v.getObject() != this.getObject()).reduce((first,second) -> second).orElse(null);
        
        // Only for operator nodes
        if(! "SELECT".equalsIgnoreCase(operator)) {
            // 1. If root node, just close with alias
            if(parent == null) {
                sql += ") t_" + this.getObject() + " ";
            }
            // 2. If no elder siblings and parent was an OR, just close with alias
            else if(elder == null) {
                sql += ") t_" + this.getObject() + " ";
            }
            // 3. If has elder siblings and parent was an AND, close the join 
            else if("AND".equalsIgnoreCase(parent.operator)) {
                sql += ") t_" + this.getObject() + " ON t_" + this.getObject() + ".Id = t_" + elder.getObject() + ".Id ";
            }
            // 4. If has elder siblings and parent was an OR, close with alias
            else if ("OR".equalsIgnoreCase(parent.operator)) {
                sql += ") t_" + this.getObject() + " ";
            }
            else throw new RuntimeException("Unknown case");
        }
        return sql;
    }

    @Override
    public String printEnd(final List<DFSTreeNode> originalList, final List<DFSTreeNode> visited) {
        String sql = "";
        
        return sql;
    }
    
}
