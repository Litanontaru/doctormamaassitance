package com.example.doctormamaassistance.service.impl;

import com.example.doctormamaassistance.core.repository.ChildRepository;
import com.example.doctormamaassistance.service.MessageStore;
import com.example.doctormamaassistance.service.RuleEngine;
import com.example.doctormamaassistance.service.ScheduleService;
import com.example.doctormamaassistance.service.impl.rule.FirstStandardRecommendationRule;
import com.example.doctormamaassistance.service.impl.rule.SecondStandardRecommendationRule;
import com.example.doctormamaassistance.service.impl.rule.StartAssistanceRule;
import com.example.doctormamaassistance.statemachine.Context;
import com.example.doctormamaassistance.statemachine.StateMachine;
import com.example.doctormamaassistance.statemachine.builder.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

/**
 * @author Andrei_Yakushin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RuleEngineImpl implements RuleEngine {
    private static final Consumer<Context> SKIP_ONE = c -> c.skipDaysAndTerminate(1);

    private final ChildRepository childRepository;
    private final MessageStore store;
    private final ScheduleService scheduleService;

    private final StartAssistanceRule startAssistanceRule;
    private final FirstStandardRecommendationRule firstStandardRecommendationRule;
    private final SecondStandardRecommendationRule secondStandardRecommendationRule;

    private StateMachine machine;

    @SuppressWarnings("unused")
    @PostConstruct
    private void initStateMachine() {
        machine = new StateMachine(
                Builder.startWith("Start")
                        .source("Start").action(startAssistanceRule).target("First recommendation to go")
                        .and()
                        .source("First recommendation to go").action(firstStandardRecommendationRule.andThen(SKIP_ONE)).target("Second recommendation to go")
                        .and()
                        .source("Second recommendation to go").action(secondStandardRecommendationRule.andThen(SKIP_ONE)).target("Third recommendation to go")
                        .and()
                        .build(),
                store,
                scheduleService
        );
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