package com.example.doctormamaassistance.statemachine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * TODO Write JavaDoc please.
 *
 * @author Andrei_Yakushin
 */
@AllArgsConstructor
@Getter
public class Transition {
    private final String source;
    private final String target;
    private final Consumer<Context> action;
    private final Predicate<Context> condition;
    private final Transition orElse;

    public String execute(Context context) {
        if (condition == null || condition.test(context)) {
            action.accept(context);
            return target;
        } else {
            return orElse.execute(context);
        }
    }
}
