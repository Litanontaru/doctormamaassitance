package com.example.doctormamaassistance.service.impl.rule;

import static java.util.Collections.singletonList;

import com.example.doctormamaassistance.model.Recommendation;
import com.example.doctormamaassistance.service.Description;
import com.example.doctormamaassistance.service.impl.DescriptionProvider;
import com.example.doctormamaassistance.statemachine.Context;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author Andrei_Yakushin
 */
@Component
public class SecondStandardRecommendationRule implements Consumer<Context>, DescriptionProvider {
    private static final long RULE_12_TYPE_ID = 3L;

    @Override
    public Description getDescription() {
        return new Description(
                RULE_12_TYPE_ID,
                Arrays.asList("Расчёт произведён", "Распорядок дня приведён в соответствие с правилом"),
                singletonList(YES_OPTION)
        );
    }

    @Override
    public void accept(Context context) {
        context.addMessage(new Recommendation()
                .init(1)
                .setTypeId(RULE_12_TYPE_ID)
                .setSummary("Проверить правило 12 часов")
                .setNote("todo"));
    }
}