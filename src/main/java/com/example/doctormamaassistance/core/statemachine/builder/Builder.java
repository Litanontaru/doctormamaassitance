package com.example.doctormamaassistance.core.statemachine.builder;

import com.example.doctormamaassistance.core.statemachine.Config;

/**
 * @author Andrei_Yakushin
 */
public interface Builder {
    static Builder startWith(String state) {
        return new BuilderImpl(state);
    }

    TransitionConfigurer source(String source);

    Config build();
}