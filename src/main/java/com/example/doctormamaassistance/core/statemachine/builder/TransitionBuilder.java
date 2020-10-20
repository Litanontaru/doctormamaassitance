package com.example.doctormamaassistance.core.statemachine.builder;

import com.example.doctormamaassistance.core.statemachine.Context;
import com.example.doctormamaassistance.core.statemachine.Transition;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Andrei_Yakushin
 */
@Getter
@Setter
class TransitionBuilder {
    private String source;
    private String target;
    private Consumer<Context> action = c -> {};
    private Predicate<Context> condition;
    private TransitionBuilder orElse;

    Transition build() {
        return new Transition(source, target, action, condition, orElse == null ? null : orElse.build());
    }
}
