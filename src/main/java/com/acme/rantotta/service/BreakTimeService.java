package com.acme.rantotta.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BreakTimeService {
    
    private TreeSet<String> breakTimes = new TreeSet<String>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm");
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat hourFormatter = new SimpleDateFormat("HH:mm");

    @Autowired
    public BreakTimeService(@Value("${breaktimes.init}") String breaks) {
        for(String next: breaks.split(",")) {
            breakTimes.add(next);
        }
    }

    public void add(String breakTime) {
        breakTimes.add(breakTime);
    }
    
    public void delete(String breakTime) {
        breakTimes.remove(breakTime);
    }
    
    public List<String> getAll() {
        return new ArrayList(breakTimes);
    }
    
    public int getMinutesTillNext() {
        int ret = 0;
        Date now = new Date();
        String today = dateFormatter.format(now);
        String hours = hourFormatter.format(now);
        try {
            String nextBreak = breakTimes.higher(hours);
            if (nextBreak != null) {
                Date breakTime = sdf.parse(today + nextBreak);
                ret = (int) (( breakTime.getTime() - now.getTime()) / 60000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return ret;
    }
}
