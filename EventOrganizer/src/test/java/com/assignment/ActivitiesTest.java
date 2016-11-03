/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment;

import com.assignment.entities.Activity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author neelesh
 */
public class ActivitiesTest {

    public ActivitiesTest() {
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
     * Test of readFile method, of class Activities.
     */
    @Test
    public void testReadFile() {
        System.out.println("readFile");
        String fileName = "src/main/resources/activities.txt";
        String line = "Learning Magic Tricks 40min";

        Activities instance = new Activities(fileName);
        List<Activity> result = null;
        try {
            result = instance.getActivities();
        } catch (IOException ex) {
        }
        List<Activity> expResult = new ArrayList<>();
        expResult.add(new Activity(line, 40));
//        Assert.assertTrue(result.contains(expResult));
    }

    @Test
    public void testActivityFile() {
        String line = "Learning Magic Tricks 40min";
        String line2 = "Salsa & Pickles sprint";
        String line3 = "Archery 45min";
        String line4 = "Cricket 2020 60min";

        if (line2.matches(".*sprint.*")) {
            System.out.println(line2);
        } else {
            String[] splitMinutes = line.split(" ");
            splitMinutes[splitMinutes.length - 1].replace("min", "");
        }
    }


}
