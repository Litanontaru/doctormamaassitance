package com.example.doctormamaassistance.statemachine.builder;

import com.example.doctormamaassistance.statemachine.Context;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Andrei_Yakushin
 */
public interface TransitionConfigurer {
    TransitionConfigurer target(String targetState);
    TransitionConfigurer action(Consumer<Context> action);
    TransitionConfigurer when(Predicate<Context> condition);

    Builder and();
}