/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment;

import com.assignment.entities.Activity;
import java.util.List;

/**
 *
 * @author neeleshchavan
 */
public class TeamCount {

    List<Activity> activities;
    int totalMinutesInDay = 360;

    public TeamCount(List<Activity> activities) {
        this.activities = activities;
    }

    public int totalTeams() {
        int totalTime = 0;

        for (Activity activity : activities) {
            totalTime += activity.getTimeMinutes();
        }
        return totalTime / totalMinutesInDay;
    }


}
