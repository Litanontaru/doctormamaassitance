package com.example.doctormamaassistance.demo.service.impl.rule;

import static java.util.Collections.*;

import com.example.doctormamaassistance.core.model.Recommendation;
import com.example.doctormamaassistance.core.service.Description;
import com.example.doctormamaassistance.core.service.DescriptionProvider;
import com.example.doctormamaassistance.core.statemachine.Context;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author Andrei_Yakushin
 */
@Component
public class FirstStandardRecommendationRule implements Consumer<Context>, DescriptionProvider {
    private static final long FIX_GET_UP_TYPE_ID = 2L;

    @Override
    public Description getDescription() {
        return new Description(
                FIX_GET_UP_TYPE_ID,
                singletonList("Подъём зафиксирован"),
                singletonList(YES_OPTION)
        );
    }

    @Override
    public void accept(Context context) {
        context.addMessage(new Recommendation()
                .init(getDescription().getItems().size())
                .setTypeId(FIX_GET_UP_TYPE_ID)
                .setSummary("Зафиксировать время подъёма")
                .setNote("todo"));
    }
}