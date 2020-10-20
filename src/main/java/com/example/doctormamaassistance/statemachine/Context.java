package com.example.doctormamaassistance.statemachine;

import com.example.doctormamaassistance.core.model.Child;
import com.example.doctormamaassistance.core.model.MessageLike;

/**
 * @author Andrei_Yakushin
 */
public interface Context {
    Child child();

    void addMessage(MessageLike message);

    void skipDaysAndTerminate(int days);
}
