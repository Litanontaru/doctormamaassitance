package com.example.doctormamaassistance.demo.service.impl.rule;

import com.example.doctormamaassistance.core.model.Note;
import com.example.doctormamaassistance.core.statemachine.Context;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 *
 * @author Andrei_Yakushin
 */
@Component
public class StartAssistanceRule implements Consumer<Context> {
    private static final long START_NOTE_TYPE_ID = 1L;

    @Override
    public void accept(Context context) {
        context.addMessage(new Note()
                .setTypeId(START_NOTE_TYPE_ID)
                .setSummary("Засыпатель начал работать с вами.")
                .setNote("Вы начали работать с функцией Засыпатель. Пожалуйста ознакомьтесь с видео инструкцией здесь: url."));
    }
}