package com.example.doctormamaassistance.service.impl;

import com.example.doctormamaassistance.core.statemachine.Config;
import com.example.doctormamaassistance.core.statemachine.Context;
import com.example.doctormamaassistance.core.statemachine.builder.Builder;
import com.example.doctormamaassistance.service.ConfigFactory;
import com.example.doctormamaassistance.service.impl.rule.FirstStandardRecommendationRule;
import com.example.doctormamaassistance.service.impl.rule.SecondStandardRecommendationRule;
import com.example.doctormamaassistance.service.impl.rule.StartAssistanceRule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfigFactoryImpl implements ConfigFactory {
    private static final Consumer<Context> SKIP_ONE = c -> c.skipDaysAndTerminate(1);

    private final StartAssistanceRule startAssistanceRule;
    private final FirstStandardRecommendationRule firstStandardRecommendationRule;
    private final SecondStandardRecommendationRule secondStandardRecommendationRule;

    @Override
    public Config create() {
        return Builder.startWith("Start")
                .source("Start").action(startAssistanceRule).target("First recommendation to go")
                .and()
                .source("First recommendation to go").action(firstStandardRecommendationRule.andThen(SKIP_ONE)).target("Second recommendation to go")
                .and()
                .source("Second recommendation to go").action(secondStandardRecommendationRule.andThen(SKIP_ONE)).target("Third recommendation to go")
                .and()
                .build();
    }
}
