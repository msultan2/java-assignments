////////////////////////////////////////////////////////////////////////////////
//
//  THIS SOFTWARE IS PROVIDED BY COSTAIN INTEGRATED TECHNOLOGY SOLUTIONS
//  LIMITED ``AS IS'', WITH NO WARRANTY, TERM OR CONDITION OF ANY KIND,
//  EXPRESS OR IMPLIED, AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING,
//  BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
//  FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL COSTAIN
//  INTEGRATED TECHNOLOGY SOLUTIONS LIMITED BE LIABLE FOR ANY LOSSES, CLAIMS
//  OR DAMAGES OF WHATEVER NATURE, INCLUDING ANY DIRECT, INDIRECT, INCIDENTAL,
//  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES EVEN IF ADVISED OF THE
//  POSSIBILITY OF SUCH DAMAGES OR LOSSES (INCLUDING, BUT NOT LIMITED TO,
//  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
//  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
//  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
//  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE.
//
//  Copyright 2016 (C) Costain Integrated Technology Solutions Limited.
//  All Rights Reserved.
//
////////////////////////////////////////////////////////////////////////////////
package com.assignment;

import com.assignment.entities.Activity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mohamed
 */
public class OrganiserTest {

    String fileName = "src/main/resources/activities.txt";
    Activities activities;
    List<Activity> activitiesList;
    Organiser organiser;

    public OrganiserTest() {
        activities = new Activities(fileName);
        activitiesList = new ArrayList<>();
    }


    @Before
    public void setUp() {

        try {
            activitiesList = activities.getActivities();
            organiser = new Organiser(activitiesList, 2);
        } catch (IOException ex) {
        }
    }

    /**
     * Test of createTeams method, of class Organiser.
     */
    @Test
    public void testCreateTeams() {
        System.out.println("createTeams");
        List<Team> expResult = new ArrayList<>();
        expResult.add(new Team(new ArrayList<>()));
        expResult.add(new Team(new ArrayList<>()));
        List<Team> result = organiser.createTeams();
        assertEquals(expResult.size(), result.size());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of distributeTeam method, of class Organiser.
     */
    @Test
    public void testDistributeTeam() {
        System.out.println("distributeTeam");
        List<Team> expResult = null;
        List<Team> result = organiser.distributeTeam();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }


}
