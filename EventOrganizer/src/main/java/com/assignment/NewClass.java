/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;

/**
 *
 * @author neeleshchavan
 */
public class NewClass {
    
    Instant instant;
    
    public void getDateTime(String date){
        
        LocalDate dateOfBirth = LocalDate.of(2010, 01, 14);
        LocalTime time = LocalTime.MIN;
        LocalTime time11 =LocalTime.of(9, 0, 0);
        System.out.println("time  -  "+time11);
        time11 = time11.plusMinutes(60);
        System.out.println("time  -  "+time11);
        
        LocalDateTime datetime = LocalDateTime.of(2016, Month.DECEMBER, 31, 9, 0); 
        
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        String dateInString = "Friday, Jun 7, 2013 12:10:56 PM";
        
        System.out.println("date = "+datetime.toLocalTime());
    }
    
    public static void main(String[] args) {
        NewClass class111 = new NewClass();
        class111.getDateTime("");
    }
    
}
