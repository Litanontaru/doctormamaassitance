package com.example.doctormamaassistance.core.service;

/**
 *
 * @author Andrei_Yakushin
 */
public interface RuleEngine {
    void generateScheduledForToday(Long childId);

    void generateScheduledForToday();
}
