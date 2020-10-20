package com.example.doctormamaassistance.core.statemachine;

import static java.util.stream.Collectors.toList;

import com.example.doctormamaassistance.core.model.Child;
import com.example.doctormamaassistance.core.service.MessageStore;
import com.example.doctormamaassistance.core.service.ScheduleService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * @author Andrei_Yakushin
 */
@AllArgsConstructor
public class StateMachine {
    private final Config config;
    private final MessageStore store;
    private final ScheduleService schedule;

    public void generate(Child child) {
        ContextImpl context = new ContextImpl(child);
        String state = child.getState() == null ? config.getInitialState() : child.getState();
        while (true) {
            List<Transition> transitions = config.getTransitions().get(state);
            if (transitions == null) {
                break;
            }
            List<String> states = transitions
                    .stream()
                    .map(t -> t.execute(context))
                    .filter(Objects::nonNull)
                    .collect(toList());

            if (!states.isEmpty()) {
                state = states.get(0);
            }

            child.setState(state);
            context.popMessages().forEach(store::saveMessage);

            List<Integer> days = context.daysToSkip();
            if (!days.isEmpty()) {
                schedule.schedule(child, days.stream().distinct().collect(toList()));
                break;
            }
        }
    }
}