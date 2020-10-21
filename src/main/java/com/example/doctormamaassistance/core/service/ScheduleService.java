package com.example.doctormamaassistance.core.service;

import com.example.doctormamaassistance.core.model.Child;
import com.example.doctormamaassistance.core.model.ScheduleAction;

import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface ScheduleService {
    void schedule(Child child, List<Integer> days);

    List<ScheduleAction> getTodaySchedule();

    @Deprecated
    void setShift(int shift);
}