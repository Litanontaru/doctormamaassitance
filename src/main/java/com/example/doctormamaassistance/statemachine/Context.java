package com.example.doctormamaassistance.statemachine;

import com.example.doctormamaassistance.model.Child;
import com.example.doctormamaassistance.model.MessageLike;

/**
 * @author Andrei_Yakushin
 */
public interface Context {
    Child child();

    void addMessage(MessageLike message);

    void skipDaysAndTerminate(int days);
}
