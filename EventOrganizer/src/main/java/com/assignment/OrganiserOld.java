/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment;

import com.assignment.entities.Activity;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeSet;

/**
 *
 * @author neeleshchavan
 */
public class OrganiserOld {

    List<Activity> activities;
    TreeSet<Team> listSchedule;
    List<Schedule> schedule1;
    List<Schedule> schedule2;
    int totalNumberOfTeams;

    public OrganiserOld(List<Activity> activities, int totalNumberOfTeams) {
        this.activities = activities;
        this.totalNumberOfTeams = totalNumberOfTeams;
    }

    public List<Team> distributeActivity() {

        List<Team> teams = new ArrayList<>();
        //sort by Activity
        Collections.sort(activities, (Activity o1, Activity o2) -> o2.getTimeMinutes() - o1.getTimeMinutes());    
          //Create Team 
        for (int i = 0; i < totalNumberOfTeams; i++) {
            teams.add(createTeam());
        }
        return teams;
    }

    List<Activity> activityTeam1;
    int minuteFixedSessionTime = 360;
    
    //Create Full Team and return 
    private Team createTeam() {       
        temActivityAllocation();        
        return new Team(scheduleListTeam);
    }
    
    TreeSet<Schedule> scheduleTeam;
    List<Schedule> scheduleListTeam;
    LocalTime time;

    private void temActivityAllocation() {

        activityTeam1 = new ArrayList<>();
        time = LocalTime.MIN;
        time = time.plusHours(9);
        
        scheduleTeam = new TreeSet<>();
        scheduleListTeam = new ArrayList<>();
  
        SortedMap<Integer, Activity> teams;
        
        for(Activity activity : activities){
            
            int currentActivityTime = activity.getTimeMinutes();

            if (time.equals(LocalTime.NOON)) {
                time = time.plusMinutes(60);
                scheduleListTeam.add(new Schedule(LocalTime.NOON, new Activity("Lunch Time", 60)));
            } else if (time.equals(LocalTime.NOON.plusHours(4))
                    || time.equals(LocalTime.NOON.plusHours(5).plusMinutes(5))) {
                time = time.plusMinutes(60);
                scheduleListTeam.add(new Schedule(LocalTime.NOON.plusHours(4), new Activity("Presentation Ceremony", 60)));
            }

            if (time.plusMinutes(currentActivityTime).isBefore(LocalTime.NOON)
                    && !time.plusMinutes(currentActivityTime).isAfter(LocalTime.NOON)) {
                time = time.plusMinutes(currentActivityTime);
                scheduleListTeam.add(new Schedule(time, activity));
            } else if (time.plusMinutes(currentActivityTime).equals(LocalTime.NOON.plusHours(1))
                    || time.plusMinutes(currentActivityTime).isAfter(LocalTime.NOON.plusHours(1))
                    && time.plusMinutes(currentActivityTime).isBefore(LocalTime.NOON.plusHours(4))
                    || time.plusMinutes(currentActivityTime).equals(LocalTime.NOON.plusHours(4))) {
             time = time.plusMinutes(currentActivityTime);
             scheduleListTeam.add(new Schedule(time, activity));
            } 

//            long teamSize = activityTeam1.stream()
//                    .mapToInt(activ -> activ.getTimeMinutes())
//                    .sum();
//
//               if(teamSize < minuteFixedSessionTime && teamSize + currentActivityTime <= minuteFixedSessionTime){
//                   activityTeam1.add(activity);
//                   System.out.println("ActivityTeam1 add - "+activity.getActivity()+", "+activity.getTimeMinutes());
//               }
        
        }

        printTeamSchedule();
        
    }

    private void printTeamSchedule() {
        System.out.println("-----------------------111---------------------------------------------");
        for (Schedule aa : scheduleListTeam) {
            System.out.println("Activity = " + aa.startTime + ", time = " + aa.activity);
        }
    }

    private void activityLevels(Activity activity) {
        
        int currentActivityTime = activity.getTimeMinutes();
        
        long teamSize = activityTeam1.stream()
                .map(activ -> activ.getTimeMinutes())
                .count();
                
        System.out.println("Size Team 1 = "+teamSize);
        
        if(teamSize < minuteFixedSessionTime && teamSize + currentActivityTime <= minuteFixedSessionTime){
            activityTeam1.add(activity);
            activities.remove(activity);
        }
    }
    
//    private void addSchedule(List<Activity> list1, List<Activity> activitiesList) {
//        if (suitablePlace(list1, activitiesList.get(0))) {
//            activitiesList.remove(0);
//        }
//    }

//    private boolean suitablePlace(List<Activity> list1, Activity activity) {
//        for (Activity act : list1) {
//            if (activity.getTimeMinutes() > "9:00"  {
//                and
//            }
//            
//                act.) {
//                list1.add(activitiesList.get(0));
//                return true;
//            }else{
//                    return false;
//                    }
//        }
//    }
}
