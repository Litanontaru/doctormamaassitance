package com.example.doctormamaassistance.statemachine.builder;

import com.example.doctormamaassistance.statemachine.Config;
import com.example.doctormamaassistance.statemachine.StateMachine;
import com.example.doctormamaassistance.statemachine.Transition;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrei_Yakushin
 */
@RequiredArgsConstructor
class BuilderImpl implements Builder {
    private final String initialState;
    private final List<TransitionBuilder> transitions = new ArrayList<>();

    Builder addTransition(TransitionBuilder transition) {
        transitions.add(transition);
        return this;
    }

    @Override
    public TransitionConfigurer source(String source) {
        return new TransitionConfigurerImpl(this, source);
    }

    @Override
    public Config build() {
        //todo - check consistency that all sources a either initial state or target of some transition
        //todo - check that transitions from one source have either the same target or null
        return new Config(
                initialState,
                transitions
                        .stream()
                        .map(TransitionBuilder::build)
                        .collect(Collectors.groupingBy(Transition::getSource))
        );
    }
}
