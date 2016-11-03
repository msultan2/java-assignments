/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment;

import com.assignment.entities.Activity;
import java.time.LocalTime;

/**
 *
 * @author neeleshchavan
 */
public class Schedule {

    private LocalTime startTime;
    private Activity activity;

    public Schedule(LocalTime time, Activity activity) {
        this.startTime = time;
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

}
