package com.assignment;

import com.assignment.entities.Activity;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author neeleshchavan
 */
public class Team {

    List<Schedule> schedule;

    public Team(List<Schedule> schedule) {
        this.schedule = schedule;
        fillLunchTime();
        fillPresentationTime();
    }
    
    private void fillPresentationTime() {
        this.schedule.add(new Schedule(LocalTime.NOON.plusHours(4), LocalTime.NOON.plusHours(4).plusMinutes(60), new Activity("Preentation", 60)));
    }

    private void fillLunchTime() {
        this.schedule.add(new Schedule(LocalTime.NOON, LocalTime.NOON.plusMinutes(60), new Activity("Lunch Time", 60)));
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public LocalTime bestActivityTime(Activity activity) {

        if (schedule.size() == 2) {
            return LocalTime.of(9, 0);
        } else {
            for (int i = 0; i < schedule.size() - 1; i++) {
                Schedule currentScheduleTime = schedule.get(i);
                Schedule nextScheduleTime = schedule.get(i + 1);
                if (withinTimeIntervals(currentScheduleTime, nextScheduleTime)) {
                    return currentScheduleTime.getStartTime().plusMinutes(currentScheduleTime.activity.getTimeMinutes());
                }
            }
        }
        return LocalTime.MAX;
    }

    private static boolean withinTimeIntervals(Schedule currentScheduleTime, Schedule nextScheduleTime) {
        LocalTime currentScheduleEndTime = currentScheduleTime.getStartTime().plusMinutes(
                currentScheduleTime.activity.getTimeMinutes());

        return currentScheduleEndTime.isBefore(
                nextScheduleTime.getStartTime());
    }

    void fillActivity(Activity activity) {
        LocalTime newSchecdulTime = bestActivityTime(activity);
        schedule.add(new Schedule(LocalTime.of(newSchecdulTime.getHour(), newSchecdulTime.getMinute()), activity));
        sortList();
    }

    private void sortList() {
        Collections.sort(schedule, new Comparator<Schedule>() {
            @Override
            public int compare(Schedule o1, Schedule o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        });
    }

    public class CustomComparator implements Comparator<Schedule> {

        @Override
        public int compare(Schedule o1, Schedule o2) {
            return o1.getStartTime().compareTo(o2.getStartTime());
        }
    }
}
