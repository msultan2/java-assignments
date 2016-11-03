/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment;

import com.assignment.entities.Activity;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author neeleshchavan
 */
public class TeamsTest {

    String fileName = "src/main/resources/activities.txt";

    public TeamsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    Activities activities;
    @Before
    public void setUp() {
        activities = new Activities(fileName);

        List<Activity> list = Arrays.asList(
                new Activity("Duck Herding 60min", 10),
                new Activity("Learning Magic Tricks 40min", 20),
                new Activity("Wine Tasting sprint", 15),
                new Activity("Giant Puzzle Dinosaurs 30min", 15)
        );
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of totalTeams method, of class TeamCount.
     */
    @Test
    public void testTotalTeams() {
        List<Activity> activitiesList = null;

        try {
            activitiesList = activities.getActivities();
        } catch (IOException ex) {
        }

        System.out.println("totalTeams");
        TeamCount instance = new TeamCount(activitiesList);
        int expResult = 2;
        int result = instance.totalTeams();
        assertEquals(expResult, result);
    }

    @Test
    public void testTotalTeamsTeststest() {
        List<Activity> activitiesList = null;

        try {
            activitiesList = activities.getActivities();
        } catch (IOException ex) {
        }

        seperateList(activitiesList);


        System.out.println("totalTeams");
        TeamCount instance = new TeamCount(activitiesList);
        int expResult = 2;
        int result = instance.totalTeams();
        assertEquals(expResult, result);
    }

    private void seperateList(List<Activity> activitiesList) {
        //sort by Activity
        Collections.sort(activitiesList, new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return o1.getTimeMinutes() - o2.getTimeMinutes();
            }
        });
    }
}
