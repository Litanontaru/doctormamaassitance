package com.example.doctormamaassistance.service.impl;

import com.example.doctormamaassistance.repository.ChildRepository;
import com.example.doctormamaassistance.service.MessageStore;
import com.example.doctormamaassistance.service.RuleEngine;
import com.example.doctormamaassistance.service.ScheduleService;
import com.example.doctormamaassistance.service.impl.rule.FirstStandardRecommendationRule;
import com.example.doctormamaassistance.service.impl.rule.StartAssistanceRule;
import com.example.doctormamaassistance.statemachine.StateMachine;
import com.example.doctormamaassistance.statemachine.builder.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Andrei_Yakushin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RuleEngineImpl implements RuleEngine {
    private final ChildRepository childRepository;
    private final MessageStore store;
    private final ScheduleService scheduleService;

    private final StartAssistanceRule startAssistanceRule;
    private final FirstStandardRecommendationRule firstStandardRecommendationRule;

    private StateMachine machine;

    @SuppressWarnings("unused")
    @PostConstruct
    private void initStateMachine() {
        machine = new StateMachine(
                Builder.startWith("Start")
                        .source("Start").action(startAssistanceRule).target("First recommendation to go")
                        .and()
                        .source("First recommendation to go").action(firstStandardRecommendationRule.andThen(c -> c.skipDaysAndTerminate(1))).target("Second recommendation to go")
                        .and()
                        .source("Second recommendation to go").action(c -> c.skipDaysAndTerminate(1)).target("Third recommendation to go")
                        .and()
                        .build(),
                store,
                scheduleService
        );
    }

    @Override
    public void generate(Long childId) {
        childRepository
                .findById(childId)
                .ifPresent(machine::generate);
    }

    @Override
    public void generate() {
        scheduleService.getTodaySchedule().forEach(this::generate);
    }
}