package com.example.doctormamaassistance.service;

import com.example.doctormamaassistance.core.model.Child;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface ScheduleService {
    void schedule(Child child, List<Integer> days);

    List<Long> getTodaySchedule();

    void setShift(int shift);
}