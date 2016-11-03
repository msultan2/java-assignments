/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment;

import com.assignment.entities.Activity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author neeleshchavan
 */
public class Activities {

    String fileName;

    public Activities(String fileName) {
        this.fileName = fileName;
    }

    public List<Activity> getActivities() throws IOException {
        Stream<String> stream = Files.lines(Paths.get(fileName));
        return stream.map(st -> fillActivity(st))
                .collect(Collectors.toList());
    }

    private Activity fillActivity(String lines) {
        if (lines.matches(".*sprint.*")) {
            return new Activity(lines, 15);
        } else {
            return new Activity(lines, minutesActivity(lines));
        }
    }

    private int minutesActivity(String line) {
        String[] splitMinutes = line.split(" ");
        return Integer.parseInt(splitMinutes[splitMinutes.length - 1].replace("min", ""));
    }
}
