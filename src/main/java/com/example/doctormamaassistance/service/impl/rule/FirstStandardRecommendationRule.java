package com.example.doctormamaassistance.service.impl.rule;

import com.example.doctormamaassistance.model.Recommendation;
import com.example.doctormamaassistance.statemachine.Context;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author Andrei_Yakushin
 */
@Component
public class FirstStandardRecommendationRule implements Consumer<Context> {
    private static final long FIX_GET_UP_TYPE_ID = 2L;

    @Override
    public void accept(Context context) {
        context.addMessage(new Recommendation()
                .init(1)
                .setTypeId(FIX_GET_UP_TYPE_ID)
                .setSummary("Зафиксировать время подъёма")
                .setNote("todo"));
    }
}