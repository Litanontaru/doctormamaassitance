package com.example.doctormamaassistance.core.statemachine;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * @author Andrei_Yakushin
 */
@AllArgsConstructor
@Getter(AccessLevel.PACKAGE)
public class Config {
    private final String initialState;
    private final Map<String, List<Transition>> transitions;
}