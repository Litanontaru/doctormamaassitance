package com.example.doctormamaassistance.statemachine.builder;

import com.example.doctormamaassistance.statemachine.Context;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Andrei_Yakushin
 */
class TransitionConfigurerImpl implements TransitionConfigurer {
    private final BuilderImpl builder;
    private final TransitionBuilder head;

    private TransitionBuilder building;

    TransitionConfigurerImpl(BuilderImpl builder, String source) {
        this.builder = builder;
        building = new TransitionBuilder();
        building.setSource(source);
        head = building;
    }

    @Override
    public TransitionConfigurer target(String targetState) {
        building.setTarget(targetState);
        return this;
    }

    @Override
    public TransitionConfigurer action(Consumer<Context> action) {
        building.setAction(action);
        return this;
    }

    @Override
    public TransitionConfigurer when(Predicate<Context> condition) {
        building.setCondition(condition);
        building.setOrElse(new TransitionBuilder());
        this.building = building.getOrElse();
        return this;
    }

    @Override
    public Builder and() {
        return builder.addTransition(head);
    }
}
