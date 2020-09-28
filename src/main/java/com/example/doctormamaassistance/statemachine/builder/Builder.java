package com.example.doctormamaassistance.statemachine.builder;

import com.example.doctormamaassistance.statemachine.Config;

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