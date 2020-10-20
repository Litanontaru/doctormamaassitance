package com.example.doctormamaassistance.core.service;

import static java.util.Collections.singletonList;

import com.example.doctormamaassistance.core.service.Description;

import java.util.Arrays;
import java.util.List;

/**
 * @author Andrei_Yakushin
 */
public interface DescriptionProvider {
    List<String> YES_OPTION = singletonList("Да");
    List<String> YES_NO_OPTION = Arrays.asList("Да", "Нет");

    Description getDescription();
}
