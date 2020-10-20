package com.example.doctormamaassistance.service.impl;

import com.example.doctormamaassistance.core.repository.ChildRepository;
import com.example.doctormamaassistance.core.statemachine.StateMachine;
import com.example.doctormamaassistance.service.MessageStore;
import com.example.doctormamaassistance.service.RuleEngine;
import com.example.doctormamaassistance.service.ScheduleService;
import com.example.doctormamaassistance.service.ConfigFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Andrei_Yakushin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RuleEngineImpl implements RuleEngine {
    private final ConfigFactory factory;
    private final ChildRepository childRepository;
    private final ScheduleService scheduleService;
    private final MessageStore store;

    private StateMachine machine;

    @SuppressWarnings("unused")
    @PostConstruct
    private void initStateMachine() {
        machine = new StateMachine(factory.create(), store, scheduleService);
    }

    @Override
    public void generateScheduledForToday(Long childId) {
        childRepository
                .findById(childId)
                .ifPresent(machine::generate);
    }

    @Override
    @Scheduled(cron = "0 1 0 * * ?")
    public void generateScheduledForToday() {
        scheduleService.getTodaySchedule().forEach(this::generateScheduledForToday);
    }
}