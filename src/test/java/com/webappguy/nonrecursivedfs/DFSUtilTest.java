/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webappguy.nonrecursivedfs;

import static com.webappguy.nonrecursivedfs.DFSUtil.sortDFS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vincent.lee
 */
public class DFSUtilTest {
    
    public DFSUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sortDFS method, of class DFSUtil.
     */
    @Test
    public void testSortDFSSimple() {
        System.out.println("Simple sorting");
        List<DFSTreeNode> rawNodes = new ArrayList<>();
        List<Integer> expectedResult = Arrays.asList(new Integer[]{0,1,3,5,6,4,7,9,10,11,12,8,2});
        
        rawNodes.add(new TreeNodeImpl(11,10,""));
        rawNodes.add(new TreeNodeImpl(10,9,""));
        rawNodes.add(new TreeNodeImpl(3,1,""));
        rawNodes.add(new TreeNodeImpl(5,3,""));
        rawNodes.add(new TreeNodeImpl(7,4,""));
        rawNodes.add(new TreeNodeImpl(12,9,""));
        rawNodes.add(new TreeNodeImpl(2,0,""));
        rawNodes.add(new TreeNodeImpl(8,4,""));
        rawNodes.add(new TreeNodeImpl(4,1,""));
        rawNodes.add(new TreeNodeImpl(6,5,""));
        rawNodes.add(new TreeNodeImpl(1,0,""));
        rawNodes.add(new TreeNodeImpl(9,7,""));
        rawNodes.add(new TreeNodeImpl(0,-1,""));
        
        
        List<DFSTreeNode> results = DFSUtil.sortDFS(rawNodes);
        
        assertEquals(expectedResult.size(),results.size());
        for(int i=0; i < expectedResult.size(); i++) {
            assertEquals(results.get(i).getObject(),expectedResult.get(i));
        }
    }
    
    @Test
    public void testPrintDFSSimple() {
        System.out.println("Simple printing");
        List<DFSTreeNode> rawNodes = new ArrayList<>();
        
        rawNodes.add(new TreeNodeImpl(0,-1,"AND"));
        rawNodes.add(new TreeNodeImpl(1,0,"OR"));
        rawNodes.add(new TreeNodeImpl(2,1,"SELECT"));
        rawNodes.add(new TreeNodeImpl(3,1,"SELECT"));
        rawNodes.add(new TreeNodeImpl(4,1,"SELECT"));
        rawNodes.add(new TreeNodeImpl(5,0,"AND"));
        rawNodes.add(new TreeNodeImpl(6,5,"SELECT"));
        rawNodes.add(new TreeNodeImpl(7,5,"SELECT"));
        rawNodes.add(new TreeNodeImpl(8,5,"OR"));
        rawNodes.add(new TreeNodeImpl(9,8,"SELECT"));
        rawNodes.add(new TreeNodeImpl(10,8,"SELECT"));
        
        String results = DFSUtil.printDFS(rawNodes);
        
        
        System.out.println(results);
    }
}
