package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        this.calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        Collections.sort(calendar, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting m1, Meeting m2) {
                if (m1.getEndTime().equals(m2.getEndTime()))
                {
                    return m1.getStartTime().compareTo(m2.getStartTime());
                }
                return m1.getEndTime().compareTo(m2.getEndTime());
            }
        });

        int count = 0;
        LocalTime lastEndTime = null;

        for (Meeting meeting : calendar){
            if (lastEndTime == null || meeting.getStartTime().isAfter(lastEndTime)){
                count++;
                lastEndTime = meeting.getEndTime();
            }
        }
        return count;
    }
}
