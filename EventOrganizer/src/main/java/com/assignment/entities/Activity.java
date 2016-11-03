/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.entities;

/**
 *
 * @author neeleshchavan
 */
public class Activity {
    
    private String activity;
    
    private int timeMinutes;

    public Activity(String activity, int timeMinutes) {
        this.activity = activity;
        this.timeMinutes = timeMinutes;
    }

    public String getActivity() {
        return activity;
    }

    public int getTimeMinutes() {
        return timeMinutes;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setTimeMinutes(int timeMinutes) {
        this.timeMinutes = timeMinutes;
    }
}
