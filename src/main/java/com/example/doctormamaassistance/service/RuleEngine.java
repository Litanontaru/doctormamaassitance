package com.example.doctormamaassistance.service;

/**
 *
 * @author Andrei_Yakushin
 */
public interface RuleEngine {
    void generateScheduledForToday(Long childId);

    void generateScheduledForToday();
}
