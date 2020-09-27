package com.example.doctormamaassistance.service;

/**
 * @author Andrei_Yakushin
 */
public interface DescriptionStore {
    void register(Description description);

    Description get(Long typeId);
}