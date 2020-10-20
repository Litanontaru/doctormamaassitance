package com.example.doctormamaassistance.service.impl.rule;

import static java.util.Arrays.asList;

import com.example.doctormamaassistance.core.model.Recommendation;
import com.example.doctormamaassistance.service.Description;
import com.example.doctormamaassistance.service.impl.DescriptionProvider;
import com.example.doctormamaassistance.core.statemachine.Context;
import org.springframework.stereotype.Component;

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
                asList("Расчёт произведён", "Распорядок дня приведён в соответствие с правилом"),
                asList(YES_OPTION, YES_OPTION)
        );
    }

    @Override
    public void accept(Context context) {
        context.addMessage(new Recommendation()
                .init(getDescription().getItems().size())
                .setTypeId(RULE_12_TYPE_ID)
                .setSummary("Проверить правило 12 часов")
                .setNote("todo"));
    }
}