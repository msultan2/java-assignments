/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment;

import com.assignment.entities.Activity;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author neeleshchavan
 */
public class Organiser {

    List<Activity> activities;
    TreeSet<Team> listSchedule;
    int totalNumberOfTeams;

    public Organiser(List<Activity> activities, int totalNumberOfTeams) {
        this.activities = activities;
        this.totalNumberOfTeams = totalNumberOfTeams;
    }

    public List<Team> distributeTeam() {

        List<Team> teams = createTeams();
        //sort by Activity
//        Collections.sort(activities, (Activity o1, Activity o2) -> o2.getTimeMinutes() - o1.getTimeMinutes());

        for (Team team : teams) {
            while (!activities.isEmpty() && team.lastSchedule().getActivity().getActivity().equals("Staff Motivation Presentation")) {
                    team.fillActivity(activities.get(0));
                activities.remove(0);
            }
        }
        return teams;
    }

    List<Activity> activityTeam1;
    int minuteFixedSessionTime = 360;
    
    LocalTime time;

    //Create Full Team and return 
    public List<Team> createTeams() {
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < totalNumberOfTeams; i++) {
            teams.add(new Team(new ArrayList<>()));
        }
        return teams;
    }

}
